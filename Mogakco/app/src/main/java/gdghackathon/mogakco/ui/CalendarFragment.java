package gdghackathon.mogakco.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gdghackathon.mogakco.R;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class CalendarFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, v);
        initializeLayout();
        return v;
    }

    private void initializeLayout() {
    }

    @Override
    public void onClick(View v) {

    }
}

