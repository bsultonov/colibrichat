package org.colibrisoft.colibri;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends AppCompatActivity {




    private ViewPager mSlideViewPager;
    private LinearLayout mSwitchBtn;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mSwitchBtn = (LinearLayout) findViewById(R.id.SwitchBtn);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        start_btn = (Button) findViewById(R.id.start_button);

    }

    /*public void phone_act(View v) {
        Intent i = new Intent(this, Phone.class);
        startActivity(i);
        finish();

   }*/

    public void user_name(View v) {
        Intent i = new Intent(MainActivity.this, UserInfo.class);
        startActivity(i);
        finish();

    }


    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mSwitchBtn.removeAllViews();
        for(int i =0; i<mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mSwitchBtn.addView(mDots[i]);
        }

        if(mDots.length>0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));

        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
