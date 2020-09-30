package com.temp.buda.fragments.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.DisplayUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.adapter.HomeBannerAdapter;
import com.temp.buda.adapter.HomeFunctionAdapter;
import com.temp.buda.adapter.PagerAdapter;
import com.temp.buda.bean.FuncListBean;
import com.temp.buda.bean.HomeFunctionBean;
import com.temp.buda.bean.NoticeBean;
import com.temp.buda.databinding.FragmentHomeBinding;
import com.temp.buda.net.ApiAddress;
import com.temp.buda.net.DataService;
import com.temp.buda.web.WebViewActivity;
import com.temp.buda.widget.PagingScrollHelper;
import com.temp.buda.widget.view.HomeViewFlipper;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.fragments.home
 * @FileName     : HomeFragment.java
 * @Author       : chao
 * @Date         : 2020/5/18
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
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomePageViewModel> implements PagingScrollHelper.onPageChangeListener {
    private ViewPager view_pager,functionViewPager;
    private PagerAdapter mAdapter,functionAdapter;
    private List<FuncListBean.AndroidBean> listBeans = new ArrayList<>();
    ArrayList<Fragment> list;
    private RelativeLayout rlMarketTitle;
    private List<NoticeBean.RowsBean> noticeList = new ArrayList<>();
    private HomeViewFlipper viewFlipper;
    private HomeFunctionAdapter homeFunctionAdapter;
    private ArrayList<HomeFunctionBean> homeFunctionList = new ArrayList<>();;
    private RollPagerView vp_roll;
    private HomeBannerAdapter homeBannerAdapter;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.homeViewModel;
    }

    @Override
    public void stopLoad() {
    }

    @Override
    public void lazyLoad() {
    }

    @Override
    public void initView() {
        initData();
        initViews();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onPause() {
            super.onPause();
        list.get(0).onPause();
    }

    private void initData() {
        Notice();
        ArrayList<Fragment> functionList = new ArrayList<>();
        functionViewPager = getActivity().findViewById(R.id.function_view_pager);
        functionAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), functionList);
        functionViewPager.setCurrentItem(0);  //初始化显示第一个页面
        CircleIndicator indicator2 = (CircleIndicator)getActivity(). findViewById(R.id.function_indicator);
//      showLoading(getActivity(),false);
        DataService.getInstance().getFuncList().compose(ResponseTransformer.<FuncListBean>handleResult()).subscribe(
                b -> {
                    dismissLoading();
                    listBeans = b.getAndroid();
//                    for (int i = 0; i < listBeans.size(); i++) {
//                        HomeFunctionBean data = new HomeFunctionBean(listBeans.get(i).getTitle(),listBeans.get(i).getContent(),listBeans.get(i).getIcon(),"",true);
//                        homeFunctionList.add(data);
//                        int j =0;
//                            if(i%2==0){
//                                j++;
                                functionList.add(new HomeFunctionFragment(0,listBeans));
                                functionViewPager.setAdapter(functionAdapter);
                                functionAdapter.notifyDataSetChanged();
                                indicator2.setViewPager(functionViewPager);
                                indicator2.setVisibility(View.GONE);
//                            }
//                    }
                }, t -> {
                    dismissLoading();
                    ToastUtil.show(getActivity(),t.getMessage());
                });
    }

    private void initViews() {
         LinearLayout   notice = getActivity().findViewById(R.id.notice);
         notice.setOnClickListener(view -> {
//             startActivity(NoticeActivity.class);
        });
//        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
//        PagingScrollHelper scrollHelper = new PagingScrollHelper();//初始化横向管理器
//        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 3);//这里两个参数是行列，这里实现的是一行三列
//        homeFunctionAdapter = new HomeFunctionAdapter(homeFunctionList,getActivity());//设置适配器
//        recyclerView.setAdapter(homeFunctionAdapter);
//        scrollHelper.setUpRecycleView(recyclerView);//将横向布局管理器和recycler view绑定到一起
//        scrollHelper.setOnPageChangeListener(this);//设置滑动监听
//        recyclerView.setLayoutManager(horizontalPageLayoutManager);//设置为横向
//        scrollHelper.updateLayoutManger();
//        scrollHelper.scrollToPosition(0);//默认滑动到第一页
//        recyclerView.setHorizontalScrollBarEnabled(true);

        list = new ArrayList<>();
        list.add(new HomeViewPagerFragment(0));
//      list.add(new HomeViewPagerFragment(1));
        view_pager = getActivity().findViewById(R.id.view_pager);
        mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), list);
        view_pager.setAdapter(mAdapter);
        view_pager.setCurrentItem(0);  //初始化显示第一个页面
        CircleIndicator indicator = (CircleIndicator)getActivity(). findViewById(R.id.indicator);
        indicator.setViewPager(view_pager);
        indicator.setVisibility(View.GONE);
        //初始化ViewPager
