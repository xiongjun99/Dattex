package com.temp.dattex.fragments.trade;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.LogUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.DialogPayAdapter;
import com.temp.dattex.adapter.TradeSymbolAdapter;
import com.temp.dattex.bean.InfoBySymbolBean;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.databinding.FragmentTradeBinding;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.SwitchSymbolDialogViewModel;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class TradeFragment extends BaseFragment<FragmentTradeBinding, TradeViewModel> implements SwitchSymbolDialogViewModel.OnSymbolSet {
//    private CustomSeekBar customSeekBar;
    private IndicatorSeekBar customSeekBar;
    private TextView tvPlaceAnorder;
    private TextView textView;
//  private RelativeLayout rlAska;
    private TradeSymbolAdapter applyAdapter;
    private Timer timer;
    private List<MarketListBean> mList = new ArrayList<>();
    private LinearLayout rlAska,rlAska1,rlAska2,rlAska3,rlAska4,rlBids,rlBids1,rlBids2,rlBids3,rlBids4;
    private String[] array;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_trade;
    }

    @Override
    public int initVariableId() {
        return BR.tradeViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {
        LogUtil.d("lazyLoad");
    }

    @Override
    public void initView() {
        View emptyView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.order_empty_layout, null);
        viewModel.adapter.setUseEmpty(true);
        viewModel.adapter.setEmptyView(emptyView);
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.recycler_view_trade_user_hold);
//        RecyclerView.RecycledViewPool pool= new RecyclerView.RecycledViewPool();
//        pool.setMaxRecycledViews(0, 10); recyclerView.setRecycledViewPool(pool);
        LinearLayout llSwitchSymbol = getActivity().findViewById(R.id.ll_switchsymbol);
//        customSeekBar = (CustomSeekBar)getActivity().findViewById(R.id.seekbar);

        textView = (TextView)getActivity().findViewById(R.id.tv_positionercent);
        tvPlaceAnorder = (TextView)getActivity().findViewById(R.id.tv_placeanorder);

