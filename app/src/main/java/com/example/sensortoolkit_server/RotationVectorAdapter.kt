package com.example.sensortoolkit_server


import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast

// returning Rotation Vector

// copy pasted from docs

/*
* The rotation vector represents the orientation of the device as a combination of an angle and an axis,
* in which the device has rotated through an angle θ around an axis (x, y, or z).
* Where the magnitude of the rotation vector is equal to sin(θ/2),
* and the direction of the rotation vector is equal to the direction of the axis of rotation.
* The three elements of the rotation vector are equal to the last three components of a unit quaternion (cos(θ/2), x*sin(θ/2), y*sin(θ/2), z*sin(θ/2)). Elements of the rotation vector are unitless. The x, y, and z axes are defined in the same way as the acceleration sensor. The reference coordinate system is defined as a direct orthonormal basis (see figure 1).
* This coordinate system has the following characteristics:
* X is defined as the vector product Y x Z. It is tangential to the ground at the device's current location and points approximately East.
* Y is tangential to the ground at the device's current location and points toward the geomagnetic North Pole.
* Z points toward the sky and is perpendicular to the ground plane.
*/

class RotationVectorAdapter():Activity(),SensorEventListener {
    private lateinit var sensorManager: SensorManager

    private var sensor:Sensor?=null // Rotation Vector
    private var accuracy = 0
    private var tost = { _:String->}
    private var data :FloatArray = FloatArray(3)

    constructor(ctx:Context) : this() {
        sensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
        tost = {input:String -> Toast.makeText( ctx, input, Toast.LENGTH_SHORT).show()}
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 !=  null && p0.values.size != 3 ) {
            data = p0.values
            accuracy = p0.accuracy
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        accuracy = p1
        tost.invoke("sensor accuracy changed \n new value is $p1 ")
    }


    fun readRaw():FloatArray = data

    fun read(): RotationVectorContainer {
        return RotationVectorContainer(ThreeAxisWithAccuracy(Axis(data[0],data[1],data[2]),accuracy))
    }

    fun kill() {return sensorManager.unregisterListener(this)}
}

