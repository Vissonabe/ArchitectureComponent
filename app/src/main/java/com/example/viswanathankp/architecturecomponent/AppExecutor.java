package com.example.viswanathankp.architecturecomponent;

import java.util.concurrent.Executor;

/**
 * Created by viswanathan.kp on 31/12/17.
 */

public class AppExecutor {

  private final Executor mDiskIO;

  public AppExecutor(Executor diskIO){
    mDiskIO = diskIO;
  }

  public Executor diskIO() {
    return mDiskIO;
  }
}
