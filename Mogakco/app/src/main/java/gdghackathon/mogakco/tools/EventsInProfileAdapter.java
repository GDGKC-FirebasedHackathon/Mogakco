package gdghackathon.mogakco.tools;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gdghackathon.mogakco.R;
import gdghackathon.mogakco.ui.DetailActivity;

/**
 * Created by mk on 2017-02-17.
 */

public class EventsInProfileAdapter extends RecyclerView.Adapter<EventsInProfileAdapter.EventsInfProfileViewHolder>{
    private static final String TAG = EventsInProfileAdapter.class.getSimpleName();
    private Context mContext;

    public EventsInProfileAdapter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public EventsInfProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.events_in_profile_item, parent, false);

        Log.d(TAG, "호출테스트1");
        return new EventsInfProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsInfProfileViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    class EventsInfProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public EventsInfProfileViewHolder(View itemView){
            super(itemView);
            Log.d(TAG,"호출테스트 Viewholder");
            imageView = (ImageView)itemView.findViewById(R.id.show_imageOfEvents_in_fragment_profile);;
            textView = (TextView)itemView.findViewById(R.id.show_name_in_fragment_profile);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Log.d(TAG, "layoutposition테스트 " + position);

            // 인텐트
            Intent intent = new Intent(mContext, DetailActivity.class);
            mContext.startActivity(intent);

        }
    }
}
