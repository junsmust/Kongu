package com.example.isp30.wearetheone;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.isp30.wearetheone.R;

public class ViewFlipperAction implements View.OnTouchListener {
    Context context;
    int countIndexes;//현재 화면 개수
    int currentIndex;//현재 화면 인덱스
    float downX;//터치용 x좌표
    float upX;
    ViewFlipper viewFlipper;

    ViewFlipperCallback indexCallback;
    //인터페이스 - 탭 클릭시 이미지 변경하기 위한 인터페이스
    //여러 엑티비타가 fragment를 호출하여도 동일한 인터페이스를 구현하도록 한다.
    public static interface ViewFlipperCallback{
        public void onFlipperActionCallback(int position);
    }

    public ViewFlipperAction(Context context, ViewFlipper viewFlipper) {
        this.context = context;
        this.viewFlipper = viewFlipper;

        if(context instanceof ViewFlipperCallback){
            indexCallback = (ViewFlipperCallback)context;
        }

        currentIndex = 0;
        downX = 0;
        upX= 0;
        countIndexes = viewFlipper.getChildCount();

        indexCallback.onFlipperActionCallback(currentIndex);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //터치시작
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            downX = event.getX();
        }
        //터치종료
        else if(event.getAction()==MotionEvent.ACTION_UP){
            upX = event.getX();

            //왼쪽 -> 오른쪽
            if(upX < downX){
                //애니메이션
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));

                //인덱스체크 - 마지막화면이면 동작없음
                if(currentIndex < (countIndexes-1)){
                    viewFlipper.showNext();

                    currentIndex++;
                    //인덱스 업데이트
                    indexCallback.onFlipperActionCallback(currentIndex);
                }
            }
            //오른쪽 -> 왼쪽
            else if(upX > downX){
                //애니메이션 설정
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));

                //인덱스체크 - 첫번째화면이면 동작없음
                if(currentIndex > 0){
                    viewFlipper.showPrevious();

                    currentIndex--;
                    //인덱스 업데이트
                    indexCallback.onFlipperActionCallback(currentIndex);
                }
            }
        }

        return true;
    }



}
