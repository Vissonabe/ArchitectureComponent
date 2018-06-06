package com.example.viswanathankp.architecturecomponent.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.viswanathankp.architecturecomponent.service.model.User;

import java.util.Date;

/**
 * Created by viswanathan.kp on 31/12/17.
 */

@Entity
public class Project {
  @PrimaryKey
  public long id;
  @ColumnInfo(name = "name")
  public String name;
  public String language;
  public int watchers;

  @Ignore
  public Project(com.example.viswanathankp.architecturecomponent.service.model.Project project){
    id = project.id;
    name = project.name;
    language = project.language;
    watchers = project.watchers;
  }

//  public Project(long id,String name,String language,int watchers){
//    this.id = id;
//    this.name = name;
//    this.language = language;
//    this.watchers = watchers;
//  }

  public Project(){
  }
}
