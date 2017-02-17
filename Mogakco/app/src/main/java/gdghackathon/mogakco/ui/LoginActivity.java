package gdghackathon.mogakco.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.btnGoogle)
    ImageView btnGoogle;
    @Bind(R.id.btnGithub)
    ImageView btnGithub;
    @Bind(R.id.btnFB)
    ImageView btnFB;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initializeLayout();
    }

    private void initializeLayout() {
        btnGithub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGithub:
                startActivity(MainActivity.getStartIntent(LoginActivity.this));
                finish();
                break;
        }
    }
}
