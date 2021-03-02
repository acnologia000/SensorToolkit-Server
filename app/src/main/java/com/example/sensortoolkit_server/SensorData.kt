package com.example.sensortoolkit_server

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class SensorBunchObject{

    private var Accelerometer: Sensor
    private var AccelerometerLinear: Sensor
    private var GyroScope: Sensor
    private var RotationVector: Sensor



    constructor(sm: SensorManager){
        //this.sensor_Manager = ct.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.Accelerometer = sm.getDefaultSensor(Sensor.TYPE_GRAVITY)
        this.AccelerometerLinear = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        this.GyroScope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        this.RotationVector = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    }

    fun rSelf(): String {
        return Json.encodeToString(this)
    }
}
@Keep
@Serializable
data class AllSensorData(val Accelerometer:String)

@Serializable
data class Accelerometer(val x:Int, val y:Int, val z:Int)