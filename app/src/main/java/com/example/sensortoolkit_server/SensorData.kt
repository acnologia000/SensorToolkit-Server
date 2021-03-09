package com.example.sensortoolkit_server

import android.content.Context


class Sensors() {

    private lateinit var linearAccelerometer:LinearAccelerometerAdapter // without gravity
    private lateinit var accelerometer      :AccelerometerAdapter       // with gravity
    private lateinit var gAccelerometer     :gAccelerometerAdapter      // just gravity
    private lateinit var rotationVector     :RotationVectorAdapter      // Rotation Vector
    constructor(context: Context):this(){

        linearAccelerometer = LinearAccelerometerAdapter(context)
        accelerometer = AccelerometerAdapter(context)
        gAccelerometer = gAccelerometerAdapter(context)
        rotationVector = RotationVectorAdapter(context)
    }

    // read as data Class Object for easy JSON serialising
    fun readLinearAccelerometer(): AccelerometerContainer { return linearAccelerometer.read() }
    fun readAccelerometer():AccelerometerContainer { return accelerometer.read() }
    fun readGAccelerometer():AccelerometerContainer { return gAccelerometer.read() }
    fun readRotationVector(): RotationVectorContainer { return rotationVector.read() }

    // read as raw Float Arrays to process it faster in micro controller
    fun readLinearAccelerometerRaw(): FloatArray { return linearAccelerometer.readRaw() }
    fun readAccelerometerRaw():FloatArray { return accelerometer.readRaw() }
    fun readGAccelerometerRaw():FloatArray { return gAccelerometer.readRaw() }
    fun readRotationVectorRaw():FloatArray { return rotationVector.readRaw() }

}
