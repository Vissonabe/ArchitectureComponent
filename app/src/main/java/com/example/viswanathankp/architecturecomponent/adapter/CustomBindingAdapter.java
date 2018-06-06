package com.example.viswanathankp.architecturecomponent.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by viswanathan.kp on 30/12/17.
 */

public class CustomBindingAdapter {
  @BindingAdapter("visibleGone")
  public static void showHide(View view, boolean show) {
    view.setVisibility(show ? View.VISIBLE : View.GONE);
  }
}