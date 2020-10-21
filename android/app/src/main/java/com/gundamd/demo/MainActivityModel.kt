package com.gundamd.demo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gundamd.thai_postcode_sdk.ThaiPostcodeSDK
import com.gundamd.thai_postcode_sdk.data.ThaiAddress
import kotlinx.coroutines.*


class MainActivityModel(application: Application) : AndroidViewModel(application) {

    val address = MutableLiveData<List<ThaiAddress>?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())

    /**
     * Search
     */
    fun search(string: String) {
        uiScope.launch {
            try {
                address.value = withContext(Dispatchers.IO) {
                    ThaiPostcodeSDK.instance().searchByProvince(string, 30)
                }
            } catch (e: Exception) {
                address.value = arrayListOf()
            }
        }
    }

    private fun cancelLoading() {
        uiScope.cancel()
    }


    override fun onCleared() {
        super.onCleared()
        cancelLoading()
    }
}
