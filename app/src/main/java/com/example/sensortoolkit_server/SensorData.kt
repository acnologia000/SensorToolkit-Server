package com.example.sensortoolkit_server

import android.hardware.Sensor
import android.hardware.SensorManager

class SensorData{

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
}