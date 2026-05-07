package com.example.adminui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SimplePieChartView extends View {

    private final Paint slicePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF pieBounds = new RectF();
    private final List<PieSlice> slices = new ArrayList<>();

    public SimplePieChartView(Context context) {
        super(context);
        init();
    }

    public SimplePieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimplePieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        labelPaint.setColor(0xFF555555);
        labelPaint.setTextAlign(Paint.Align.CENTER);
        labelPaint.setTextSize(spToPx(11));
    }

    public void setSlices(List<PieSlice> data) {
        slices.clear();
        slices.addAll(data);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (slices.isEmpty()) {
            drawPlaceholderChart(canvas);
            return;
        }

        float total = 0f;
        for (PieSlice slice : slices) {
            total += slice.value;
        }
        if (total <= 0f) {
            return;
        }

        float padding = dpToPx(32);
        float size = Math.min(getWidth(), getHeight()) - (padding * 2);
        float left = (getWidth() - size) / 2f;
        float top = (getHeight() - size) / 2f;
        pieBounds.set(left, top, left + size, top + size);

        float startAngle = -90f;
        for (PieSlice slice : slices) {
            float sweepAngle = (slice.value / total) * 360f;
            slicePaint.setColor(slice.color);
            canvas.drawArc(pieBounds, startAngle, sweepAngle, true, slicePaint);

            float midAngle = startAngle + (sweepAngle / 2f);
            float labelRadius = size * 0.68f;
            float cx = pieBounds.centerX() + (float) (Math.cos(Math.toRadians(midAngle)) * labelRadius);
            float cy = pieBounds.centerY() + (float) (Math.sin(Math.toRadians(midAngle)) * labelRadius);
            String label = String.format(Locale.US, "%s\n%.0f%%", slice.label, slice.value);
            drawMultilineText(canvas, label, cx, cy);

            startAngle += sweepAngle;
        }
    }

    private void drawPlaceholderChart(Canvas canvas) {
        float padding = dpToPx(32);
        float size = Math.min(getWidth(), getHeight()) - (padding * 2);
        float left = (getWidth() - size) / 2f;
        float top = (getHeight() - size) / 2f;
        pieBounds.set(left, top, left + size, top + size);

        slicePaint.setColor(0xFFD9DEE8);
        canvas.drawArc(pieBounds, -90f, 360f, true, slicePaint);

        drawMultilineText(canvas, "Waiting\nfor data", pieBounds.centerX(), pieBounds.centerY());
    }

    private void drawMultilineText(Canvas canvas, String text, float x, float y) {
        String[] lines = text.split("\n");
        float lineHeight = labelPaint.getTextSize() + dpToPx(2);
        float startY = y - ((lines.length - 1) * lineHeight / 2f);
        for (int i = 0; i < lines.length; i++) {
            canvas.drawText(lines[i], x, startY + (i * lineHeight), labelPaint);
        }
    }

    private float dpToPx(int dp) {
        return dp * getResources().getDisplayMetrics().density;
    }

    private float spToPx(int sp) {
        return sp * getResources().getDisplayMetrics().scaledDensity;
    }

    public static class PieSlice {
        final String label;
        final float value;
        final int color;

        public PieSlice(String label, float value, int color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }
    }
}
