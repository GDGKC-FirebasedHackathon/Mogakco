package gdghackathon.mogakco.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.base.CustomAdapter;
import gdghackathon.mogakco.model.MogakcoEvent;
import gdghackathon.mogakco.tools.MaterialDialog;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class EventListDialog extends MaterialDialog {
    @Bind(R.id.txtvDate)
    TextView txtvDate;
    @Bind(R.id.recvEvent)
    RecyclerView recvEvent;

    Context mContext;


    public EventListDialog(Context context) {
        super(context);
        mContext = context;
    }

    public static EventListDialog init(Context context, List<Event> mogakcoEvents) {
        EventListDialog dialog = new EventListDialog(context);
        dialog.setup(context, mogakcoEvents);
        return dialog;
    }

    private void setup(Context context, List<Event> events) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_eventlist, null);
        ButterKnife.bind(this, view);
        txtvDate.setText("2017년 10월10일");
        recvEvent.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        EventListAdapter mAdapter = new EventListAdapter(mContext, null);
        recvEvent.setAdapter(mAdapter);
        for (Event event : events) {
            mAdapter.add((MogakcoEvent) event.getData());
        }
        setCanceledOnTouchOutside(true);

        setNegativeButton("닫기", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setView(view);

    }

    class EventListAdapter extends CustomAdapter<MogakcoEvent, EventListAdapter.EventListViewHolder> {
        public EventListAdapter(Context context, List<MogakcoEvent> data) {
            super(context, data);
        }

        @Override
        public EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EventListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_event_dialog, null));
        }

        @Override
        public void onBindViewHolder(EventListViewHolder holder, int position) {
            final MogakcoEvent item = mItems.get(position);
            holder.txtvTitle.setText(item.getTitle());
            holder.txtvLocation.setText(item.getAddress() == null ? "" : item.getAddress());
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(EventDetailActivity.getStartIntent(mContext, item));
                    dismiss();
                }
            });
        }

        class EventListViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.container)
            CardView container;
            @Bind(R.id.txtvTitle)
            TextView txtvTitle;
            @Bind(R.id.txtvLocation)
            TextView txtvLocation;

            public EventListViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
