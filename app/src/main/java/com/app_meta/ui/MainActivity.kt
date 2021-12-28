package com.app_meta.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.app_meta.R
import com.app_meta.network.cache.CacheLocalDisk

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CacheLocalDisk.initDatabase(this)
    }
}
