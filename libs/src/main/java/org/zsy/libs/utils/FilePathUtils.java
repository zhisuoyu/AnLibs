package org.zsy.libs.utils;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;

public class FilePathUtils {


    public static File getInFileDir(@NonNull Context context) {
        return context.getFilesDir(); // /data/data/com.belt.wg.myutils/files
    }

    public static File getInCacheDir(@NonNull Context context) {
        return context.getCacheDir();// /data/data/com.belt.wg.myutils/cache
    }

    public static File getExRootDir() {
        return Environment.getExternalStorageDirectory();// /storage/sdcard0
    }

    public static File getExPrivateDir(String type, @NonNull Context context) {
        return context.getExternalFilesDir(type); // /storage/sdcard0/Android/data/com.belt.wg.myutils/files/{type}
    }

    public static File getExPrivateDir(@NonNull Context context) {
        return context.getExternalFilesDir(""); // /storage/sdcard0/Android/data/com.belt.wg.myutils/files/{type}
    }


    public static File getExPublicDir(String type) {
        return Environment.getExternalStoragePublicDirectory(type); // /storage/sdcard0/{type}
    }

    public static File getExPublicDir() {
        return Environment.getExternalStoragePublicDirectory(""); // /storage/sdcard0/{type}
    }

    public static File getExCacheDir(@NonNull Context context) {
        return context.getExternalCacheDir(); ///storage/sdcard0/Android/data/com.belt.wg.myutils/cache
    }


}
