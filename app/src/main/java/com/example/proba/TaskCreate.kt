package com.example.proba

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proba.adapter.ProductAdapter
import com.example.proba.databinding.CreateTaskBinding
import com.example.proba.databinding.FragmentLoginBinding
import com.example.proba.databinding.FragmentProductsBinding
import com.example.proba.retrofit.AuthRequest
import com.example.proba.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskCreate: Fragment()  {

    lateinit var binding: CreateTaskBinding
    private lateinit var mainApi: MainApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateTaskBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRetrofit()



binding.apply {
   button_task.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            val task = mainApi.newTask(
                Task(
                    taskText.text.toString(),
                    "open"
                )
            )

        }
    }

}
    }



    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor() //создали интерсептор(2)
        interceptor.level = HttpLoggingInterceptor.Level.BODY //перехватываем тело  интерсептор(2)

        val client = OkHttpClient.Builder()  //подключаем клиент к библиотеки ретрофит(2)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.219:1080").client(client) //функция для передачи client (2)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //инициализируем библиотек ретрофит

        mainApi = retrofit.create(MainApi::class.java) //используем конвектор

    }




}