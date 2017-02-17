package gdghackathon.mogakco.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.model.MogakcoEvent;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class SearchFragment extends Fragment implements View.OnClickListener {
    private DatabaseReference mDatabase;
    @Bind(R.id.recvEvent)
    RecyclerView recvEvent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, v);
        initializeLayout();
        return v;
    }

    private void initializeLayout() {
        recvEvent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    private void startSearch(String title) {
        mDatabase.child("title").child(title).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        MogakcoEvent user = dataSnapshot.getValue(MogakcoEvent.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            Toast.makeText(getActivity(), "검색결과가 없어요 ㅠㅠ. ", Toast.LENGTH_SHORT).show();
                        } else {

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
