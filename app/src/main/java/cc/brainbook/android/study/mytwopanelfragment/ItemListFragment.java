package cc.brainbook.android.study.mytwopanelfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.brainbook.android.study.mytwopanelfragment.dummy.DummyContent;
import cc.brainbook.android.study.mytwopanelfragment.util.Util;

public class ItemListFragment extends Fragment {
    public static final String TAG = "TAG";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ///尽量避免使用getActivity()
    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    int mCurCheckPosition = 5;

    ///[双屏TwoPane]
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemListFragment newInstance(String param1, String param2) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "ItemListFragment# onAttach()# ");
        super.onAttach(context);

        ///尽量避免使用getActivity()
        mContext = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "ItemListFragment# onCreate()# ");
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        ///[双屏TwoPane]尽量避免使用getActivity()
        mTwoPane = Util.isTwoPanel(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "ItemListFragment# onCreateView()# ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "ItemListFragment# onViewCreated()# ");
        super.onViewCreated(view, savedInstanceState);

        ///[RecyclerView]
        mRecyclerView = view.findViewById(R.id.item_list);
        ///初始化RecyclerView
        initRecyclerView();

        ///[双屏TwoPane]
        if (mTwoPane) {
            // Make sure our UI is in the correct state.
            showDetails(mRecyclerViewAdapter.getItemId(mCurCheckPosition));
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "ItemListFragment# onActivityCreated()# ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "ItemListFragment# onStart()# ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "ItemListFragment# onResume()# ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "ItemListFragment# onPause()# ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "ItemListFragment# onStop()# ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "ItemListFragment# onDestroyView()# ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "ItemListFragment# onDestroy()# ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "ItemListFragment# onDetach()# ");
        super.onDetach();

        ///尽量避免使用getActivity()
        mContext = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "ItemListFragment# onSaveInstanceState()# ");
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        ///[RecyclerView]
        mRecyclerViewAdapter = new RecyclerViewAdapter(DummyContent.ITEM_MAP);

        ///[RecyclerView#OnItemClickListener]
        ///https://hackernoon.com/android-recyclerview-onitemclicklistener-getadapterposition-a-better-way-3c789baab4db
        mRecyclerViewAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();

                mCurCheckPosition = viewHolder.getAdapterPosition();    ///注意：这里用getAdapterPosition()，而不用getLayoutPosition()

                ///[RecyclerView#ScrollToPosition]非首次创建时滚动定位position在第一个可见项之后、最后一个可见项之前，滚动无效！
                ///https://www.jb51.net/article/110542.htm
//                mRecyclerView.scrollToPosition(mCurCheckPosition);

                ///[RecyclerView#ScrollToPosition置顶]
                ///https://stackoverflow.com/questions/26875061/scroll-recyclerview-to-show-selected-item-on-top
                final LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                if (llm != null) {
                    llm.scrollToPositionWithOffset(mCurCheckPosition, 0);
                }

                showDetails(mRecyclerViewAdapter.getItemId(mCurCheckPosition));
            }
        });

        ///[RecyclerView#优化：setHasFixedSize(true)]
        ///当增删Item时不会影响RecyclerView的宽高时可以设置setHasFixedSize(true)来提高RecyclerView绘制速度，尤其是对于 GridLayoutManager
        ///如果影响就用notifyDataSetChanged()整体刷新一下
        mRecyclerView.setHasFixedSize(true);
        ///[RecyclerView#优化：取消RecyclerView的默认动画mRecyclerView.setItemAnimator(null)]
        mRecyclerView.setItemAnimator(null);

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        ///[RecyclerView#ScrollToPosition]当创建RecyclerView后首次滚动定位时，任意position都有效，但横竖屏切换后无效！
        mRecyclerView.smoothScrollToPosition(mCurCheckPosition);
//        mRecyclerView.scrollToPosition(mCurCheckPosition);
//        mRecyclerView.getLayoutManager().scrollToPosition(mCurCheckPosition);

        ///[RecyclerView#获取指定position的View或Data]
        ///https://stackoverflow.com/questions/33784369/recyclerview-get-view-at-particular-position/38640656
        mRecyclerView.findViewHolderForAdapterPosition(mCurCheckPosition);

    }

    ///[双屏TwoPane]
    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     *
     * @param itemId
     */
    private void showDetails(long itemId) {
        // The basic design is multi-pane (landscape on the phone) allows us
        // to display both fragments (titles and details) with in the same
        // activity; that is FragmentLayout -- one activity with two
        // fragments.
        // Else, it's single-pane (portrait on the phone) and we fire
        // another activity to render the details fragment - two activities
        // each with its own fragment.
        //
        if (mTwoPane) {
            ///////?????????????????????????????????????????????????????????????????????????????????????????????????
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
//            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            ItemDetailFragment itemDetailFragment = null;
            final FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                itemDetailFragment = (ItemDetailFragment) fragmentManager.findFragmentById(R.id.item_detail_container);
            }

            if (itemDetailFragment == null || itemDetailFragment.getItemId() != itemId) {
                // Make new fragment to show this selection.
                itemDetailFragment = ItemDetailFragment.newInstance(itemId);

                // Execute a transaction, replacing any existing fragment with this one inside the frame.
                getFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, itemDetailFragment)
                        .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        } else {
            // Otherwise we need to launch a new activity to display the dialog fragment with selected text.
            final Intent intent = new Intent(mContext, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, itemId);

            mContext.startActivity(intent);
        }
    }

}
