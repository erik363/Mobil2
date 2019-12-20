package com.example.android.navigation;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class TitleFragmentDirections {
  private TitleFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionTitleFragmentToGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_titleFragment_to_gameFragment);
  }

  @NonNull
  public static NavDirections actionTitleFragmentToEredmenyekFragment() {
    return new ActionOnlyNavDirections(R.id.action_titleFragment_to_eredmenyekFragment);
  }

  @NonNull
  public static NavDirections actionTitleFragmentToWebFragment() {
    return new ActionOnlyNavDirections(R.id.action_titleFragment_to_webFragment);
  }
}
