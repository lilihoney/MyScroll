package scroll.qll.com.myscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by qll on 2016/8/22.
 */

public class MyScroll extends ViewGroup {
    private int mScreenHeight;
    private int mLastY;
    private Scroller mScroller;
    private int mStart,mEnd;
    
    public MyScroll(Context context){
        this(context,null);
    }
    public MyScroll(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public MyScroll(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
    }

    /**
     * 测量ViewGroup尺寸
     * 设置wrap_cotent必须要重写onMeasure方法
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i = 0;i < count;++i){
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 子View的摆放位置
     * */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        //设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams)getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        for(int i = 0 ;i < childCount;++i){
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                child.layout(left, i*mScreenHeight, right, (i+1)*mScreenHeight);
            }
        }
    }

    /**
     * 重写触摸事件
     * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录触摸起点
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY- y;
                if(getScrollY() < 0 || getScrollY() > getHeight()-mScreenHeight){
                    dy = 0;
                }
                scrollBy(0,dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //记录触摸终点
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY > 0){
                    if(dScrollY < mScreenHeight /3){
                        mScroller.startScroll(
                                0,getScrollY(),
                                0,-dScrollY);
                    }else{
                        mScroller.startScroll(
                                0,getScrollY(),
                                0,mScreenHeight - dScrollY);
                    }
                }else{
                    if(-dScrollY < mScreenHeight/3){
                        mScroller.startScroll(
                                0,getScrollY(),
                                0,-dScrollY);
                    }else{
                        mScroller.startScroll(
                                0,getScrollY(),
                                0,-mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }
    public void computeScroll(){
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }

    }
}
