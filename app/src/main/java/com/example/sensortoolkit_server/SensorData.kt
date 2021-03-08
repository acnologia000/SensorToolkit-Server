package com.example.sensortoolkit_server

import android.app.Activity
import android.content.Context
import android.hardware.*
import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class SensorBunchObject() {
    private lateinit var gyro:RotationVectorAdapter

    constructor(context: Context):this(){
        gyro = RotationVectorAdapter(context)
    }
}
