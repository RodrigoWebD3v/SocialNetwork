package com.example.socialnetwork

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnetwork.API.RetrofitHelper
import com.example.socialnetwork.Adapter.PostsAdapter
import com.example.socialnetwork.Interfaces.INomes
import com.example.socialnetwork.Interfaces.IPosts
import com.example.socialnetwork.Models.Nome
import com.example.socialnetwork.Models.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {





     lateinit var  rvPosts : RecyclerView


    private val jsonPlaceHolder by lazy {
        RetrofitHelper.jsonPlaceHolder
    }
    private val ibgNomes by lazy {
        RetrofitHelper.ibgNomes
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPosts = findViewById(R.id.rv_posts)

        CoroutineScope(Dispatchers.IO).launch {
            val posts =  recuperarPosts()
            val nomes = recuperaNomes()
            withContext(Dispatchers.Main){
                try{
                    rvPosts.adapter = PostsAdapter(posts, nomes)
                    rvPosts.layoutManager = LinearLayoutManager(applicationContext)
                }catch (e:Exception){
                    Log.i("error_log", "${e.message}")
                }
            }
        }
    }

    private fun like(id: Any) {

    }

    private suspend fun recuperarPosts() : List<Post> {
        var retorno : Response<List<Post>>? = null

        var posts : List<Post> = emptyList()


        try {
            val postsAPI =  jsonPlaceHolder.create(IPosts::class.java)
            retorno = postsAPI.recuperaPosts()
        }catch (e:Exception){
            Log.i("retorno_log", "Posts - ${e.message} ")
        }

        if (retorno != null && retorno.isSuccessful) {
            val body = retorno.body()
            if (body != null) {
                posts = body
            }
        }
        return posts
    }


    private suspend fun recuperaNomes() : Array<String> {
        var retorno : Response<List<Nome>>? = null

        var nomes : List<Nome> = emptyList()

        var nomesArray : Array<String> = arrayOf()

        try {
            val nomesAPI =  ibgNomes.create(INomes::class.java)
            retorno = nomesAPI.recuperaNomes()
        }catch (e:Exception){
            Log.i("retorno_log", "Nomes - ${e.message} ")
        }

        if (retorno != null && retorno.isSuccessful) {
            val body = retorno.body()
            if (body != null) {
                nomes = body
                nomes.forEach { item -> Log.i("retorno_log", "${item.nome}")
                    nomesArray += item.nome
                }
            }
        }
        return nomesArray
    }



}