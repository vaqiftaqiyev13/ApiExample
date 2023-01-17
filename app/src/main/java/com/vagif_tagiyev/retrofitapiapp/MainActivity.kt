package com.vagif_tagiyev.retrofitapiapp

import android.companion.CompanionDeviceManager.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vagif_tagiyev.retrofitapiapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var MainActivityBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainActivityBinding.root)

        val serviceGenerator = ServiceGenerator.buildServices(ApiService::class.java)

        val call = serviceGenerator.getAlbums()

        MainActivityBinding.getBtn.setOnClickListener {
            call.enqueue(object : retrofit2.Callback<MutableList<AlbumModel>> {

                override fun onResponse(
                    call: Call<MutableList<AlbumModel>>,
                    response: Response<MutableList<AlbumModel>>
                ) {
                    if (response.isSuccessful) {
                        Log.e("ResponseSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<MutableList<AlbumModel>>, t: Throwable) {
                    t.printStackTrace()

                }

            })
        }
    }
}