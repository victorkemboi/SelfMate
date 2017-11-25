package com.selfmate.mes.selfmate.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application = mApplication
}
