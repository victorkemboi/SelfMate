package com.selfmate.mes.selfmate.ui.repo;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.selfmate.mes.selfmate.models.MoodLog;
import com.selfmate.mes.selfmate.models.Question;
import com.selfmate.mes.selfmate.models.Results;
import com.selfmate.mes.selfmate.si.GAME_TYPE;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by vicki_mes on 11/18/2017.
 */

public class MateViewModel extends ViewModel {


    public int listIndexCounter = 0;
    public int currentListSize = 0;
    public int gameType = GAME_TYPE.INSTANCE.getINIT();
    private   Question questionOne;
    private LiveData<Results> resultsLiveData ;
    private LiveData<Question> randomQuestionLiveData ;
    private LiveData<List<Question>> HotQuizList;
    private LinkedList<Question> HotQuizStack ;
    private  QuestionRepository questionRepo;
    private MoodLogRepository moodLogRepository;
    private ResultsRepository resultsRepository;

   @Inject
   public MateViewModel(QuestionRepository questionRepom, MoodLogRepository moodLogRepositorym, ResultsRepository resultsRepositorym) {
        this.questionRepo = questionRepom;
       this.moodLogRepository = moodLogRepositorym;
       this.resultsRepository = resultsRepositorym;

    }

    public void initQuestionOne(Question question) {
       /* if (questionsList != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }*/
        Random rand = new Random();
        int n = rand.nextInt(20) + 1;
        //questionOne = questionRepo.findById(n);
      //  Log.d("questionOne",String.valueOf(n));

        questionOne =question;

    }



    public Question getQuestionOne() {
        return questionOne;
    }

    public void addMoodLog(MoodLog moodLog) {
        // executor.execute(() -> {moodLogRepository.insert(moodLog);});

        moodLogRepository.insert(moodLog);
    }

    public LiveData<Results> getResultsLiveData() {
        return resultsLiveData;
    }

    public void setResultsLiveData() {


      resultsLiveData = resultsRepository.getResults();

        Log.d("Has obs",  ""+resultsLiveData.hasActiveObservers());



      if(resultsLiveData.getValue()==null){
          Log.d("Check Live Data mp Null", "It is Null." );

      }else {
          Log.d("Check Live Data mp Null", resultsLiveData.getValue().toString() );

      }

    }
    public void updateResults(Results results){

        // executor.execute(() -> resultsRepository.updateResults(results));

        //this.resultsLiveDataresult);
        resultsRepository.updateResults(results);


   }

   public void populateHotQuizList(){
       HotQuizList = questionRepo.setupHot10();



   }
   public void initHotQuizStack(List<Question> questionListm){
       HotQuizStack = new LinkedList<>(questionListm);
   }

    public LiveData<List<Question>> getHotQuizList() {
        return HotQuizList;
    }



    public LinkedList<Question> getHotQuizStack() {
        return HotQuizStack;
    }

    public LiveData<Question> getRandomQuestionLiveData() {
        return randomQuestionLiveData;
    }

    public void setRandomQuestionLiveData() {
       this.randomQuestionLiveData =questionRepo.getRandom();
    }
}
