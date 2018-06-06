package com.example.viswanathankp.architecturecomponent.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.viswanathankp.architecturecomponent.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends LifecycleActivity implements HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          return true;
        case R.id.navigation_dashboard:
          return true;
        case R.id.navigation_notifications:
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    ProjectListFragment fragment = new ProjectListFragment();
    getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
  }

  /** Shows the project detail fragment */
//  public void show(Project project) {
//    ProjectFragment projectFragment = ProjectFragment.forProject(project.name);
//
//    getSupportFragmentManager()
//            .beginTransaction()
//            .addToBackStack("project")
//            .replace(R.id.fragment_container,
//                    projectFragment, null).commit();
//  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }
}
