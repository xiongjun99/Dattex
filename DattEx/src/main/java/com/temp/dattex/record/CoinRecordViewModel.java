package com.temp.dattex.record;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.bus.SingleLiveEvent;
import com.independ.framework.response.ResponseTransformer;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.temp.dattex.R;
import com.temp.dattex.bean.AssetsRecordBean;
import com.temp.dattex.bean.CoinRecordBean;
import com.temp.dattex.binding.adapter.CommonViewBinding;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.databinding.ItemCoinRecordBinding;
import com.temp.dattex.kline.KlineViewModel;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.DateFormatter;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.Utils;

import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.record
 * @FileName     : CoinRecordViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/20
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class CoinRecordViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, CommonViewBinding.SmartViewListener {

    private String coinName;
    private int page;
    private int inorout;

    public int getInorout() {
        return inorout;
    }

    public void setInorout(int inorout) {
        this.inorout = inorout;
    }
    public static class UIChangeObservable {
        //密码开关观察者
        SingleLiveEvent<Boolean> pop = new SingleLiveEvent<>();
    }
    public CoinRecordViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void rightClick() {
        CoinRecordFilerViewModel coinRecordFilerViewModel = new CoinRecordFilerViewModel();
        Activity peek = AppManager.getActivityStack().peek();
        DialogUtil.showFilterDialog(peek, coinRecordFilerViewModel);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        getData();
    }

    @SuppressLint("CheckResult")
    private void getData() {
        DataService.getInstance().findWithdrawRecord(inorout,coinName, page, "").compose(ResponseTransformer.<CoinRecordBean>handleResult()).subscribe(
                bean -> {
                    List<CoinRecordBean.RowsBean> rows = bean.getRows();
                    if (null != rows) {
                        page++;
                        madapter.addData(rows);
                        madapter.notifyDataSetChanged();
                    }
                }, t -> {

                }
        );
        madapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                CoinRecordBean.RowsBean data = (CoinRecordBean.RowsBean) adapter.getData().get(position);
                String type = "";
                String addr = "";
                if (inorout==1){
                    type = "链上提币";
                    addr = data.getFromAddr();
                } else {
                    type = "链上充币";
                    addr = data.getToAddr();
                }
                ShowDialog(addr,Utils.format8(String.valueOf(data.getFee())),String.valueOf(data.getAmount()),type,String.valueOf(data.getStates()),String.valueOf(data.getTxhash()),data.getCreateTime());
            }
        });
    }


    public BaseQuickAdapter madapter = new BaseQuickAdapter<CoinRecordBean.RowsBean, BaseViewHolder>(R.layout.item_coin_record) {

        @Override
        protected void convert(BaseViewHolder viewHolder, CoinRecordBean.RowsBean assetsItemBean) {
            ItemCoinRecordBinding binding = viewHolder.getBinding();
            binding.setDataBean(assetsItemBean);
            if (assetsItemBean.getStates()==0){
                binding.tvStatus.setText("待审核");
            } else if (assetsItemBean.getStates()==1){
                binding.tvStatus.setText("取消");
            } else if (assetsItemBean.getStates()==2){
                binding.tvStatus.setText("网络确认中");
            } else if (assetsItemBean.getStates()==3){
                binding.tvStatus.setText("驳回");
            } else if (assetsItemBean.getStates()==4){
                binding.tvStatus.setText("成功");
            }else if (assetsItemBean.getStates()==5){
                binding.tvStatus.setText("失败");
            }
            if (inorout == 0) {
                binding.tvName.setText("链上充币");
            } else {
                binding.tvName.setText("链上提币");
            }
            binding.tvTime.setText(DateFormatter.StrformatTime(assetsItemBean.getCreateTime()));
            binding.tvAmount.setText(Utils.format8(String.valueOf(assetsItemBean.getAmount())));
        }

        @Override
        protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            DataBindingUtil.bind(viewHolder.itemView);

        }
    };
    public  void ShowDialog(String addr,String fee,String amount,String type,String status,String id ,String time) {
        Activity peek = AppManager.getActivityStack().peek();
        View view = LayoutInflater.from(peek).inflate(R.layout.dialog_record,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(peek).setView(view).create();
        ImageView ivCancel = view.findViewById(R.id.iv_cancel);
        dialog.setCancelable(false);
        ivCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });
        TextView tvAmount = view.findViewById(R.id.tv_amount);
        TextView tvType = view.findViewById(R.id.tv_type);
        TextView tvStatus = view.findViewById(R.id.tv_status);
        TextView tvId = view.findViewById(R.id.tv_id);
        TextView tvTime = view.findViewById(R.id.tv_time);
        TextView tvAddr = view.findViewById(R.id.tv_addr);
        TextView tvFee = view.findViewById(R.id.tv_fee);

        RelativeLayout rlAddr = view.findViewById(R.id.rl_addr);
        RelativeLayout rlFee = view.findViewById(R.id.rl_fee);
        View viewAddr = view.findViewById(R.id.view_addr);
        View viewFee = view.findViewById(R.id.view_fee);

        tvAddr.setText(addr);
        tvFee.setText(fee);
        if (type.contains("提币")){
            tvAmount.setText("- "+ Utils.format8(amount) + " USDT");
        }else {
            tvAmount.setText("+ "+amount + " USDT");
        }
        if (status.contains("0")){
            tvStatus.setText("待审核");
        } else if (status.contains("1")){
            tvStatus.setText("取消");
        } else if (status.contains("2")){
            tvStatus.setText("网络确认中");
        } else if (status.contains("3")){
            tvStatus.setText("驳回");
        } else if (status.contains("4")){
            tvStatus.setText("成功");
        }else if (status.contains("5")){
            tvStatus.setText("失败");
        }
        if (id == null){
            tvId.setText("");
        }else {
            tvId.setText(id);
        }
        if(inorout==0){
            rlAddr.setVisibility(View.GONE);
            rlFee.setVisibility(View.GONE);
            viewAddr.setVisibility(View.GONE);
            viewFee.setVisibility(View.GONE);
        }else {
            rlAddr.setVisibility(View.VISIBLE);
            rlFee.setVisibility(View.VISIBLE);
            viewAddr.setVisibility(View.VISIBLE);
            viewFee.setVisibility(View.VISIBLE);
        }
        tvType.setText(type);
        tvTime.setText(time);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setBackgroundDrawable(new ColorDrawable(peek.getResources().getColor(R.color.color_282C42)));
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog); // 添加动画
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, Utils.getScreenHeight(peek)/5*4);
    }
}
