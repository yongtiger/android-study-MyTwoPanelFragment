package cc.brainbook.android.study.mytwopanelfragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cc.brainbook.android.study.mytwopanelfragment.util.Util;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListFragment}.
 */
public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    ///[双屏TwoPane]
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ItemDetailActivity# onCreate()# ");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_detail);

        ///给AppCompatActivity的标题栏上加上返回按钮
        ///https://www.jianshu.com/p/3600b2178afa
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ///[双屏TwoPane]判断是否双屏
        mTwoPane = Util.isTwoPanel(this);
        if (mTwoPane) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            final long itemId = getIntent().getLongExtra(ItemDetailFragment.ARG_ITEM_ID, 0L);
            // Make new fragment to show this selection.
            final ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance(itemId);

            // Execute a transaction, replacing any existing fragment
            // with this one inside the frame.
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, itemDetailFragment)
                    .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "ItemDetailActivity# onRestart()# ");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "ItemDetailActivity# onStart()# ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "ItemDetailActivity# onResume()# ");
        super.onResume();
    }

    ///当设置了android:configChanges="smallestScreenSize|screenLayout|orientation|keyboardHidden|screenSize"会调用onConfigurationChanged()
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "ItemDetailActivity# onConfigurationChanged()# ");
        super.onConfigurationChanged(newConfig);

        ///[双屏TwoPane]翻转屏幕后宽度发生变化，需要重新判断是否双屏
        mTwoPane = Util.isTwoPanel(this);
        if (mTwoPane) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "ItemDetailActivity# onPause()# ");
        super.onPause();
    }

    ///当没设置android:configChanges="smallestScreenSize|screenLayout|orientation|keyboardHidden|screenSize"则重启
    // onSaveInstanceState()保存状态，然后在onCreate()中恢复状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "ItemDetailActivity# onSaveInstanceState()# ");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "ItemDetailActivity# onStop()# ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "ItemDetailActivity# onDestroy()# ");
        super.onDestroy();
    }

    ///给AppCompatActivity的标题栏上加上返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
