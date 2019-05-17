package cc.brainbook.android.study.mytwopanelfragment;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    private ItemListFragment mItemListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MainActivity# onCreate()# ");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ///避免Fragment重叠问题
        if (savedInstanceState == null) {
            mItemListFragment = ItemListFragment.newInstance("param1", "param2");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, mItemListFragment)
                    ///只能在 Activity 保存其状态（用户离开 Activity）之前使用 commit() 提交事务。
                    // 如果您试图在该时间点后提交，则会引发异常。
                    // 这是因为如需恢复 Activity，则提交后的状态可能会丢失。
                    // 对于丢失提交无关紧要的情况，使用 commitAllowingStateLoss()
//                .commitAllowingStateLoss();
                    .commit();
        } else {
            mItemListFragment = (ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        }

    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "MainActivity# onRestart()# ");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "MainActivity# onStart()# ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "MainActivity# onResume()# ");
        super.onResume();
    }

    ///当设置了android:configChanges="smallestScreenSize|screenLayout|orientation|keyboardHidden|screenSize"会调用onConfigurationChanged()
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "MainActivity# onConfigurationChanged()# ");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "MainActivity# onPause()# ");
        super.onPause();
    }

    ///当没设置android:configChanges="smallestScreenSize|screenLayout|orientation|keyboardHidden|screenSize"则重启
    // onSaveInstanceState()保存状态，然后在onCreate()中恢复状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "MainActivity# onSaveInstanceState()# ");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "MainActivity# onStop()# ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "MainActivity# onDestroy()# ");
        super.onDestroy();
    }

}
