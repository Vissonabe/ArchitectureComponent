package com.example.viswanathankp.architecturecomponent.di;

import com.example.viswanathankp.architecturecomponent.viewmodel.ProjectListViewModel;

import dagger.Subcomponent;

/**
 * Created by viswanathan.kp on 30/12/17.
 */
@Subcomponent
public interface ViewModelSubComponent {
  @Subcomponent.Builder
  interface Builder {
    ViewModelSubComponent build();
  }

  ProjectListViewModel projectListViewModel();
}