//        customSeekBar.setOnProgressChangedListener((seekBar, fromUser, isFinished) -> {
//            if (seekbarIntervals ==null ||seekbarIntervals.size()<=0) {
////               ToastUtil.show(getActivity(),"杠杆获取失败");
//            } else {
//                viewModel.getPositionPercent().set(Integer.valueOf(seekbarIntervals.get(seekBar.getProgress())));
//                viewModel.getTradeAmount().set(seekbarIntervals.get(seekBar.getProgress()));
//            }
//        });
        llSwitchSymbol.setOnClickListener(view -> {
                shoAddressDialog();
        });
        if (null!=getActivity().getIntent().getStringExtra("many")){
            if (getActivity().getIntent().getStringExtra("many").equals("0")){
                viewModel.getLeftCoin().set(getActivity().getIntent().getStringExtra("leftCoin"));
                viewModel.getRightCoin().set(getActivity().getIntent().getStringExtra("rightCoin"));
                viewModel.tradeBuy.set(true);
            }else {
                viewModel.tradeBuy.set(false);
                viewModel.getLeftCoin().set(getActivity().getIntent().getStringExtra("leftCoin"));
                viewModel.getRightCoin().set(getActivity().getIntent().getStringExtra("rightCoin"));
            }
        }
        applyAdapter = new TradeSymbolAdapter(mList);

        rlAska = (LinearLayout)getActivity().findViewById(R.id.rl_aska);
        rlAska1 = (LinearLayout)getActivity().findViewById(R.id.rl_aska1);
        rlAska2 = (LinearLayout)getActivity().findViewById(R.id.rl_aska2);
        rlAska3 = (LinearLayout)getActivity().findViewById(R.id.rl_aska3);
        rlAska4 = (LinearLayout)getActivity().findViewById(R.id.rl_aska4);

        rlBids = (LinearLayout)getActivity().findViewById(R.id.rl_bids);
        rlBids1 = (LinearLayout)getActivity().findViewById(R.id.rl_bids1);
        rlBids2 = (LinearLayout)getActivity().findViewById(R.id.rl_bids2);
        rlBids3 = (LinearLayout)getActivity().findViewById(R.id.rl_bids3);
        rlBids4 = (LinearLayout)getActivity().findViewById(R.id.rl_bids4);

        customSeekBar = getActivity().findViewById(R.id.test_seekbar);
        customSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
           if (array ==null ||array.length<=0) {
//               ToastUtil.show(getActivity(),"杠杆获取失败");
            } else {
                viewModel.getPositionPercent().set(Integer.valueOf(array[seekParams.thumbPosition]));
                viewModel.getTradeAmount().set(array[seekParams.thumbPosition]);
            }
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

    }
    /**
     * dp转为px
     *
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    public void initData(){
        DataService.getInstance().getInfoBySymbol(viewModel.getLeftCoin().get() + "/" + viewModel.getRightCoin().get()).compose(ResponseTransformer.<InfoBySymbolBean>handleResult()).subscribe(
               o -> {
                   Message message = new Message();
                   message.what = 2;
                   Bundle b = new Bundle();
                   b.putParcelable("obj", (Parcelable) o);
                   message.setData(b);
                   handler.sendMessage(message);
               }, t -> {
                    viewModel.getIsShow().set(false);
                    customSeekBar.setEnabled(false);
                    customSeekBar.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
               }
       );
   }
    public void onSymbolSet(String coinSymbol, String baseSymbol) {
        viewModel.getLeftCoin().set(coinSymbol);
        viewModel.getRightCoin().set(baseSymbol);
        viewModel.tradeSymbolConfig = SymbolConfigs.getInstance().getSymbol(viewModel.getLeftCoin().get() + "/" + viewModel.getRightCoin().get());
        viewModel.getFeeRates().set(String.valueOf(viewModel.tradeSymbolConfig.getFeeRates()));
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginInfo.isSign()) {
            initData();
        }else {
            customSeekBar.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getMarketList();
            }
        }, 1000, 1000);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
        viewModel.onPause();
    }
    @Override
    public void initViewObservable() {
        viewModel.getAska1().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlAska.getLayoutParams();
                params.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska2().get())*10);
//              rlAska.post(() -> rlAska.setLayoutParams(params));
                rlAska.setLayoutParams(params);

                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) rlAska1.getLayoutParams();
                params1.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska4().get())*10);
//                rlAska1.post(() -> rlAska1.setLayoutParams(params1));
                rlAska1.setLayoutParams(params1);

                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) rlAska2.getLayoutParams();
                params2.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska6().get())*10);
//                rlAska2.post(() -> rlAska2.setLayoutParams(params2));
                rlAska2.setLayoutParams(params2);

                RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) rlAska3.getLayoutParams();
                params3.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska8().get())*10);
//                rlAska3.post(() -> rlAska3.setLayoutParams(params3));
                rlAska3.setLayoutParams(params3);

                RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) rlAska4.getLayoutParams();
                params4.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska10().get())*10);
//                rlAska4.post(() -> rlAska4.setLayoutParams(params4));
                rlAska4.setLayoutParams(params4);

                RelativeLayout.LayoutParams params_1 = (RelativeLayout.LayoutParams) rlBids.getLayoutParams();
                params_1.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids2().get())*10);
//                rlBids.post(() -> rlBids.setLayoutParams(params_1));
                rlBids.setLayoutParams(params_1);

                RelativeLayout.LayoutParams params_2 = (RelativeLayout.LayoutParams) rlBids1.getLayoutParams();
                params_2.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids4().get())*10);
//                rlBids1.post(() -> rlBids1.setLayoutParams(params_2));
                rlBids1.setLayoutParams(params_2);

                RelativeLayout.LayoutParams params_3 = (RelativeLayout.LayoutParams) rlBids2.getLayoutParams();
                params_3.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids6().get())*10);
//                rlBids2.post(() -> rlBids2.setLayoutParams(params_3));
                rlBids2.setLayoutParams(params_3);

                RelativeLayout.LayoutParams params_4 = (RelativeLayout.LayoutParams) rlBids3.getLayoutParams();
                params_4.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids8().get())*10);
//                rlBids3.post(() -> rlBids3.setLayoutParams(params_4));
                rlBids3.setLayoutParams(params_4);

                RelativeLayout.LayoutParams params_5 = (RelativeLayout.LayoutParams) rlBids4.getLayoutParams();
                params_5.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids10().get())*10);
//                rlBids4.post(() -> rlBids4.setLayoutParams(params_5));
                rlBids4.setLayoutParams(params_5);
//                new Thread(new Runnable() {
//                    public void run() {
//                    handler.sendEmptyMessage(1);
//                    }
//                }).start();
            }
        });


       viewModel.getTrade().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
           @Override
           public void onPropertyChanged(Observable sender, int propertyId) {
           }
       });
        viewModel.seekbarIntervals.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
            }
        });
        viewModel.isShow.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                    if (viewModel.getIsShow().get() == false) {
                        tvPlaceAnorder.setEnabled(false);
                        tvPlaceAnorder.setBackground(getResources().getDrawable(R.drawable.shape_market_item_normal));
                        customSeekBar.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                    } else {
                        tvPlaceAnorder.setEnabled(true);
                        tvPlaceAnorder.setBackground(getResources().getDrawable(R.drawable.shape_trade_login_background));
                        customSeekBar.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }
            }
        });
        viewModel.getTradeAmount().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                viewModel.getServiceCharge().set(""+Float.valueOf(viewModel.getTradeAmount().get())*Float.valueOf(viewModel.getFeeRates().get()));
            }
        });
        viewModel.getmList().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                applyAdapter.addData(viewModel.getmList().get());
            }
        });
    }


    @SuppressLint("CheckResult")
    public void getMarketList() {
        DataService.getInstance().getMarketList().compose(ResponseTransformer.<List<MarketListBean>>handleResult()).subscribe(
                l -> {
                    if (null!=mList && mList.size()>0){
                        mList.clear();
                    }
                    applyAdapter.setNewData(l);
                }, t -> {
                }
        );
    }
    public  void shoAddressDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_withdrawpay,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        ImageView ivCancel = view .findViewById(R.id.iv_cancel);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(applyAdapter);
        applyAdapter.setOnItemClickListener((adapter, view1, position) -> {
            String newbean = applyAdapter.getData().get(position).getCoinId().substring(applyAdapter.getData().get(position).getCoinId().lastIndexOf("/")+1);
            String newbean_1= applyAdapter.getData().get(position).getCoinId().substring(0, applyAdapter.getData().get(position).getCoinId().indexOf("/"));
            viewModel.getLeftCoin().set(newbean_1);
            viewModel.getRightCoin().set(newbean);
            onResume();
            dialog.dismiss();
        });
        ivCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
//        dialog.getWindow().setBackgroundDrawableResource(R.color.color_282C42);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog); // 添加动画
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
//      dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams params =
                dialog.getWindow().getAttributes();
        params.width = 800;
        params.height = 1000;
        dialog.getWindow().setAttributes(params);

    }
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlAska.getLayoutParams();
                    params.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska2().get())*10);
                    rlAska.post(() -> rlAska.setLayoutParams(params));
                    RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) rlAska1.getLayoutParams();
                    params1.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska4().get())*10);
                    rlAska1.post(() -> rlAska1.setLayoutParams(params1));

                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) rlAska2.getLayoutParams();
                    params2.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska6().get())*10);
                    rlAska2.post(() -> rlAska2.setLayoutParams(params2));

                    RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) rlAska3.getLayoutParams();
                    params3.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska8().get())*10);
                    rlAska3.post(() -> rlAska3.setLayoutParams(params3));

                    RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) rlAska4.getLayoutParams();
                    params4.width = dip2px(getActivity(),Float.valueOf(viewModel.getAska10().get())*10);
                    rlAska4.post(() -> rlAska4.setLayoutParams(params4));

                    RelativeLayout.LayoutParams params_1 = (RelativeLayout.LayoutParams) rlBids.getLayoutParams();
                    params_1.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids2().get())*10);
                    rlBids.post(() -> rlBids.setLayoutParams(params_1));

                    RelativeLayout.LayoutParams params_2 = (RelativeLayout.LayoutParams) rlBids1.getLayoutParams();
                    params_2.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids4().get())*10);
                    rlBids1.post(() -> rlBids1.setLayoutParams(params_2));

                    RelativeLayout.LayoutParams params_3 = (RelativeLayout.LayoutParams) rlBids2.getLayoutParams();
                    params_3.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids6().get())*10);
                    rlBids2.post(() -> rlBids2.setLayoutParams(params_3));

                    RelativeLayout.LayoutParams params_4 = (RelativeLayout.LayoutParams) rlBids3.getLayoutParams();
                    params_4.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids8().get())*10);
                    rlBids3.post(() -> rlBids3.setLayoutParams(params_4));

                    RelativeLayout.LayoutParams params_5 = (RelativeLayout.LayoutParams) rlBids4.getLayoutParams();
                    params_5.width = dip2px(getActivity(),Float.valueOf(viewModel.getBids10().get())*10);
                    rlBids4.post(() -> rlBids4.setLayoutParams(params_5));
                    break;
                case 2:
                    InfoBySymbolBean obj = msg.getData().getParcelable("obj");
                    viewModel.getIsShow().set(true);
                    customSeekBar.setEnabled(true);
                    viewModel.leverageAdapter.setNewData(Arrays.asList(obj.getExchangeLevers().replaceAll(" ","").split(",")));
                    viewModel.getLeveraged().set(String.valueOf(viewModel.leverageAdapter.getData().get(0)).substring(0, String.valueOf(viewModel.leverageAdapter.getData().get(0)).indexOf("X")));
//                  seekbarIntervals = Arrays.asList(o.getExchangePrincipalPrice().replaceAll(" ","").split(","));
//                  viewModel.seekbarIntervals.set(seekbarIntervals);
                    customSeekBar.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    array = Arrays.asList(obj.getExchangePrincipalPrice().replaceAll(" ","").split(",")).toArray(new String[0]);
                    customSeekBar.customTickTexts(array);
                    customSeekBar.setTickCount(array.length);
                    viewModel.getPositionPercent().set(Integer.valueOf(array[0]));
                    if (customSeekBar.getProgress()>0){
                        customSeekBar.setProgress(0);
                    }
                    customSeekBar.invalidate();
                    break;
            }
        }
    };
}
