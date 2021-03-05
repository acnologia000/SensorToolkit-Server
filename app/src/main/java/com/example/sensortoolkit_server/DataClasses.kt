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
data class Accelerometer( val axis: ThreeAxisWithAccuracy, val Name:String="Accelerometer")

@Keep
@Serializable
data class GyroScopeContainer(val axis: ThreeAxisWithAccuracy, val Name:String="Accelerometer")

@Keep
@Serializable
data class RotationVector(val axis: ThreeAxisWithAccuracy,val Name:String="Accelerometer")