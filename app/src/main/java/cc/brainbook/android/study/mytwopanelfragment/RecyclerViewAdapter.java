package cc.brainbook.android.study.mytwopanelfragment;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cc.brainbook.android.study.mytwopanelfragment.dummy.DummyContent;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LongSparseArray<DummyContent.DummyItem> mValueMap;

    ///[RecyclerView#OnItemClickListener]
    ///https://hackernoon.com/android-recyclerview-onitemclicklistener-getadapterposition-a-better-way-3c789baab4db
    private View.OnClickListener mOnItemClickListener;
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        mOnItemClickListener = onClickListener;
    }

    public RecyclerViewAdapter(LongSparseArray<DummyContent.DummyItem> itemMap) {
        mValueMap = itemMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    ///Note: avoid any expensive operations in onBindViewHolder() because it can slow down your scrolling.
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ///注意：如果增删Item时，这里应使用viewHolder.getAdapterPosition()，而不用position，否则可能导致混乱！
        // The `position` sometimes is not updated according to the latest update (add or delete).
        ///https://medium.com/@haydar_ai/better-way-to-get-the-item-position-in-androids-recyclerview-820667d435d4
        ///viewHolder.deleteButton.setOnClickListener(view -> delete(position));///wrong!
        ///viewHolder.deleteButton.setOnClickListener(view -> delete(viewHolder.getAdapterPosition()));///right

        holder.mIdView.setText(String.valueOf(getItem(position).id));
        holder.mContentView.setText(getItem(position).content);
    }

    ///建议：尤其当有id时，添加方法，方便用Adapter操作数据
    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    ///建议：添加方法，方便用Adapter操作数据
    public DummyContent.DummyItem getItem(int position) {
        return mValueMap.valueAt(position);
    }

    @Override
    public int getItemCount() {
        return mValueMap.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View itemView) {
            super(itemView);

            mIdView = (TextView) itemView.findViewById(R.id.id_text);
            mContentView = (TextView) itemView.findViewById(R.id.content);

            ///[RecyclerView#onItemClickListener]
            ///https://hackernoon.com/android-recyclerview-onitemclicklistener-getadapterposition-a-better-way-3c789baab4db
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

}
