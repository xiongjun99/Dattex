package com.temp.dattex.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.HomeFunctionBean;
import java.util.List;

public class HomeFunctionAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> {
    public HomeFunctionAdapter(@Nullable List<HomeFunctionBean> data) {
        super(R.layout.item_homefunction, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeFunctionBean item) {
        TextView tvFunName = helper.getView(R.id.tv_fun_name);
        tvFunName.setText(item.getmFunName());
        TextView tvFunInfo = helper.getView(R.id.tv_fun_info);
        tvFunInfo.setText(item.getmFunInfo());
        ImageView ivFun = helper.getView(R.id.iv_fun);
        Bitmap bmp = BitmapFactory.decodeResource(getContext().getResources(), item.getmURL());
        ivFun.setImageBitmap(bmp);
    }
}