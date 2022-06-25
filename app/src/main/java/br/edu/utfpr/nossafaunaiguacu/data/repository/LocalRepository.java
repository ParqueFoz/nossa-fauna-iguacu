package br.edu.utfpr.nossafaunaiguacu.data.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class LocalRepository {
    private static SharedPreferences sharedPreferences = null;

    public static void init(Application application) {
        sharedPreferences = application.getSharedPreferences("application", Context.MODE_PRIVATE);
    }

    public static Boolean isFirstAccess() {
        return sharedPreferences.getBoolean("FIRST_ACCESS", true);
    }

    public static void saveNotFirstAccess() {
        sharedPreferences.edit()
                .putBoolean("FIRST_ACCESS", false)
                .apply();
    }

    public static Boolean isFavorite(Integer id) {
        return sharedPreferences.getBoolean("FAVORITE_" + id, false);
    }

    public static void saveFavorite(Integer id, Boolean favorite) {
        sharedPreferences.edit()
                .putBoolean("FAVORITE_" + id, favorite)
                .commit();
    }

    public static Boolean isKnown(Integer id) {
        return sharedPreferences.getBoolean("KNOWN_" + id, false);
    }

    public static void discoveredAnimal(Integer id) {
        sharedPreferences.edit()
                .putBoolean("KNOWN_" + id, true)
                .commit();
    }
}
