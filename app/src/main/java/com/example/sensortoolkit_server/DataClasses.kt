package com.example.sensortoolkit_server

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Axis(val x:Float, val y:Float, val z:Float)

@Keep
@Serializable
data class ThreeAxisWithAccuracy(var Data:Axis,var Accuracy:Int)

@Keep
@Serializable
data class AccelerometerContainer( val axis: ThreeAxisWithAccuracy, val Name:String="Accelerometer")

@Keep
@Serializable
data class GyroScopeContainer(val axis: ThreeAxisWithAccuracy, val Name:String="Gyroscope")

@Keep
@Serializable
data class RotationVectorContainer(val axis: ThreeAxisWithAccuracy,val Name:String="RotationVector")

@Keep
@Serializable
data class ProximitySensorContainer(var Distance:FloatArray,val Name: String="proximitySensor") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProximitySensorContainer

        if (!Distance.contentEquals(other.Distance)) return false

        return true
    }

    override fun hashCode(): Int {
        return Distance.contentHashCode()
    }
}

@Keep
@Serializable
data class AllSensors(
        val LinearAccelerometer:AccelerometerContainer,
        val AcceleratorMeter:AccelerometerContainer,
        val gAccelerometer:AccelerometerContainer,
        val RotationVector:RotationVectorContainer,
        val proximitySensor: ProximitySensorContainer,
)