package com.temp.dattex.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.WithDrawListBean;
import java.util.List;

public class WithdrawListAdapter  extends BaseQuickAdapter <WithDrawListBean, BaseViewHolder>{

    public WithdrawListAdapter(@Nullable List<WithDrawListBean> data) {
        super(R.layout.item_withdrawlist, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, WithDrawListBean item) {
        int position = helper.getLayoutPosition();
        TextView tv_name =helper.getView(R.id.str1);
        tv_name.setText(item.getString1());

        TextView tv_address =helper.getView(R.id.str2);
        tv_address.setText(item.getString2());

        ImageView ivDelect = helper.getView(R.id.iv_delete);
        ivDelect.setOnClickListener(view -> {
        remove(position);
        notifyDataSetChanged();
        });
    }
}
