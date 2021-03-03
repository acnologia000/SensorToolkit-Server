package com.example.sensortoolkit_server

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

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