package cc.brainbook.android.study.mytwopanelfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cc.brainbook.android.study.mytwopanelfragment.dummy.DummyContent;
///AS Sample
/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    public static final String TAG = "TAG";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    ///尽量避免使用getActivity()
    private Context mContext;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    /**
     * Create a new instance of DetailsFragment.
     */
    public static ItemDetailFragment newInstance(long itemId) {
        final ItemDetailFragment fragment = new ItemDetailFragment();

        // Supply index input as an argument.
        final Bundle arguments = new Bundle();
        arguments.putLong(ItemDetailFragment.ARG_ITEM_ID, itemId);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "ItemDetailFragment# onAttach()# ");
        super.onAttach(context);

        ///尽量避免使用getActivity()
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "ItemDetailFragment# onAttach()# ");
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getLong(ARG_ITEM_ID));

            ///设置标题栏
            final Activity activity = getActivity();
            if (activity instanceof ItemDetailActivity) {
                activity.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "ItemDetailFragment# onActivityCreated()# ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "ItemDetailFragment# onStart()# ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "ItemDetailFragment# onResume()# ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "ItemDetailFragment# onPause()# ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "ItemDetailFragment# onStop()# ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "ItemDetailFragment# onDestroyView()# ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "ItemDetailFragment# onDestroy()# ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "ItemDetailFragment# onDetach()# ");
        super.onDetach();

        ///尽量避免使用getActivity()
        mContext = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "ItemDetailFragment# onSaveInstanceState()# ");
        super.onSaveInstanceState(outState);
    }

    public long getItemId() {
        if (getArguments() != null) {
            return getArguments().getLong(ARG_ITEM_ID, 0);
        } else {
            return -1;
        }
    }

}
