package com.temp.dattex.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.temp.dattex.R;

//适配器
public class ImageNormalAdapter extends StaticPagerAdapter {
    //本地图片资源
    int[] imgs = new int[]{
            R.mipmap.banner_1,
            R.mipmap.banner_2,
    };

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setImageResource(imgs[position]);
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}