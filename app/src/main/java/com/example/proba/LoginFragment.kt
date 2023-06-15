package com.example.proba


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proba.databinding.FragmentLoginBinding
import com.example.proba.retrofit.AuthRequest
import com.example.proba.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mainApi: MainApi


//    private val viewModel: LoginViewModel by activityViewModels(  ) // инициализируем наш класс для токена //?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()

        binding.apply {

            bSignIn.setOnClickListener{    //слушател нажатий

                findNavController().navigate(R.id.action_loginFragment_to_ProductFragment)

                /*  auth(
                       AuthRequest(
                           login.text.toString()
                       )
                   )*/

                // findNavController().navigate(R.id.action_loginFragment_to_productsFragment)
            }
btPlus.setOnClickListener {
    findNavController().navigate(R.id.action_loginFragment_to_TackCreate)
}



        }
    }


    //инициализация ретрофита?
    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor() //создали интерсептор(2)
        interceptor.level = HttpLoggingInterceptor.Level.BODY //перехватываем тело  интерсептор(2)

        val client = OkHttpClient.Builder()  //подключаем клиент к библиотеки ретрофит(2)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            //"http://192.168.1.219:1080"
            .baseUrl("http://192.168.1.219:1080").client(client) //функция для передачи client (2)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //инициализируем библиотек ретрофит
        mainApi = retrofit.create(MainApi::class.java) //используем конвектор
    }

    //функция для авторизации
    private fun auth(authRequest: AuthRequest){
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainApi.auth(authRequest)


            requireActivity().runOnUiThread {
                //    binding.error.text=response.errorBody()?.string()
            }


        }
    }


}