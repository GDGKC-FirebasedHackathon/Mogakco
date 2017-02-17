package gdghackathon.mogakco.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import gdghackathon.mogakco.R;

public class LoadingActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.getStartIntent(LoadingActivity.this));
                finish();
            }
        }, 1500);

    }

    private void initializeLayout(){

    }
}
