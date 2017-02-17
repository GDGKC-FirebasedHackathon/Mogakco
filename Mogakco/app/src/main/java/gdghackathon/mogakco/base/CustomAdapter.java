package gdghackathon.mogakco.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final static int ANIM_DURATION = 400; // in milliseconds

    protected final Context mContext;
    protected List<T> mItems;
    private int lastPosition = -1;

    public void add(T item) {
        int position = getItemCount();
        mItems.add(item);
        notifyItemInserted(position);
    }

    public void add(T item, int index) {
        mItems.add(index, item);
        notifyItemInserted(index);
    }

    public void add(List<T> items) {
        int position = getItemCount();
        for (T item : items) {
            mItems.add(item);
        }
        notifyItemRangeInserted(position, items.size());
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mItems.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(T item) {
        int position = mItems.indexOf(item);
        remove(position);
    }

    public void clear() {
        int count = mItems.size();
        if (count > 0) {
            mItems.clear();
            notifyDataSetChanged();
        }
    }

    public CustomAdapter(Context context, List<T> data) {
        mContext = context;
        if (data != null) {
            //mItems = new ArrayList<T>(data);
            mItems = data;
        } else mItems = new ArrayList<T>();
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

//    protected void setAnimation(RecyclerView.ViewHolder holder, int position) {
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(mContext,
//                    R.anim.up_from_bottom);
//            holder.itemView.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
//    protected void setAnimation(RecyclerView.ViewHolder holder, int position) {
//        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        anim.setDuration(ANIM_DURATION);
//        holder.itemView.startAnimation(anim);
//    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public List<T> getItems() {
        return mItems;
    }
}