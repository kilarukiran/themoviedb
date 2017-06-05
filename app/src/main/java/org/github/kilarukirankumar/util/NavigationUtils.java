package org.github.kilarukirankumar.util;

import android.content.Context;
import android.content.Intent;

import org.github.kilarukirankumar.model.videos.MovieModel;
import org.github.kilarukirankumar.view.activity.MovieDetailsActivity;

/**
 * Navigate among different activities.
 */

public class NavigationUtils {

    private NavigationUtils() {

    }

    public static void navigateToMovieDetails(Context context, MovieModel movieModel) {
        if (context != null) {
            Intent intentToLaunch = MovieDetailsActivity.getCallingIntent(context, movieModel);
            context.startActivity(intentToLaunch);
        }
    }
}
