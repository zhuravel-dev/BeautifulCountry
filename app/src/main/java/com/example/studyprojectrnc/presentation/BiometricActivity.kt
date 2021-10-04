package com.example.studyprojectrnc.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.studyprojectrnc.databinding.BiometricBinding
import timber.log.Timber
import java.util.concurrent.Executor

class BiometricActivity : AppCompatActivity() {

    lateinit var binding: BiometricBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(
            this@BiometricActivity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    binding.tvBiometric.text = "Authentication Error: $errString"
                    Timber.i("In BiometricActivity")
                    Toast.makeText(
                        this@BiometricActivity,
                        "Authentication Error: $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    binding.tvBiometric.text = "Authentication Failed"
                    Timber.i("In BiometricActivity: authentication failed")
                    Toast.makeText(
                        this@BiometricActivity,
                        "Auth failed", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    binding.tvBiometric.text = "Authentication succeed"
                    Timber.i("In BiometricActivity: authentication succeed")
                    Toast.makeText(
                        this@BiometricActivity, "Auth succeed", Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(this@BiometricActivity, MainActivity::class.java)
                    )
                    finish()
                }
            })
        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Biometric auth")
            .setSubtitle("Login using fingerprint auth").setNegativeButtonText("Use app password")
            .build()

        binding.btnBiometric.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}