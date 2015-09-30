package com.example.ledoa.dailyexsuper.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HexagonImageView2 extends ImageView {
    private static int viewWidth;
    private static int viewHeight;
    private static float radius2;
    private static int borderWidth = 1;

    public HexagonImageView2(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        viewWidth = getWidth();
        viewHeight = getHeight() + 2;
        radius2 = viewHeight / 2;
        Bitmap roundBitmap = getRoundedCroppedBitmap(bitmap, viewWidth);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    public Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        Paint paintBorder = new Paint();
      //  paintBorder.setAntiAlias(true);
       // this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);

        float centerX = viewWidth / 2;
        float centerY = viewHeight / 2;
        float radiusBorder = radius2 - borderWidth;
        float triangleBorderHeight = (float) (Math.sqrt(3) * radiusBorder / 2);
        float triangleHeight = (float) (Math.sqrt(3) * radius / 2);

        Path hexagonBorderPath = new Path();
        hexagonBorderPath.moveTo(centerX, centerY + radius);
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY + radius/2);
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY - radius/2);
        hexagonBorderPath.lineTo(centerX, centerY - radius);
        hexagonBorderPath.lineTo(centerX + triangleHeight, centerY - radius / 2);
        hexagonBorderPath.lineTo(centerX + triangleHeight, centerY + radius / 2);
        hexagonBorderPath.moveTo(centerX, centerY + radius);

        Path hexagonPath = new Path();
        hexagonPath.moveTo(centerX, centerY + radiusBorder);
        hexagonPath.lineTo(centerX - triangleBorderHeight, centerY + radiusBorder / 2);
        hexagonPath.lineTo(centerX - triangleBorderHeight, centerY - radiusBorder / 2);
        hexagonPath.lineTo(centerX, centerY - radiusBorder);
        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY - radiusBorder / 2);
        hexagonPath.lineTo(centerX + triangleBorderHeight, centerY + radiusBorder / 2);
        hexagonPath.moveTo(centerX, centerY + radiusBorder);
        hexagonPath.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#e30910"));
        //paintBorder.setColor(Color.parseColor("#e30910"));
        canvas.drawPath(hexagonPath, paint);
        //canvas.drawPath(hexagonBorderPath, paintBorder);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }
}