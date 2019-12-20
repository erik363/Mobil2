package com.example.android.navigation;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class PlayerOneWonFragmentDirections {
  private PlayerOneWonFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionGameWonFragmentToGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_gameWonFragment_to_gameFragment);
  }
}
