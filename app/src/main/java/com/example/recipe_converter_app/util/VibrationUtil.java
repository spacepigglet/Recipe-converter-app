package com.example.recipe_converter_app.util;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
public class VibrationUtil {
    public static void tick(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android Quince Tart (API 29) and above, use VibrationEffect
                vibrator.vibrate(android.os.VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK));
            } else {
                // For older devices, use the vibrate method
                vibrator.vibrate(50);
            }
        }
    }
}
