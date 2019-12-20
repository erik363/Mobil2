package com.example.android.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class GameFragmentDirections {
  private GameFragmentDirections() {
  }

  @NonNull
  public static ActionGameFragmentToGameWonFragment actionGameFragmentToGameWonFragment() {
    return new ActionGameFragmentToGameWonFragment();
  }

  public static class ActionGameFragmentToGameWonFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionGameFragmentToGameWonFragment() {
    }

    @NonNull
    public ActionGameFragmentToGameWonFragment setScore(int score) {
      this.arguments.put("score", score);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("score")) {
        int score = (int) arguments.get("score");
        __result.putInt("score", score);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_gameFragment_to_gameWonFragment;
    }

    @SuppressWarnings("unchecked")
    public int getScore() {
      return (int) arguments.get("score");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionGameFragmentToGameWonFragment that = (ActionGameFragmentToGameWonFragment) object;
      if (arguments.containsKey("score") != that.arguments.containsKey("score")) {
        return false;
      }
      if (getScore() != that.getScore()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getScore();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionGameFragmentToGameWonFragment(actionId=" + getActionId() + "){"
          + "score=" + getScore()
          + "}";
    }
  }
}
