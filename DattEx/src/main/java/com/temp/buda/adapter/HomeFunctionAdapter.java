package com.temp.buda.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.HomeFunctionBean;
import java.util.List;

public class HomeFunctionAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> {
    Context mContext;
    public HomeFunctionAdapter(@Nullable List<HomeFunctionBean> data,Context context) {
        super(R.layout.item_homefunction, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeFunctionBean item) {
        TextView tvFunName = helper.getView(R.id.tv_fun_name);
        tvFunName.setText(item.getmFunName());
        TextView tvFunInfo = helper.getView(R.id.tv_fun_info);
        tvFunInfo.setText(item.getmFunInfo());
        ImageView ivFun = helper.getView(R.id.iv_fun);
        if (tvFunName.getText().toString().contains("帮助中心")){
            Glide.with(mContext).load(R.mipmap.icon_help_center).into(ivFun);
        }else if (tvFunName.getText().toString().contains("新闻公告")){
            Glide.with(mContext).load(R.mipmap.icon_news).into(ivFun);
        }
    }
}