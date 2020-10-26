package com.example.eventmang;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {

    Context mContext;
    ImageAdapter(Context context){
        this.mContext=context;

    }

    @Override
    public int getCount() {
        return imageSlide.length;
    }

    private int[] imageSlide=new int[]{
            R.drawable.dashboardimg1,R.drawable.dashboardimg3,R.drawable.dashboardimg4,R.drawable.ec2,R.drawable.ec3,R.drawable.ec4,
            R.drawable.eco1,R.drawable.eco4,R.drawable.mech1
    };

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageSlide[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
