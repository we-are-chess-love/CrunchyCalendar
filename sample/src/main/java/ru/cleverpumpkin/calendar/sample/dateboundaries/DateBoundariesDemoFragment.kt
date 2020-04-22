package ru.cleverpumpkin.calendar.sample.dateboundaries

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_calendar.*
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import ru.cleverpumpkin.calendar.sample.BaseFragment
import ru.cleverpumpkin.calendar.sample.R
import java.util.*

/**
 * This demo fragment demonstrate usage of the [CalendarView] with min-max date boundaries.
 *
 * Created by Alexander Surinov on 2019-05-13.
 */
class DateBoundariesDemoFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_calendar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbarView) {
            setTitle(R.string.demo_date_boundaries)
            setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }

        if (savedInstanceState == null) {
            setupCalendar()
        }
    }

    private fun setupCalendar() {
        val calendar = Calendar.getInstance()

        val initialDate = CalendarDate(calendar.time)

        calendar.set(2020, Calendar.APRIL, 1)
        val minDate = CalendarDate(calendar.time)

        calendar.set(2020, Calendar.JULY, 30)
        val maxDate = CalendarDate(calendar.time)

        calendarView.setupCalendar(
                initialDate = initialDate,
                selectedDates = listOf(initialDate),
                minDate = minDate,
                maxDate = maxDate,
                selectionMode = CalendarView.SelectionMode.SINGLE
        )

    }

}