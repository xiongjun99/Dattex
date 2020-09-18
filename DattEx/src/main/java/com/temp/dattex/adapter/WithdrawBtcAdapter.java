package com.temp.dattex.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.bean.NewPayTypeBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;
import com.temp.dattex.withdraw.WithdrawListActivity;

import java.util.List;

public class WithdrawBtcAdapter extends BaseQuickAdapter<NewPayTypeBean, BaseViewHolder> {
    private WithdrawListActivity mActivity;
    private int id;
    public WithdrawBtcAdapter(WithdrawListActivity activity, @Nullable List<NewPayTypeBean> data) {
        super(R.layout.item_withdrawlist, data);
        mActivity = activity;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, NewPayTypeBean item) {
        int position = helper.getLayoutPosition();
        id = item.getId();
        TextView tv_name =helper.getView(R.id.str1);
        tv_name.setText(item.getAddr());
        TextView tv_address =helper.getView(R.id.str2);
        tv_address.setText(item.getReceivingAccount());
        tv_address.setVisibility(View.GONE);
        ImageView ivDelect = helper.getView(R.id.iv_delete);
        ivDelect.setOnClickListener(view -> {
            showDialog();
        });
    }
    public  void showDialog() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_isexit,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });
        tvConfirm.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
            remove();
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(mActivity)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void remove() {
        DataService.getInstance().removeMemberPayType(id).compose(ResponseTransformer.<Object>handleResult()).subscribe(b -> {
            mActivity.onResume();
        }, t -> {
            System.out.println("---------"+t.getMessage());
        });
    }
}

