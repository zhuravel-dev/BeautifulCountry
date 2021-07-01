package com.example.studyprojectrnc

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyprojectrnc.db.LiveRealmObject
import com.example.studyprojectrnc.db.RealmObject
import com.example.studyprojectrnc.repository.model.Hits
import com.example.studyprojectrnc.repository.model.HitsDataList
import io.realm.Realm
import io.realm.kotlin.where
import retrofit2.Call

class SecondFragmentViewModel : ViewModel() {
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private var realm: Realm? = null
    val dataModelRealm = RealmObject()
    private val _getData: LiveRealmObject<RealmObject> = LiveRealmObject(null)
    val getData: LiveData<RealmObject>
        get() = _getData

   /* fun getAllData() {
        _showProgress.postValue(true)
        val service = RetrofitClientInstance.getRetrofitInstance().create(ImagesService::class.java)
        val call: Call<HitsDataList> = service.getContent()
        //call.enqueue()
        _showProgress.postValue(false)
    }
*/

    fun getData() {
        realm?.executeTransaction {
            realm -> realm.copyToRealm(dataModelRealm)
        }
    }

    fun readData(){
        val dataModels: List<RealmObject> =
            realm!!.where(RealmObject::class.java).findAll()
    }

    override fun onCleared() {
        realm?.close()
    }
}

