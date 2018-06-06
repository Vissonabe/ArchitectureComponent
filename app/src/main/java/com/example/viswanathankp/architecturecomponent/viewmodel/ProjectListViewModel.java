package com.example.viswanathankp.architecturecomponent.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.viswanathankp.architecturecomponent.db.AppDatabase;
import com.example.viswanathankp.architecturecomponent.service.model.Project;
import com.example.viswanathankp.architecturecomponent.service.model.User;
import com.example.viswanathankp.architecturecomponent.service.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

public class ProjectListViewModel extends ViewModel {

  private MutableLiveData<List<User>> users;
  private final LiveData<List<Project>> projectListObservable;

  @Inject
  ProjectListViewModel(@NonNull ProjectRepository projectRepository){
    projectListObservable = projectRepository.getProjectList("square");
  }

  public LiveData<List<Project>> getProjectListObservable() {
    return projectListObservable;
  }

  @Override
  protected void onCleared() {
    super.onCleared();
  }
}
