package ru.cleverpumpkin.calendar.sample.events;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import ru.cleverpumpkin.calendar.CalendarDate;
import ru.cleverpumpkin.calendar.CalendarView;
import ru.cleverpumpkin.calendar.extension.ContextExtenisonKt;
import ru.cleverpumpkin.calendar.sample.BaseFragment;
import ru.cleverpumpkin.calendar.sample.R;

/**
 * Created by Alexander Surinov on 04.07.2019
 */
public class EventListDemoJavaFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbarView);

        toolbar.setTitle(R.string.demo_events);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDatesIndicators(generateEventItems());

        if (savedInstanceState == null) {
            calendarView.setupCalendar(
                    CalendarDate.Companion.getToday(),
                    null,
                    null,
                    CalendarView.SelectionMode.NONE,
                    Collections.<CalendarDate>emptyList(),
                    null,
                    true
            );
        }
    }

    private List<EventItem> generateEventItems() {
        Context context = requireContext();
        Calendar calendar = Calendar.getInstance();

        List<EventItem> eventItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            eventItems.add(
                    new EventItem(
                            new CalendarDate(calendar.getTime()),
                            ContextExtenisonKt.getColorInt(context, R.color.event_1_color),
                            "Event #1"
                    )
            );

            eventItems.add(
                    new EventItem(
                            new CalendarDate(calendar.getTime()),
                            ContextExtenisonKt.getColorInt(context, R.color.event_2_color),
                            "Event #2"
                    )
            );

            eventItems.add(
                    new EventItem(
                            new CalendarDate(calendar.getTime()),
                            ContextExtenisonKt.getColorInt(context, R.color.event_3_color),
                            "Event #3"
                    )
            );

            eventItems.add(
                    new EventItem(
                            new CalendarDate(calendar.getTime()),
                            ContextExtenisonKt.getColorInt(context, R.color.event_4_color),
                            "Event #4"
                    )
            );

            eventItems.add(
                    new EventItem(
                            new CalendarDate(calendar.getTime()),
                            ContextExtenisonKt.getColorInt(context, R.color.event_5_color),
                            "Event #5"
                    )
            );

            calendar.add(Calendar.DAY_OF_MONTH, 5);
        }

        return eventItems;
    }

}
