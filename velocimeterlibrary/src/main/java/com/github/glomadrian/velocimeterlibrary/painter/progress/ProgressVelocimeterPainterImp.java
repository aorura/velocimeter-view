package com.github.glomadrian.velocimeterlibrary.painter.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.RectF;
import com.github.glomadrian.velocimeterlibrary.utils.DimensionUtils;

/**
 * @author Adrián García Lomas
 */
public class ProgressVelocimeterPainterImp implements ProgressVelocimeterPainter {

  private RectF circle;
  protected Paint paint;
  private int color;
  private int startAngle = 160;
  private int width;
  private int height;
  private float plusAngle = 0;
  private float max;
  private int strokeWidth;
  private int blurMargin;
  private int lineWidth;
  private int lineSpace;
  private Context context;

  public ProgressVelocimeterPainterImp(int color, float max, int margin, Context context) {
    this.color = color;
    this.max = max;
    this.blurMargin = margin;
    this.context = context;
    initSize();
    init();
  }

  private void initSize() {
    this.lineWidth = DimensionUtils.getSizeInPixels(1, context);
    this.lineSpace = DimensionUtils.getSizeInPixels(3, context);
    this.strokeWidth = DimensionUtils.getSizeInPixels(10, context);
  }

  private void init() {
    initPainter();
  }

  private void initPainter() {
    float pathEff[] = new float[] {lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineSpace, lineWidth, lineWidth+2*lineSpace};
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setAntiAlias(true);
    paint.setStrokeWidth(strokeWidth);
    paint.setColor(color);
    paint.setStyle(Paint.Style.STROKE);
    paint.setPathEffect(new DashPathEffect(pathEff, 0));
    //paint.setPathEffect(new DashPathEffect(new float[] { lineWidth, lineSpace}, 0));
    //paint.setPathEffect(new PathDashPathEffect(createRouteLineStyle(), 10, 3, PathDashPathEffect.Style.MORPH));
    //paint.setPathEffect(new PathDashPathEffect(makeDefaultLinePath(), 100, 3, PathDashPathEffect.Style.MORPH));

  }

  private Path makeDefaultLinePath() {
    Path p = new Path();
//    p.moveTo(-15f, 6f);
//    p.lineTo(-15f, -6f);
//    p.lineTo(-10f, -6f);
//    p.lineTo(-10f, -6f);
//    p.close();
    p.moveTo(0f, 6f);
    p.lineTo(0f, -6f);
    p.lineTo(5f, -6f);
    p.lineTo(5f, 6f);
    p.close();
    p.moveTo(25f, 6f);
    p.lineTo(25f, -6f);
    p.lineTo(20f, -6f);
    p.lineTo(20f, 6f);
    p.close();
    return p;
  }

  private void initExternalCircle() {
    int padding = strokeWidth / 2 + blurMargin;
    circle = new RectF();
    circle.set(padding, padding, width - padding, height - padding);
  }

  @Override public void draw(Canvas canvas) {
    canvas.drawArc(circle, startAngle, plusAngle, false, paint);
  }

  @Override public void setColor(int color) {
    this.color = color;
    paint.setColor(color);
  }

  @Override public int getColor() {
    return color;
  }

  @Override public void onSizeChanged(int height, int width) {
    this.width = width;
    this.height = height;
    initExternalCircle();
  }

  public void setValue(float value) {
    this.plusAngle = (222f * value) / max;
  }

  public float getMax() {
    return max;
  }

  public void setMax(float max) {
    this.max = max;
  }
}
