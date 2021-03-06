package com.example.sensortoolkit_server

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sensortoolkit_server.databinding.ActivityMainBinding
import java.net.Inet4Address
import java.net.ServerSocket
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

//TODO: delete default response and replace with sending data of standard sensors
//TODO: remove all logging actions

// Project status as of now ---

/* * * *
 * Sensors Done are as follows with files
 * #LinearAccelerometer ( accelerometer what people usually expect
 * #Accelerometer ( accelerometer data with gravitation constant with whatever axis experiencing it )
 * #Rotation Vector ( device orientation in 3D space, yet to make sense of it )( gyroscope in short )
 * #gAccelerometer ( acceleration of gravitational constant reading in different axis of the device )
 *
 * Features remaining :
 * GPS
 * SMS sending
 * Notification Sending
 */

val response  = ("HTTP/1.1 200 OK\r\n"+"Content-Type: text/html\r\n\n"+"""<html> <body> <h1>Hello   World!</h1> </body> </html>""").toByteArray()
//val InternalServerError = ("HTTP/1.1 500 Internal Server Error\r\n" + "Content-Type: text/html\r\n\n"+"""<html> <body> <h1>Something went wrong</h1> </body> </html>""").toByteArray()
val BadRequestErr = ("HTTP/1.1 400 Bad Request\r\n"+"Content-Type: text/html\r\n\n"+"""<html> <body> <h1>Bad Request</h1> </body> </html>""").toByteArray()
val NotFoundErr = ("HTTP/1.1 404 NOT FOUND\r\n"+"Content-Type: text/html\r\n\n"+"""<html> <body> <h1>Bad Request</h1> </body> </html>""").toByteArray()

class MainActivity : AppCompatActivity() {

    private var switch : AtomicBoolean = AtomicBoolean(false)

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStart.setOnClickListener {

            switch.set(true)
            Log.e("dbg","button click received")

            val handle = Sensors(this)
            thread {
                Log.e("start","server")
                server(switch,handle)
            }

            binding.btStart.isEnabled = false
            binding.buttonStop.isEnabled = true
        }

        binding.buttonStop.setOnClickListener {
            switch.set(false)
            binding.btStart.isEnabled = true
            binding.buttonStop.isEnabled = false
        }
    }
    private fun server(switchStatus:AtomicBoolean,handle:Sensors){
        Log.e("dbg","inside sever function and proceeding to bind")
        val server = ServerSocket(4000,1000, Inet4Address.getByName("0.0.0.0"))
        println("listening to socket")

        runOnUiThread{binding.tvmain.text = getString(R.string.running)}

        while(true){
            Log.e("dbg","waiting to accept connection")

            if (switchStatus.equals(false)) {break}
            val socket = server.accept()
            val inputStream = socket.getInputStream()

            Log.e("reading","Getting the reader")
            //val reader = inputStream.reader()

            Log.e("reading","reading all data as text")
            // code stuck here

            val req = Scanner(inputStream).nextLine().split(" ").toTypedArray()

            // Log.e("output",data.toArray().toString())
            val outputStream = socket.getOutputStream()

            // early return in case of bad http request
            if (req.size!=3){ outputStream.write(BadRequestErr) ; continue  }

            // dispatching requests to mapped routes
            // req[1] being the middle of the top line
            // of http request that contains requested URI
            // each string pattern/route corresponds to
            // designated sensor by name and "/" or home responds
            // to collection of data by all the standard sensors
            // TODO: Impliment full routing system with raw + json outputs
            when(req[1]){
                "/"-> {} // respond with data of all the sensors
                "/<name of sensor>" -> print("hello") // something like Outputstream.write(<sensor data json>)
                else -> outputStream.write(NotFoundErr)
            }



            Log.e("writing","writing the output")
            outputStream.write(response)

            Log.e("closing","closing output stream")
            outputStream.flush()
            outputStream.close()


            Log.e("closing","closing socket")
            socket.close()

        }
    }
}



