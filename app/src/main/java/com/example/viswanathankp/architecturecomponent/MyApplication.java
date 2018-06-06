package com.example.viswanathankp.architecturecomponent;

import android.app.Activity;
import android.app.Application;

import com.example.viswanathankp.architecturecomponent.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by viswanathan.kp on 30/12/17.
 * https://github.com/hazems/mvvm-sample-app/blob/part2/app/src/main/java/com/example/test/mvvmsampleapp/view/ui/MainActivity.java
 */

public class MyApplication extends Application implements HasActivityInjector{

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override
  public void onCreate() {
    super.onCreate();
    AppInjector.init(this);
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
