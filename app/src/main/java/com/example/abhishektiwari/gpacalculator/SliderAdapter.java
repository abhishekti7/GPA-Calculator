package com.example.abhishektiwari.gpacalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
      R.drawable.icon_2,
      R.drawable.cloud,
      R.drawable.icon_3
    };

    public String[] slide_headings = {
            "Calculate Your GPA and CGPA",
            "Store GPA grades on the Cloud",
            "Get Started"
    };

    public String[] slide_info = {
            "Calculate your GPA and CGPA with this application exclusive to IIIT Kalyani. Just enter your grades and Voila!",
            "Store your GPA performance details on the Cloud. Access the grades by logging into your account. Have your performance evaluation on the go.",
            "Just click ont Sign Up to create your account and enjoy the comfort of knowing and evaluating your GPA in minutes!"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImage = (ImageView)view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView)view.findViewById(R.id.slideHeading);
        TextView slideInfo = (TextView)view.findViewById(R.id.slideInfo);

        slideImage.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideInfo.setText(slide_info[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
