package com.example.studyprojectrnc.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Telephony.Mms.Part.FILENAME
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studyprojectrnc.R
import com.example.studyprojectrnc.databinding.FragmentCameraBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

@AndroidEntryPoint
class CameraX : Fragment() {

    private var fragmentCameraBinding: FragmentCameraBinding? = null

    private var outputDirectory: File? = null
    private var cameraExecutor = Executors.newSingleThreadExecutor()
    private var cameraProvider: ProcessCameraProvider? = null
    private var cameraSelectorOption = CameraSelector.LENS_FACING_BACK
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK
    private val imageCapture by lazy {
        ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCameraBinding = FragmentCameraBinding.inflate(inflater, container, false)
        return fragmentCameraBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        fragmentCameraBinding?.fabAddPhoto?.setOnClickListener {
            takePhoto()
            Timber.i("Click on TakePhoto")
        }
        loadLatestPhotoForGalleryThumbnail()
        setupSwitch()
        outputDirectory = getOutputDirectory()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                cameraProvider = cameraProviderFuture.get()

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(cameraSelectorOption)
                    .build()

                setCameraConfig(cameraProvider, cameraSelector)

            }, ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun setCameraConfig(
        cameraProvider: ProcessCameraProvider?,
        cameraSelector: CameraSelector
    ) {
        try {
            val preview = Preview.Builder()
                .build()
            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture
            )
            preview.setSurfaceProvider(
                fragmentCameraBinding?.previewView?.surfaceProvider
            )
        } catch (e: Exception) {
            Timber.e("Use case binding failed")
        }
    }

    private fun takePhoto() {
        val photoFile = File(
            outputDirectory, SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    Timber.e("Saved: $msg")
                    setGalleryThumbnail(savedUri)
                }

                override fun onError(exc: ImageCaptureException) {
                    Timber.e("Photo capture failed: ${exc.message}")
                }
            })
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir
        else requireActivity().filesDir
    }


    private fun setupSwitch() {
        fragmentCameraBinding?.fabSwitch.let {
            it?.setOnClickListener {
                cameraProvider?.unbindAll()
                cameraSelectorOption =
                    if (cameraSelectorOption == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                    else CameraSelector.LENS_FACING_BACK
                startCamera()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(), "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor?.shutdown()
    }

    private fun setGalleryThumbnail(uri: Uri) {

        fragmentCameraBinding?.photoViewButton?.let { photoViewButton ->
            photoViewButton.post {
                photoViewButton.setPadding(resources.getDimension(R.dimen.stroke_small).toInt())

                Glide.with(photoViewButton)
                    .load(uri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(photoViewButton)
            }
        }
    }

    private fun loadLatestPhotoForGalleryThumbnail() {
        fragmentCameraBinding?.root.let {
            fragmentCameraBinding?.root?.removeView(it)
        }

        fragmentCameraBinding = FragmentCameraBinding.inflate(
            LayoutInflater.from(requireContext()),
            fragmentCameraBinding?.root,
            true
        )


        lifecycleScope.launch(Dispatchers.IO) {
            outputDirectory?.listFiles { file ->
                EXTENSION_WHITELIST.contains(file.extension.toUpperCase(Locale.ROOT))
            }?.maxOrNull()?.let {
                setGalleryThumbnail(Uri.fromFile(it))
            }
        }

        fragmentCameraBinding?.fabAddPhoto?.setOnClickListener {

            imageCapture.let { imageCapture ->

                val photoFile = createFile(outputDirectory!!, FILENAME, PHOTO_EXTENSION)
                val metadata = ImageCapture.Metadata().apply {

                    // Mirror image when using the front camera
                    isReversedHorizontal = lensFacing == CameraSelector.LENS_FACING_FRONT
                }

                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
                    .setMetadata(metadata)
                    .build()


                imageCapture.takePicture(
                    outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {

                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                            Timber.e("Photo capture succeeded: $savedUri")
                            setGalleryThumbnail(savedUri)
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Timber.e("Photo capture failed: ${exception.message}")
                        }
                    })
            }
        }

        // Listener for button used to view the most recent photo
        fragmentCameraBinding?.photoViewButton?.setOnClickListener {
            if (true == outputDirectory?.listFiles()?.isNotEmpty()) {
                Navigation.findNavController(
                    requireActivity(), R.id.nav_host_fragment
                ).navigate(CameraXDirections
                    .actionCameraFragmentToGalleryFragment(outputDirectory!!.absolutePath))
            }
        }
    }

    private fun updateCameraSwitchButton() {
        try {
            fragmentCameraBinding?.fabSwitch?.isEnabled = hasBackCamera() && hasFrontCamera()
        } catch (exception: CameraInfoUnavailableException) {
            fragmentCameraBinding?.fabSwitch?.isEnabled = false
        }
    }

    private fun hasBackCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA) ?: false
    }

    private fun hasFrontCamera(): Boolean {
        return cameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        val EXTENSION_WHITELIST = arrayOf("JPG")
        private const val PHOTO_EXTENSION = ".jpg"

        /** Helper function used to create a timestamped file */
        private fun createFile(baseFolder: File, format: String, extension: String) =
            File(
                baseFolder, SimpleDateFormat(format, Locale.US)
                    .format(System.currentTimeMillis()) + extension
            )
    }
}