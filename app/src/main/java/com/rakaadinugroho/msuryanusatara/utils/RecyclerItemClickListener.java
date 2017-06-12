package com.rakaadinugroho.msuryanusatara.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Raka Adi Nugroho on 5/28/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        this.mListener  = listener;
        mGestureDetector    = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child  = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null){
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childview  = rv.findChildViewUnder(e.getX(), e.getY());
        if (childview != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(childview, rv.getChildAdapterPosition(childview));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
