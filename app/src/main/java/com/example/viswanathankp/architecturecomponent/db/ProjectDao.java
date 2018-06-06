package com.example.viswanathankp.architecturecomponent.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by viswanathan.kp on 31/12/17.
 */

@Dao
public interface ProjectDao {
  @Query("SELECT * FROM Project")
  List<Project> getAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUsers(List<Project> projects);
}
