package com.temp.dattex.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.bean.NewPayTypeBean;
import com.temp.dattex.bean.WithDrawListBean;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.login.LoginActivity;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;
import com.temp.dattex.withdraw.NewPayTypeActivity;

import java.util.List;

public class WithdrawListAdapter  extends BaseQuickAdapter <NewPayTypeBean, BaseViewHolder>{
  private NewPayTypeActivity mActivity;
  private int id;
    public WithdrawListAdapter(NewPayTypeActivity activity, @Nullable List<NewPayTypeBean> data) {
        super(R.layout.item_withdrawlist, data);
        mActivity = activity;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, NewPayTypeBean item) {
        int position = helper.getLayoutPosition();
        id = item.getId();
        ImageView ivLogo =helper.getView(R.id.iv_logo);

        if (item.getType()==1)
            Glide.with(mActivity).load(mActivity.getResources().getDrawable(R.mipmap.bank)).into(ivLogo);
        else if (item.getType()==2){
            Glide.with(mActivity).load(mActivity.getResources().getDrawable(R.mipmap.alipay)).into(ivLogo);
        }else {
            Glide.with(mActivity).load(mActivity.getResources().getDrawable(R.mipmap.wx)).into(ivLogo);
        }
        TextView tv_name =helper.getView(R.id.str1);
        tv_name.setText(item.getBankName());

        TextView tv_address =helper.getView(R.id.str2);
        tv_address.setText(item.getReceivingAccount());

        ImageView ivDelect = helper.getView(R.id.iv_delete);
        ivDelect.setOnClickListener(view -> {
            showDialog();
        });

        RelativeLayout rlDelete = helper.getView(R.id.rl_delete);
        rlDelete.setOnClickListener(view -> showDialog());

    }
    public  void showDialog() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_isexit,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        TextView tvDialogTitle = view.findViewById(R.id.tv_dialog_title);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvDialogTitle.setText("是否删除");
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
