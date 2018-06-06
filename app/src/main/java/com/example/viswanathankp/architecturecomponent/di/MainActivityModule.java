package com.example.viswanathankp.architecturecomponent.di;

import com.example.viswanathankp.architecturecomponent.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

@Module
public abstract class MainActivityModule {
  @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
  abstract MainActivity contributeMainActivity();
}
