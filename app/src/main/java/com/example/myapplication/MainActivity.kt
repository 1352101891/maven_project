package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.rong.pushperm.ResultCallback
import io.rong.pushperm.RongPushPremissionsCheckHelper
import io.rong.pushperm.perm.PermissionType
import java.lang.reflect.Constructor

class MyResultCallback():ResultCallback{
    override fun onAreadlyOpened(value: String?) {
        TODO("Not yet implemented")
    }

    override fun onBeforeShowDialog(value: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onGoToSetting(value: String?) {
        TODO("Not yet implemented")
    }

    override fun onFailed(value: String?, type: ResultCallback.FailedType?) {
        TODO("Not yet implemented")
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RongPushPremissionsCheckHelper.checkPermissionsAndShowDialog(
                this
                ,MyResultCallback())
    }


    public suspend fun t(){
        tt()
    }

    public suspend fun tt(){

    }

    public fun test(){
        generateSequence(3){ it + 1}
        val oddNumbers = sequence {
            yield(1)
            yieldAll(listOf(3, 5))
            yieldAll(generateSequence(7) { it + 2 })
        }
        println(oddNumbers.take(3).toList())
    }
}