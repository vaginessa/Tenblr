package com.tenblr.bhargav.tenblr.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bhargav on 15/11/16.
 */

    public class PrefUtil {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        public PrefUtil(Context context) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
            editor = settings.edit();
        }

        public void setStringPref(String key, String value) {
            editor.putString(key, value);

        }

        public void setIntPref(String key, int value) {
            editor.putInt(key, value);

        }

        public void setBooleanPref(String key, boolean value) {
            editor.putBoolean(key, value);

        }

        public void setLongPref(String key, Long value) {
            editor.putLong(key, value);

        }

        public void setStringSetPref(String key, Set<String> value) {
            editor.putStringSet(key, value);

        }

        /**
         * @gradle compile 'com.google.code.gson:gson:2.5'
         * @usage uncomment to use this
         * @description used to convert class objects to string
         */
        public void setObjectPref(String key, Object value) {
            Gson gson = new Gson();
            String json = gson.toJson(value);
            editor.putString(key, json);
            commit();
        }

        public String getObjectPref(String key) {
            String json = settings.getString(key, "");
            return json;
        }

        public Set<String> getStringSetPref(String key, HashSet<String> defValue) {
            return settings.getStringSet(key, defValue);
        }

        public String getStringPref(String key, String defValue) {
            return settings.getString(key, defValue);
        }

        public int getIntPref(String key, int defValue) {
            return settings.getInt(key, defValue);
        }

        public boolean getBooleanPref(String key, boolean defValue) {
            return settings.getBoolean(key, defValue);
        }

        public long getLongPref(String key, Long defValue) {
            return settings.getLong(key, defValue);
        }

        public void removePref(String Key) {
            editor.remove(Key);
        }

        public void clearAllSharedPreferences() {
            if (settings != null)
                settings.edit().clear().commit();
        }

        public void commit() {
            editor.commit();
        }

    }

