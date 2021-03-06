package com.appvisor_event.master;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.UUID;

public class AppUUID {
    private static String uuid = null;
    private static final String UUID_KEY = "UUID_KEY";

    public static String get(Context context) {
        if (uuid != null) {// 既にapp内からinvokeされている場合
            return uuid;
        }

        SharedPreferences sharedPreferences = context.getSharedPreferences(UUID_KEY, Context.MODE_PRIVATE);
        uuid = sharedPreferences.getString(UUID_KEY, null);
        if (uuid == null) {// 何も設定されていない場合
            uuid = UUID.randomUUID().toString();// randomな文字列を生成
            Editor editor = sharedPreferences.edit();
            editor.putString(UUID_KEY, uuid);
            editor.commit();// 保存
        }
        return uuid;
    }
}