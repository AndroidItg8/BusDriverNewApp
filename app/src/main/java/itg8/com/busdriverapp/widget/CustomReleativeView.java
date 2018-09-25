package itg8.com.busdriverapp.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;


/**
 * Created by Android itg 8 on 1/25/2018.
 */

public class CustomReleativeView extends FrameLayout {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Path path = new Path();

    public CustomReleativeView(Context context) {
        super(context);
    }

    public CustomReleativeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomReleativeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomReleativeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

        paint.setXfermode(pdMode);
        path.reset();
        path.moveTo(0, getHeight());
        path.lineTo(getWidth(), getHeight());

        path.lineTo(getWidth(), getHeight() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics()));
        path.close();
        canvas.drawPath(path, paint);

        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);



//        RectF space = new RectF(this.getLeft()-100, this.getTop()-100, this.getRight()+100, this.getBottom());
//
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//          paint.setShader(new LinearGradient(0, getWidth(), 0, 0, Color.parseColor("#076810"), Color.parseColor("#076810"), Shader.TileMode.MIRROR));
//
//        canvas.drawArc(space, 270, 360, true, paint);
//
//        Rect rect = new Rect(this.getLeft(),this.getTop(),this.getRight(),this.getBottom()-((getBottom()-getTop())/4));
//        canvas.drawRect(rect, paint);
    }

//      paint.setXfermode(pdMode);
//        path.reset();
//        path.moveTo(0, getHeight());
//        path.lineTo(getWidth(), getHeight());
//        path.lineTo(getWidth(), getHeight() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
//        path.close();
//        canvas.drawPath(path, paint);
//
//        canvas.restoreToCount(saveCount);
//        paint.setXfermode(null);



}
