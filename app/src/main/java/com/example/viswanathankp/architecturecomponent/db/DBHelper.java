package com.example.viswanathankp.architecturecomponent.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viswanathan.kp on 31/12/17.
 */

public class DBHelper {

  public static List<Project> getDbProjectList(List<com.example.viswanathankp.architecturecomponent.service.model.Project> projects){
    List<Project> dbProjects = new ArrayList<>();
    for (com.example.viswanathankp.architecturecomponent.service.model.Project p:projects) {
      dbProjects.add(new Project(p));
    }
    return dbProjects;
  }

  public static List<com.example.viswanathankp.architecturecomponent.service.model.Project> getModelProjectList(List<Project> projects){
    List<com.example.viswanathankp.architecturecomponent.service.model.Project> dbProjects = new ArrayList<>();
    for (Project p:projects) {
      dbProjects.add(new com.example.viswanathankp.architecturecomponent.service.model.Project(p.name,p.language,p.watchers));
    }
    return dbProjects;
  }
}
