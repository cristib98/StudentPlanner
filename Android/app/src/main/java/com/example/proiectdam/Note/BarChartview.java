package com.example.proiectdam.Note;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BarChartview extends View {
    private Map<Integer, Integer> source;
    private Paint paint;
    private List<Integer> labels;
    private Random random;

    public BarChartview(Context context, Map<Integer, Integer> source)
    {
        super(context);
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        labels = new ArrayList<>(source.keySet());
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (source != null && source.size() > 0)
        {
            paint.setStrokeWidth((float) (getHeight() * 0.01));

            float paddW = (float) (getWidth() * 0.1);
            float paddH = (float) (getHeight() * 0.1);


            float availableWidth = getWidth() - 2 * paddW;
            float availableHeight = getHeight() - 2 * paddH;


            canvas.drawLine(paddW, paddH, paddW, paddH + availableHeight, paint);
            canvas.drawLine(paddW, paddH + availableHeight, paddW + availableWidth, paddH + availableHeight, paint);


            double maxValue = calculateMaxim();

            float widthOfElement = availableWidth / source.size();
            for (int i = 0; i < labels.size(); i++) {

                int color = Color.argb(100,
                        1 + random.nextInt(254),
                        1 + random.nextInt(254),
                        1 + random.nextInt(254));
                paint.setColor(color);

                float x1 = paddW + i * widthOfElement;
                float y1 = (float) ((1 - source.get(labels.get(i)) / maxValue) * availableHeight + paddH);
                float x2 = x1 + widthOfElement;
                float y2 = paddH + availableHeight;

                canvas.drawRect(x1, y1, x2, y2, paint);
                drawLabel(canvas, x1, widthOfElement, paddH, availableHeight, labels.get(i));
            }
        }
    }

    private void drawLabel(Canvas canvas, float x1, float widthOfElement, float paddH, float availableHeight, Integer label) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.2* widthOfElement));
        float x = x1 + widthOfElement / 2;
        float y = 1 / 2 * paddH + availableHeight;
        canvas.rotate(270, x, y);
        canvas.drawText(label + " - " + source.get(label), x, y, paint);
        canvas.rotate(-270, x, y);
    }

    private double calculateMaxim() {
        double max = 0;
        for (double value : source.values()) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

}
