package com.example.viswanathankp.architecturecomponent.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.viswanathankp.architecturecomponent.AppExecutor;
import com.example.viswanathankp.architecturecomponent.db.AppDatabase;
import com.example.viswanathankp.architecturecomponent.db.DBHelper;
import com.example.viswanathankp.architecturecomponent.service.model.Project;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

@Singleton
public class ProjectRepository {
  private GitHubService gitHubService;
  private AppDatabase mAppDatabase;
  private AppExecutor mAppExecutor;

  @Inject
  public ProjectRepository(GitHubService gitHubService, AppDatabase appDatabase, AppExecutor executor) {
    this.gitHubService = gitHubService;
    mAppDatabase = appDatabase;
    mAppExecutor = executor;
  }

  public LiveData<List<Project>> getProjectList(String userId) {
    final MutableLiveData<List<Project>> data = new MutableLiveData<>();

    getValuesFromDB(data);

    gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
      @Override
      public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
        //simulateDelay();
        //updateToDB(response.body());
        data.setValue(response.body());
      }

      @Override
      public void onFailure(Call<List<Project>> call, Throwable t) {
        // TODO better error handling in part #2 ...
        data.setValue(null);
      }
    });

    return data;
  }

  private void getValuesFromDB(MutableLiveData<List<Project>> data) {
    mAppExecutor.diskIO().execute(new Runnable() {
      @Override
      public void run() {
         data.postValue(DBHelper.getModelProjectList(mAppDatabase.projectDao().getAll()));
      }
    });
  }

  private void updateToDB(List<Project> projects){
    mAppExecutor.diskIO().execute(new Runnable() {
      @Override
      public void run() {
        mAppDatabase.projectDao().insertUsers(DBHelper.getDbProjectList(projects));
      }
    });
  }

  public LiveData<Project> getProjectDetails(String userID, String projectName) {
    final MutableLiveData<Project> data = new MutableLiveData<>();

    gitHubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
      @Override
      public void onResponse(Call<Project> call, Response<Project> response) {
        //simulateDelay();
        data.setValue(response.body());
      }

      @Override
      public void onFailure(Call<Project> call, Throwable t) {
        // TODO better error handling in part #2 ...
        data.setValue(null);
      }
    });

    return data;
  }

  private void simulateDelay() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