//        ArrayList<Fragment> functionList = new ArrayList<>();
//        functionList.add(new HomeFunctionFragment(0));
//        functionList.add(new HomeFunctionFragment(1));
//        functionViewPager = getActivity().findViewById(R.id.function_view_pager);
//        functionAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), functionList);
//        functionViewPager.setAdapter(functionAdapter);
//        functionViewPager.setCurrentItem(0);  //初始化显示第一个页面
//        CircleIndicator indicator2 = (CircleIndicator)getActivity(). findViewById(R.id.function_indicator);
//        indicator2.setViewPager(functionViewPager);
        ImageView iv_ip = getActivity().findViewById(R.id.iv_ip);
        iv_ip.setOnClickListener(view -> {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.KF_URL +  "/php/app.php?widget-mobile");
        startActivity(WebViewActivity.class, bundle);
        });
        vp_roll = getActivity().findViewById(R.id.vp_roll);
        vp_roll.setHintView(new ColorPointHintView(getActivity(), Color.WHITE, Color.GRAY));//设置指示器颜色
        vp_roll.setHintPadding(0,0, DisplayUtil.getScreenHardwareWidth(getActivity())/2 - 60,40);
        vp_roll.setOnItemClickListener(position -> {
            if (!TextUtils.isEmpty(viewModel.urls.get().get(position).getLink())){
                Bundle bundle = new Bundle();
                bundle.putString(WebViewActivity.KEY_PARAM_URL,viewModel.urls.get().get(position).getLink());
                startActivity(WebViewActivity.class, bundle);
            }
        });
        rlMarketTitle  = getActivity().findViewById(R.id.rl_market_title);
        viewFlipper = getActivity().findViewById(R.id.viewFlipper);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void initViewObservable() {
//        ArrayList<Drawable> list = new ArrayList<>();
//        list.add(getResources().getDrawable(R.mipmap.banner_1));
//        binding.banner.setViews(list);
//        binding.banner.setViewUrls(list);

//        binding.banner.setOnBannerItemClickListener(i -> {
//        });
        viewModel.urls.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                homeBannerAdapter = new HomeBannerAdapter(vp_roll, getActivity(), viewModel.urls.get());
                vp_roll.setAdapter(homeBannerAdapter);
                homeBannerAdapter.notifyDataSetChanged();
            }
        });

        viewModel.checkRank.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.checkRank.get()==3){
                    rlMarketTitle.setVisibility(View.VISIBLE);
                }else {
                    rlMarketTitle.setVisibility(View.GONE);
                }
            }
        });
        viewModel.list.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                if (viewModel.list.get()!=null&&viewModel.list.get().size()>0){
//                    ArrayList<Fragment> list = new ArrayList<>();
//                    view_pager = getActivity().findViewById(R.id.view_pager);
//                    mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), list);
//                    view_pager.setCurrentItem(0);  //初始化显示第一个页面
//                    CircleIndicator indicator = (CircleIndicator)getActivity(). findViewById(R.id.indicator);
//                    indicator.setViewPager(view_pager);
//
//                    int j =0;
//                    for (int i = 0; i < viewModel.list.get().size(); i++) {
//                        if(i%3==0){
//                          j++;
//                        list.add(new HomeViewPagerFragment(j-1));
//                        view_pager.setAdapter(mAdapter);
//                        functionAdapter.notifyDataSetChanged();
//               }
//            }
//         }
            }
        });
    }

    private void Notice (){
        DataService.getInstance().getNotice(1,1,1,1).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if (b.getRows()==null||b.getRows().size()<=0){
                        View itemVp = View.inflate(getActivity(), R.layout.item_view_flipper, null);
                        TextView tv_title = itemVp.findViewById(R.id.tv_title);
                        tv_title.setText("暂无公告");


                        
                    }else {
                        noticeList = b.getRows();
                        for (int i = 0; i < b.getRows().size(); i++) {
                            View itemVp = View.inflate(getActivity(), R.layout.item_view_flipper, null);
                            TextView tv_title = itemVp.findViewById(R.id.tv_title);
                            String announceTitle = b.getRows().get(i).getTitle();
                            int id = b.getRows().get(i).getId();
                            tv_title.setText(announceTitle);
                            itemVp.setOnClickListener(view -> {
                                Bundle bundle = new Bundle();
                                bundle.putString(WebViewActivity.KEY_PARAM_TITLE, "公告");
                                bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.WEB_URL +"/#/article?id="+id);
                                startActivity(WebViewActivity.class, bundle);
                            });
                            viewFlipper.addView(itemVp);
                        }
                        viewFlipper.startFlipping();
                    }
                }, t -> {
                    ToastUtil.show(getActivity(),"获取公告失败"+t.getMessage());
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageChange(int index) {
        //这里是配合圆点指示器实现的，可以忽略

    }
}
