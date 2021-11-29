package com.rickirick.catchme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Runnable
import java.util.*

class Main2 : AppCompatActivity() {
    var number = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)

        hideImages()


        object : CountDownTimer(15200,1000) {
            override fun onTick(p0: Long) {
                timeText.text = "Time: ${p0/1000}"

            }

            override fun onFinish() {
                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@Main2)
                alert.setTitle("Game Over")
                alert.setMessage("Do you want to try again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                   var intent = Intent(applicationContext,Main2::class.java)
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()

    }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(11)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 400)
            }

        }
        handler.post(runnable)

    }

    fun twoZero(view: View){

        number = number + 1
        textView3.text = "Score: ${number}"
    }


}