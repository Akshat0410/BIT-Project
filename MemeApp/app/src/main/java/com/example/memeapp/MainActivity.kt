package com.example.memeapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var closeButton: Button? = null
    var builder: AlertDialog.Builder? = null
    var currentImageUrl:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
                  BackButton    }


    private fun loadmeme(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)  //Passing the context that we pass this
        val url = "https://meme-api.herokuapp.com/gimme"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                currentImageUrl = response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Progress.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                       Progress.visibility=View.GONE
                        return false
                    }

                }).into(MemeImageView)

            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)



    }

    fun sharememe(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Check out this meme from Reddit $currentImageUrl")
        val chooser=Intent.createChooser(intent,"Share this meme through ")
        startActivity(chooser)



    }
    fun nextmeme(view: View) {

          loadmeme()
    }

    fun GoingBack(view: View) {
        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title)
        builder.setMessage("Do you want to close this application ?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                    Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                        Toast.LENGTH_SHORT).show();
                }

            }
}
