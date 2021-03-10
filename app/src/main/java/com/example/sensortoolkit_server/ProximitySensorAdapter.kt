package com.example.sensortoolkit_server

import android.app.Activity
import android.app.Service
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ProximitySensorAdapter():Activity(),SensorEventListener {

    private var data:FloatArray = FloatArray(3,)
    private var accuracy:Int = 0
    private var sensor:Sensor? = null
    private lateinit var sensorManager:SensorManager
    constructor(ctx: Context):this(){
        sensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onSensorChanged(p0: SensorEvent?) {if (p0 != null) { data = p0.values }}


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) { accuracy = p1 }

}