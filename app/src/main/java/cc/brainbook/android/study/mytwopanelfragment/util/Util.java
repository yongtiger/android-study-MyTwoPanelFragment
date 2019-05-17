package cc.brainbook.android.study.mytwopanelfragment.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;

import cc.brainbook.android.study.mytwopanelfragment.config.Config;

public class Util {

    public static boolean isTwoPanel(@NonNull Context context) {
        return getDisplayWidths(context) >= Config.MIN_WIDTH_TWO_PANEL;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(@NonNull Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getDisplayWidthPixels(@NonNull Context context) {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getDisplayHeightPixels(@NonNull Context context) {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    public static int getDisplayWidths(@NonNull Context context) {
        final int widthPixels = getDisplayWidthPixels(context);
        return px2dip(context, widthPixels);
    }
    public static int getDisplayHeight(@NonNull Context context) {
        final int heightPixels = getDisplayWidthPixels(context);
        return px2dip(context, heightPixels);
    }

    /**
     * 获取设备屏幕的宽
     * @param context
     * @return
     */
    public static int getDeviceWidth(Activity context){
        Display display = context.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.x;
    }

    /**获取屏幕的高*/
    public static int getDeviceHeight(Activity context){
        Display display = context.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.y;
    }

    /**
     * 获取屏幕宽高，单位px
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return new Point(screenWidth, screenHeight);
    }

}
