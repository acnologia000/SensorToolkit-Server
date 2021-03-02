package com.example.sensortoolkit_server

import android.app.Activity
import android.hardware.*
import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class SensorBunchObject() : Activity(),SensorEventListener{

    private lateinit var Accelerometer: Sensor
    private lateinit var AccelerometerLinear: Sensor
    private lateinit var GyroScope: Sensor
    private lateinit var RotationVector: Sensor
    private lateinit var AccelerometerListener:SensorEventListener
    private lateinit var sm: SensorManager

    constructor(sm: SensorManager) : this() {
        this.Accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY)
        this.AccelerometerLinear = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        this.GyroScope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        this.RotationVector = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        this.sm = sm
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()

    }

    public fun ur(){
        sm.unregisterListener(this)
    }

}
@Keep
@Serializable
data class Accelerometer( val axis: Axis, val Name:String="Accelerometer")


@Keep
@Serializable
data class GyroScope(val axis: Axis, val Name:String="Accelerometer")

@Keep
@Serializable
data class RotationVector(val axis: Axis,val Name:String="Accelerometer")

@Keep
@Serializable
data class Axis(val x:Int, val y:Int, val z:Int)