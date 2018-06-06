package com.example.viswanathankp.architecturecomponent.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viswanathankp.architecturecomponent.R;
import com.example.viswanathankp.architecturecomponent.adapter.ProjectAdapter;
import com.example.viswanathankp.architecturecomponent.databinding.FragmentProjectListBinding;
import com.example.viswanathankp.architecturecomponent.di.Injectable;
import com.example.viswanathankp.architecturecomponent.service.model.Project;
import com.example.viswanathankp.architecturecomponent.viewmodel.ProjectListViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends LifecycleFragment implements Injectable {

  public static final String TAG = "ProjectListFragment";

  private ProjectAdapter projectAdapter;
  private FragmentProjectListBinding binding;

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  public ProjectListFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);

    projectAdapter = new ProjectAdapter();
    binding.projectList.setAdapter(projectAdapter);
    binding.setIsLoading(true);

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    final ProjectListViewModel viewModel = ViewModelProviders.of(this,
            viewModelFactory).get(ProjectListViewModel.class);

    observeViewModel(viewModel);
  }

  private void observeViewModel(ProjectListViewModel viewModel) {
    // Update the list when the data changes
    viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
      @Override
      public void onChanged(@Nullable List<Project> projects) {
        if (projects != null) {
          binding.setIsLoading(false);
          projectAdapter.setProjectList(projects);
        }
      }
    });
  }
}
