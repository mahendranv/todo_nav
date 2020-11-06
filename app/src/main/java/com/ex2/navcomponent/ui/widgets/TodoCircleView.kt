package com.ex2.navcomponent.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TodoCircleView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var fillColor: Int = DEFAULT_COLOR

    private var radius: Int = 10

    private val circlePaint = Paint().apply {
        isAntiAlias = false
        style = Paint.Style.FILL
    }

    private val tickPaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 12f
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        radius = MeasureSpec.getSize(widthMeasureSpec) / 2 - 10
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return


        val center = height / 2f
        circlePaint.color = fillColor
        canvas.drawCircle(center, center, radius.toFloat(), circlePaint)

        if (isSelected) {
            val path = Path()
            path.moveTo(height * 0.2f, height * 0.55f)
            path.lineTo(height * 0.40f, height * 0.75f)
            path.lineTo(height * 0.75f, height * 0.35f)
            canvas.drawPath(path, tickPaint)
        }
    }


    companion object {

        const val DEFAULT_COLOR = 0xff415dba.toInt()
    }
}