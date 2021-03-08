package com.example.sensortoolkit_server

import android.content.Context


class Sensors() {
    private lateinit var gyro               :RotationVectorAdapter      // rotation vector
    private lateinit var linearAccelerometer:LinearAccelerometerAdapter // without gravity
    private lateinit var accelerometer      :AccelerometerAdapter       // with gravity
    private lateinit var gAccelerometer     :gAccelerometerAdapter      // just gravity
    private lateinit var rotationVector     :RotationVectorAdapter      // Rotation Vector
    constructor(context: Context):this(){
        gyro = RotationVectorAdapter(context)
        linearAccelerometer = LinearAccelerometerAdapter(context)
        accelerometer = AccelerometerAdapter(context)
        gAccelerometer = gAccelerometerAdapter(context)
        rotationVector = RotationVectorAdapter(context)
    }

    fun readLinearAccelerometer(): AccelerometerContainer {
        return  linearAccelerometer.read()
    }

    fun read():AccelerometerContainer {
        return accelerometer.read()
    }

}
