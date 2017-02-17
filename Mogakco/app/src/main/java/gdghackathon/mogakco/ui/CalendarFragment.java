package gdghackathon.mogakco.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.domain.Event;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import gdghackathon.mogakco.R;
import gdghackathon.mogakco.model.MogakcoEvent;
import gdghackathon.mogakco.tools.MaterialDialog;

/**
 * Created by choijinjoo on 2017. 2. 16..
 */

public class CalendarFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.compactcalendar_view)
    gdghackathon.mogakco.tools.CompactCalendarView compactCalendarView;
    //    @Bind(R.id.btnLeftMonth)
//    ImageView btnLeftMonth;
//    @Bind(R.id.btnRightMonth)
//    ImageView btnRightMonth;
    @Bind(R.id.txtvYear)
    TextView txtvYear;
    @Bind(R.id.txtvMonth)
    TextView txtvMonth;

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MM", Locale.getDefault());
    private SimpleDateFormat dateFormatForYear = new SimpleDateFormat("yyyy", Locale.getDefault());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, v);
        initializeLayout();
        return v;
    }

    private void initializeLayout() {
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        // Add yesterday (mock data)
        Event ev1 = new Event(Color.BLUE, new DateTime().minusDays(1).getMillis(), new MogakcoEvent("GDG 세미나"));
        Event ev2 = new Event(Color.RED, new DateTime().minusDays(1).getMillis(), new MogakcoEvent("홍대에서 같이 코딩합시다!"));
        compactCalendarView.addEvent(ev1);
        compactCalendarView.addEvent(ev2);

        // Add now (mock data)
        Event ev3 = new Event(Color.GREEN, new DateTime().getMillis(), new MogakcoEvent("해커톤"));
        compactCalendarView.addEvent(ev3);
        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        List<Event> events = compactCalendarView.getEvents(1433701251000L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new gdghackathon.mogakco.tools.CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                String titles = "";
                for (Event event : events) {
                    titles += ((MogakcoEvent) event.getData()).getTitle() + " ";
                }

                if (events.size() > 0)
                    showEventListDialog(titles);

                txtvYear.setText(dateFormatForYear.format(dateClicked)+"년");
                txtvMonth.setText(dateFormatForMonth.format(dateClicked) + "월");

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                txtvYear.setText(dateFormatForYear.format(firstDayOfNewMonth)+"년");
                txtvMonth.setText(dateFormatForMonth.format(firstDayOfNewMonth) + "월");

            }
        });

//        btnLeftMonth.setOnClickListener(this);
//        btnRightMonth.setOnClickListener(this);
    }

    private void showEventListDialog(String titles) {
        final MaterialDialog dialog = new MaterialDialog(getActivity());

        dialog.setTitle("이벤트 리스트");
        dialog.setMessage(titles);
        dialog.setPositiveButton("닫기", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case btnLeftMonth:
//                compactCalendarView.showPreviousMonth();
//                break;
//            case btnRightMonth:
//                compactCalendarView.showNextMonth();
//                break;
        }
    }
}

