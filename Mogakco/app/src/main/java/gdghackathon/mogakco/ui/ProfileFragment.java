package gdghackathon.mogakco.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.model.Profile;
import gdghackathon.mogakco.tools.EventsInProfileAdapter;

import static gdghackathon.mogakco.R.layout.fragment_profile;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class ProfileFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = ProfileFragment.class.getSimpleName();


    RecyclerView mRecyclerView;
    private EventsInProfileAdapter eventsInProfileAdapter;

    @Bind(R.id.update_name_edittext_in_fragment_profile)
    EditText nameUpdateEdittext;
    @Bind(R.id.update_email_edittext_in_fragment_profile)
    EditText emailUpdateEdittext;
    @Bind(R.id.update_name_button_in_fragment_profile)
    ImageButton nameUpdateButton;
    @Bind(R.id.update_email_button_in_fragment_profile)
    ImageButton emailUpdateButton;
    @Bind(R.id.set_up_name_button_in_fragment_profile)
    ImageButton setUpNameButton;
    @Bind(R.id.set_up_email_button_in_fragment_profile)
    ImageButton setUpEmailButton;

    private FirebaseAuth mAuth;

    private String mFirebaseUid;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ValueEventListener mProfileListener;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ArrayList<Profile> mProfileList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "파이어베이스테스트 onCreate");

        databaseReference = databaseReference.child("profiles");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "파이어베이스테스트 sing in : " + user.getUid());
                }else{
                    Log.d(TAG, "파이어베이스테스트 sign out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "파이어베이스테스트 onstart");
        mAuth.addAuthStateListener(mAuthListener);

        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mProfileList.clear();
                Log.d(TAG, "파이어베이스테스트 onstart 속 onDataChange");
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Profile profile = Profile.parseSnapshot(child);
                    Log.d(TAG, "파이어베이스테스트 : " + profile.email);
                    Log.d(TAG, "파이어베이스테스트 : " + profile.name);
                    Log.d(TAG, "파이어베이스테스트 : " + profile.profileImgUrl);
                    Log.d(TAG, "파이어베이스테스트 : " + profile.firebaseUid);
                    mProfileList.add(profile);
                    Log.d(TAG, "파이어베이스 테스트"+firebaseDatabase.getReference().getParent() + firebaseDatabase.getReference().getDatabase() + firebaseDatabase.getReference().getKey() + firebaseDatabase.getReference().getRoot());
                    firebaseDatabase.getReference("child");
                    Log.d(TAG, "파이어베이스 테스트  : " + mProfileList.size() + mProfileList.get(0).firebaseUid + mProfileList.get(0).email);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "파이어베이스테스트 " + databaseError.getMessage());
            }
        };
        if(databaseReference != null){
            databaseReference.addValueEventListener(profileListener);
        }
        mProfileListener = profileListener;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_profile, container, false);
        ButterKnife.bind(this, v);
        initializeLayout(v);
//        nameUpdateEdittext =  (EditText)v.findViewById(R.id.update_name_edittext_in_fragment_profile);
//        emailUpdateEdittext = (EditText)v.findViewById(R.id.update_email_edittext_in_fragment_profile);

        nameUpdateEdittext.setEnabled(false);
        emailUpdateEdittext.setEnabled(false);

        setUpNameButton.setOnClickListener(this);
        setUpEmailButton.setOnClickListener(this);
        nameUpdateButton.setOnClickListener(this);
        emailUpdateButton.setOnClickListener(this);

        return v;
    }

    private void initializeLayout(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_in_fragment_profile);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        eventsInProfileAdapter = new EventsInProfileAdapter(getContext());
        mRecyclerView.setAdapter(eventsInProfileAdapter);
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG,"버튼테스트 name update");
        int viewId = v.getId();
        switch (viewId) {
            case R.id.update_name_button_in_fragment_profile:
                Log.d(TAG,"버튼테스트 name update");
                nameUpdateEdittext.setEnabled(true);
                break;
            case R.id.update_email_button_in_fragment_profile:
                Log.d(TAG,"버튼테스트 name update");
                emailUpdateEdittext.setEnabled(true);
                break;
            case R.id.set_up_name_button_in_fragment_profile:
                // 눌리면 이제 다시 못적게
                nameUpdateEdittext.setEnabled(false);
                String name = nameUpdateEdittext.getText().toString();

               // name값 보내기


                break;
            case R.id.set_up_email_button_in_fragment_profile:
                emailUpdateEdittext.setEnabled(false);
                String email = nameUpdateEdittext.getText().toString();

                mProfileList.get(0).email = "rlaansrl789@gmail.com";
//                mProfileList.size();
                Log.d(TAG, "파이어베이스테스트 email눌렀을떼 mProfileList size : " + mProfileList.size());

                FirebaseDatabase.getInstance().getReference().child("profiles").child("3epHnwNZqpZB00C6FY7fQIkRk9b2").updateChildren((HashMap)(mProfileList.get(0).toMap()));
                // email값 보내기
                Log.d(TAG, "파이어베이스 업뎃성공!" );
                break;
        }
    }


}
