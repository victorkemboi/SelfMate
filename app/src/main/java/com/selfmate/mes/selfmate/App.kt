package com.selfmate.mes.selfmate

import android.app.Application
import com.facebook.stetho.Stetho
import com.selfmate.mes.selfmate.di.AppComponent
import com.selfmate.mes.selfmate.di.AppModule
import com.selfmate.mes.selfmate.di.DaggerAppComponent
import com.selfmate.mes.selfmate.di.RoomModule

/**
 * Created by vicki_mes on 11/15/2017.
 */


class App : Application() {

    lateinit var daggerAppComponent: AppComponent

    override fun onCreate() {

        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this)

        daggerAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule(this))
                .build()

    }

    companion object {
        lateinit var INSTANCE: App
    }
}