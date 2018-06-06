package com.example.viswanathankp.architecturecomponent.di;

import android.app.Application;

import com.example.viswanathankp.architecturecomponent.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class,AppModule.class,MainActivityModule.class})
public interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance Builder application(Application application);
//    @BindsInstance Builder appModule(AppModule appModule);
    AppComponent build();
  }
  void inject(MyApplication mvvmApplication);
}
