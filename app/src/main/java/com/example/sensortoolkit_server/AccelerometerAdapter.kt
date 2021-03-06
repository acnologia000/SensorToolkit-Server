package com.example.sensortoolkit_server

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast

// Acceleration (including gravity)

class AccelerometerAdapter():SensorEventListener {
    lateinit var sensorManager: SensorManager
    private var sensor: Sensor?=null
    private var tost = { _:String->}
    var data :FloatArray = FloatArray(3)
    private var accuracy = 0
    constructor(ctx: Context) : this() {
        sensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
        tost = {input:String -> Toast.makeText( ctx, input, Toast.LENGTH_SHORT).show()}
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 !=  null && p0.values.size != 3 ) {
            data = p0.values
            p0.accuracy
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        accuracy = p1
        tost.invoke("sensor accuracy changed \n new value is $p1 ")
    }

    fun readRaw():FloatArray = data

    fun read(): AccelerometerContainer {
        return AccelerometerContainer(ThreeAxisWithAccuracy(Axis(data[0],data[1],data[2]),accuracy))
    }


    fun kill() {return sensorManager.unregisterListener(this)}
}

