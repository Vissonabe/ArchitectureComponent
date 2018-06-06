package com.example.viswanathankp.architecturecomponent.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.viswanathankp.architecturecomponent.AppExecutor;
import com.example.viswanathankp.architecturecomponent.db.AppDatabase;
import com.example.viswanathankp.architecturecomponent.service.repository.GitHubService;
import com.example.viswanathankp.architecturecomponent.viewmodel.ProjectViewModelFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {
  
  @Singleton @Provides
  AppExecutor provideAppExecutor(){
    return new AppExecutor(Executors.newSingleThreadExecutor());
  }

  @Singleton @Provides
  AppDatabase provideAppDatabase(Application appContext){
    return Room.databaseBuilder(appContext,
            AppDatabase.class, "github_repos_db").build();
  }

  @Singleton @Provides
  HttpLoggingInterceptor provideHttpInterceptor() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    return logging;
  }

  @Singleton @Provides
  Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }

  @Singleton @Provides
  OkHttpClient provideOkHttp(HttpLoggingInterceptor logging) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(logging);
    return httpClient.build();
  }

  @Singleton @Provides
  GitHubService provideGithubService(Gson gson, OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
            .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(GitHubService.class);
  }

  @Singleton
  @Provides
  ViewModelProvider.Factory provideViewModelFactory(
          ViewModelSubComponent.Builder viewModelSubComponent) {

    return new ProjectViewModelFactory(viewModelSubComponent.build());
  }
}
