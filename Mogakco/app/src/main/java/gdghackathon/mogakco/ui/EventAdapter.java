package gdghackathon.mogakco.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.base.CustomAdapter;
import gdghackathon.mogakco.model.MogakcoEvent;

/**
 * Created by choijinjoo on 2017. 2. 17..
 */

public class EventAdapter extends CustomAdapter<MogakcoEvent, EventAdapter.EventViewHolder> {

    OnClickListener mListener;

    interface OnClickListener{
        void OnClick(MogakcoEvent event);
    }

    public EventAdapter(Context context, List<MogakcoEvent> data, OnClickListener listener) {
        super(context, data);
        this.mListener = listener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_event, null));
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        holder.txtvTitle.setText(mItems.get(position).getTitle());
        if (mItems.get(position).getImageUrl() != null) {
            holder.imgvEvent.setImageURI(mItems.get(position).getImageUrl());
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnClick(mItems.get(position));
            }
        });
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.container)
        CardView container;
        @Bind(R.id.txtvTitle)
        TextView txtvTitle;
        @Bind(R.id.txtvLocation)
        TextView txtvLocation;
        @Bind(R.id.txtvDate)
        TextView txtvDate;
        @Bind(R.id.imgvEvent)
        SimpleDraweeView imgvEvent;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
