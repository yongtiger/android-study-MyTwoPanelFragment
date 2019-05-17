package cc.brainbook.android.study.mytwopanelfragment.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import static cc.brainbook.android.study.mytwopanelfragment.BuildConfig.DEBUG;

public class MyApplication extends Application {
    private static final String TAG = "TAG";

    private static MyApplication mInstance;
    private static Context context;
    public static MyApplication getInstance() {
        return mInstance;
    }
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        if (DEBUG) Log.d(TAG, "MyApplication# onCreate()# ");
        super.onCreate();

        mInstance = this;
        context = getApplicationContext();

        ///[LeakCanary] A memory leak detection library. https://github.com/square/leakcanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            /// 用来进行过滤操作，如果当前的进程是用来给LeakCanary 进行堆分析的则return，否则会执行LeakCanary的install方法。
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

    }

}