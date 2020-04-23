package ru.cleverpumpkin.calendar.style

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ru.cleverpumpkin.calendar.R

/**
 * This class responsible for reading Calendar's style XML attributes.
 */
internal object CalendarStyleAttributesReader {

    fun readStyleAttributes(
            context: Context,
            attrs: AttributeSet,
            @AttrRes defStyleAttr: Int,
            styleAttributes: CalendarStyleAttributes
    ) {
        val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.CalendarView, defStyleAttr, 0)

        try {
            with(styleAttributes) {

                daysBarBackground = typedArray.getColor(
                        R.styleable.CalendarView_calendar_day_bar_background,
                        daysBarBackground
                )

                daysBarTextColor = typedArray.getColor(
                        R.styleable.CalendarView_calendar_day_bar_text_color,
                        daysBarTextColor
                )

                monthTextColor = typedArray.getColor(
                        R.styleable.CalendarView_calendar_month_text_color,
                        monthTextColor
                )

                selectedDateCellBackgroundColor = typedArray.getColor(
                        R.styleable.CalendarView_calendar_selected_dates_background_color,
                        selectedDateCellBackgroundColor
                )

                dateCellTextColorStateList = typedArray.getColorStateList(
                        R.styleable.CalendarView_calendar_date_text_color
                ) ?: dateCellTextColorStateList

                dateCellFont = typedArray.getResourceId(
                        R.styleable.CalendarView_calendar_dates_font,
                        dateCellFont
                )
            }
        } finally {
            typedArray.recycle()
        }
    }

}