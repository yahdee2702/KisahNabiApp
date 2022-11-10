package com.yahdi.kisahnabiapp.data.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yahdi.kisahnabiapp.data.model.KisahResponse
import com.yahdi.kisahnabiapp.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisahViewModel: ViewModel() {
    private val api = ApiClient.getApiService()
    private val kisahData = MutableLiveData<List<KisahResponse>>()

    fun getKisah(): MutableLiveData<List<KisahResponse>> {
        api.getAllKisah().enqueue(object: Callback<List<KisahResponse>> {
            override fun onResponse(
                call: Call<List<KisahResponse>>,
                response: Response<List<KisahResponse>>
            ) {
                if (response.isSuccessful) {
                    kisahData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<KisahResponse>>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }
        })
        return kisahData
    }
}