package ru.cleverpumpkin.calendar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import ru.cleverpumpkin.calendar.extension.dpToPix
import ru.cleverpumpkin.calendar.extension.getColorInt
import ru.cleverpumpkin.calendar.extension.spToPix

/**
 * This internal view class represents a single date cell of the Calendar with optional
 * colored indicators.
 *
 * This view class control its drawable state with [isToday], [isDateSelected] and [isDateDisabled] properties.
 */
internal class CalendarDateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0

) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_TEXT_SIZE = 14.0f
        private const val BACKGROUND_RADIUS = 48.0f

        private val stateToday = intArrayOf(R.attr.calendar_state_today)
        private val stateDateSelected = intArrayOf(R.attr.calendar_state_selected)
        private val stateDateDisabled = intArrayOf(R.attr.calendar_state_disabled)
    }

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = context.spToPix(DEFAULT_TEXT_SIZE)
    }

    private var dayNumberCalculatedWidth = 0.0f
    private var currentStateTextColor: Int = getColorInt(R.color.calendar_date_text_color)
    private var selectedDateColor: Int = getColorInt(R.color.calendar_date_selected_background)
    private var dateFont: Typeface? = null

    var textColorStateList: ColorStateList? = null

    var isToday: Boolean = false
        set(value) {
            if (value != field) {
                field = value
                refreshDrawableState()
            }
        }

    var isDateSelected: Boolean = false
        set(value) {
            if (value != field) {
                field = value
                refreshDrawableState()
            }
        }

    var isDateDisabled: Boolean = false
        set(value) {
            if (value != field) {
                field = value
                refreshDrawableState()
            }
            isClickable = value.not()
            isLongClickable = value.not()
        }

    var dayNumber: String = ""
        set(value) {
            field = value
            dayNumberCalculatedWidth = textPaint.measureText(value)
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawDayNumber()
    }

    private fun Canvas.drawDayNumber() {
        textPaint.color = currentStateTextColor
        dateFont?.let {
            textPaint.typeface = it
        }

        val xPos = width / 2.0f
        val yPos = height / 2.0f - (textPaint.descent() + textPaint.ascent()) / 2.0f
        val y = height / 2.0f

        if (isDateSelected) {
            drawCircle(xPos, y, BACKGROUND_RADIUS, Paint().apply {
                color = selectedDateColor
            })
        }

        drawText(dayNumber, xPos - (dayNumberCalculatedWidth / 2.0f), yPos, textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 4)

        if (isToday) {
            mergeDrawableStates(drawableState, stateToday)
        }

        if (isDateSelected) {
            mergeDrawableStates(drawableState, stateDateSelected)
        }

        if (isDateDisabled) {
            mergeDrawableStates(drawableState, stateDateDisabled)
        }

        return drawableState
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()

        textColorStateList?.let {
            currentStateTextColor = it.getColorForState(drawableState, currentStateTextColor)
        }
    }

    fun setSelectedDateBackgroundColor(@ColorInt color: Int) {
        selectedDateColor = color
        refreshDrawableState()
    }

    fun setDateFont(@FontRes font: Int) {
        dateFont = ResourcesCompat.getFont(context, font)
        refreshDrawableState()
    }

}