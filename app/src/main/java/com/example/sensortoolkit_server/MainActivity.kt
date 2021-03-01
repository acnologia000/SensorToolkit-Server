package com.example.sensortoolkit_server


import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sensortoolkit_server.databinding.ActivityMainBinding
import java.net.Inet4Address
import java.net.ServerSocket
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

const val response  = "HTTP/1.1 200 OK\r\n"+"Content-Type: text/html\r\n\n"+"""<html> <body> <h1>Hello   World!</h1> </body> </html>"""

const val InternalServerError = "HTTP/1.1 500 Internal Server Error\r\n"

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
            thread {
                Log.e("start","server")
                server(switch)
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
    private fun server(switchStatus:AtomicBoolean){
        Log.e("dbg","inside sever function and proceeding to bind")
        val server = ServerSocket(4000,1000, Inet4Address.getByName("0.0.0.0"))
        println("listening to socket")

        runOnUiThread{binding.tvmain.text = "listening to socket"}
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

            if (!(req.size>=3)){ outputStream.write("".toByteArray()) ; continue  }




            Log.e("writing","writing the output")
            outputStream.write(response.toByteArray())

            Log.e("closing","closing output stream")
            outputStream.flush()
            outputStream.close()


            Log.e("closing","closing socket")
            socket.close()

        }
    }
}

class SensorData{
    private lateinit var Accelerometer:Sensor
    private lateinit var AccelerometerLinear:Sensor
    private lateinit var GyroScope:Sensor

    private lateinit var AccelerometerManager:SensorManager
    private lateinit var AccelerometerLinearManager:SensorManager
    private lateinit var GyroscopeManager:SensorManager


}

