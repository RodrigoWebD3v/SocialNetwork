package com.example.socialnetwork.Adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.socialnetwork.Models.Post
import com.example.socialnetwork.R
import kotlin.random.Random

class PostsAdapter(private val posts: List<Post>, private val nomesArray : Array<String>): Adapter<PostsAdapter.PostsViewHolder>() {

    val imagensDrawer = arrayOf(
        R.drawable.perfil1,
        R.drawable.perfil2,
        R.drawable.perfil3,
        R.drawable.perfil4,
        // Adicione mais IDs de recursos conforme necessário
    )

    inner class PostsViewHolder(
        val itemView : View
    ) : ViewHolder(itemView){
        val imgUsuario : ImageView
        val textName : TextView
        val textBody : TextView
        val textTitle : TextView
        val btnLike : ImageButton



        init {
            textTitle = itemView.findViewById(R.id.text_title)
            textName = itemView.findViewById(R.id.text_name)
            textBody = itemView.findViewById(R.id.text_body)
            imgUsuario = itemView.findViewById(R.id.img_usuario)
            btnLike = itemView.findViewById(R.id.btn_heart)
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.post_lista, parent, false)

        Log.i("Log_Adapter", "Entrou no onCreateViewHolder")
        return PostsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
            val post = posts[position]
            holder.textName.text = nomesArray[Random.nextInt(0, nomesArray.size)]
            holder.textBody.text = post.body
            holder.textTitle.text = post.title
            holder.imgUsuario.setImageResource(imagensDrawer[Random.nextInt(0, imagensDrawer.size)])

            holder.btnLike.setOnClickListener {
                // Altere o estado do botão no modelo de dados
                post.isLiked = !post.isLiked

                // Atualize a aparência do botão com base no estado
                if (post.isLiked) {
                    holder.btnLike.setBackgroundResource(R.drawable.heartblack)
                } else {
                    holder.btnLike.setBackgroundResource(R.drawable.heartwhite)
                }
            }

            // Atualize a aparência do botão com base no estado ao reciclar a célula
            if (post.isLiked) {
                holder.btnLike.setBackgroundResource(R.drawable.heartblack)
            } else {
                holder.btnLike.setBackgroundResource(R.drawable.heartwhite)
            }
    }

    override fun getItemCount(): Int {

        Log.i("Log_Adapter", "Entrou no getItem ${posts.size}")
        return posts.size
    }



}