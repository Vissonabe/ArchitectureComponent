package com.example.viswanathankp.architecturecomponent.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by viswanathan.kp on 31/12/17.
 */

@Database(entities = {Project.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract ProjectDao projectDao();
}
