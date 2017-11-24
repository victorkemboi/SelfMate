package com.selfmate.mes.selfmate.di;

import android.app.Application;

import com.selfmate.mes.selfmate.Models.SelfMateDatabase;
import com.selfmate.mes.selfmate.ui.Home;
import com.selfmate.mes.selfmate.ui.repo.MateViewModel;
import com.selfmate.mes.selfmate.ui.repo.MoodLogRepository;
import com.selfmate.mes.selfmate.ui.repo.QuestionRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(Home mainActivity);

   // QuestionDao questionDao();

    SelfMateDatabase selfMateDatabase();

    QuestionRepository questionRepository();

    MoodLogRepository moodLogRepository();

    MateViewModel mateViewModel();

    Application application();

}