package com.csg.warrior;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showPrompt(Context context, String message) {
        showPrompt(context, message, Toast.LENGTH_SHORT);
    }

    public static void showPrompt(Context context, String message, int duration) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }
    }
}
