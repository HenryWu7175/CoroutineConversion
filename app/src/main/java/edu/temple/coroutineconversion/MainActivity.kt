package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }

    //this
//    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
//
//        currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", it.what)
//        cakeImageView.alpha = it.what / 100f
//        true
//    })


    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //while this handler provide the functionality of the assiment just fine,
        // the couroutine is more efficient on the long term.
//        findViewById<Button>(R.id.revealButton).setOnClickListener{
//            Thread{
//                repeat(100) {
//                    handler.sendEmptyMessage(it)
//                    Thread.sleep(40)
//                }
//            }.start()
//        }

        findViewById<Button>(R.id.revealButton).setOnClickListener {
            //launch coroutine
            coroutineScope.launch {
                //set for loop to repeat 100 times
                repeat(100) { opacity -> //can use it if you like
                    //set text to current opacity and update consistently with coroutine opacity
                    currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", opacity)
                    //set image alpha to current opacity
                    cakeImageView.alpha = opacity / 100f
                    //same as sleep but for coroutines
                    delay(40)
                }
            }
        }
    }
}