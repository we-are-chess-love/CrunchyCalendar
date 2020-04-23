package ru.cleverpumpkin.calendar.style

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import ru.cleverpumpkin.calendar.R
import ru.cleverpumpkin.calendar.extension.getColorInt

/**
 * This class represent a holder for Calendar's style XML attributes.
 */
internal class CalendarStyleAttributes(
    context: Context,

    @ColorInt
    var daysBarBackground: Int = context.getColorInt(R.color.calendar_days_bar_background),

    @ColorInt
    var daysBarTextColor: Int = context.getColorInt(R.color.calendar_days_bar_text_color),

    @ColorInt
    var monthTextColor: Int = context.getColorInt(R.color.calendar_month_text_color),

    @ColorInt
    var selectedDateCellBackgroundColor: Int = context.getColorInt(R.color.calendar_date_selected_background),

    @FontRes
    var dateCellFont: Int = R.font.ptsans_regular,

    @FontRes
    var monthLabelFont: Int = R.font.ptsans_regular,

    var dateCellTextColorStateList: ColorStateList = requireNotNull(
        ContextCompat.getColorStateList(context, R.color.calendar_date_text_selector)
    )

)