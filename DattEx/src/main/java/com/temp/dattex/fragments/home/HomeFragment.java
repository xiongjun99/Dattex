package com.temp.dattex.fragments.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.DisplayUtil;
import com.exchange.utilslib.SPUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.ImageNormalAdapter;
import com.temp.dattex.adapter.PagerAdapter;
import com.temp.dattex.bean.FuncListBean;
import com.temp.dattex.databinding.FragmentHomeBinding;
import com.temp.dattex.util.Utils;

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
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomePageViewModel> {
    private ViewPager view_pager,functionViewPager;
    private PagerAdapter mAdapter,functionAdapter;
    private List<FuncListBean.AndroidBean> listBeans = new ArrayList<>();
    ArrayList<Fragment> list;
    private RelativeLayout rlMarketTitle;
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
    public void onPause() {
        super.onPause();
        list.get(0).onPause();
    }

    @SuppressLint("CheckResult")
    private void initData() {
//        functionViewPager = getActivity().findViewById(R.id.function_view_pager);
//        ArrayList<Fragment> functionList = new ArrayList<>();
//        functionAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), functionList);
//        functionViewPager.setCurrentItem(0);  //初始化显示第一个页面
//        CircleIndicator indicator2 = (CircleIndicator)getActivity(). findViewById(R.id.function_indicator);
//        indicator2.setViewPager(functionViewPager);
//        int j =0;
//        for (int i = 0; i < 3; i++) {
//            if(i%2==0){
//                j++;
//                functionList.add(new HomeFunctionFragment(j-1));
//                functionViewPager.setAdapter(functionAdapter);
//                functionAdapter.notifyDataSetChanged();
//            }
//        }


//        showLoading(getActivity(),false);
//        DataService.getInstance().getFuncList().compose(ResponseTransformer.<FuncListBean>handleResult()).subscribe(
//                b -> {
//                    dismissLoading();
//                    listBeans = b.getAndroid();
//                    for (int i = 0; i < listBeans.size(); i++) {
//                        if(4%2==0){
//                            if (i >0 ){
//                                System.out.println("-----yesaa"+(i-1));
//                            }else {
//                                System.out.println("-----yesbb"+i);
//                            }
//                        }else {
//                            System.out.println("-----ccccc"+i);
//                        }
//                    }
//                }, t -> {
//                    dismissLoading();
//                    ToastUtil.show(getActivity(),t.getMessage());
//                });
    }

    private void initViews() {
        list = new ArrayList<>();
        list.add(new HomeViewPagerFragment(0));
//      list.add(new HomeViewPagerFragment(1));
        view_pager = getActivity().findViewById(R.id.view_pager);
        mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), list);
        view_pager.setAdapter(mAdapter);
        view_pager.setCurrentItem(0);  //初始化显示第一个页面
        CircleIndicator indicator = (CircleIndicator)getActivity(). findViewById(R.id.indicator);
        indicator.setViewPager(view_pager);
        //初始化ViewPager
        ArrayList<Fragment> functionList = new ArrayList<>();
        functionList.add(new HomeFunctionFragment(0));
        functionList.add(new HomeFunctionFragment(1));
        functionViewPager = getActivity().findViewById(R.id.function_view_pager);
        functionAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), functionList);
        functionViewPager.setAdapter(functionAdapter);
        functionViewPager.setCurrentItem(0);  //初始化显示第一个页面
        CircleIndicator indicator2 = (CircleIndicator)getActivity(). findViewById(R.id.function_indicator);
        indicator2.setViewPager(functionViewPager);
        ImageView iv_ip = getActivity().findViewById(R.id.iv_ip);
        iv_ip.setOnClickListener(view -> {
            ToastUtil.show(getActivity(),"暂未开通");
        });
        RollPagerView vp_roll = getActivity().findViewById(R.id.vp_roll);
        vp_roll.setAdapter(new ImageNormalAdapter());//设置适配器
        vp_roll.setHintView(new ColorPointHintView(getActivity(), Color.WHITE, Color.GRAY));//设置指示器颜色
        vp_roll.setHintPadding(0,0, DisplayUtil.getScreenHardwareWidth(getActivity())/2 - 60,40);

        rlMarketTitle  = getActivity().findViewById(R.id.rl_market_title);
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
//        viewModel.urls.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable sender, int propertyId) {
//                if (viewModel.urls.get()!=null&&viewModel.urls.get().size()>0){
//                    binding.banner.setViewUrls(viewModel.urls.get());
//                } else {
//                    viewModel.urls.get().add("http://m.qpic.cn/psc?/V14aPuF23M185L/bqQfVz5yrrGYSXMvKr.cqXR4CUi4.45EWI.8oWrZCG7mAJ1iDASm3J*dIB1rQVR*344D21s*jaYH0LpEOT6Sd9iqsWccu2xXKiqDWjrKKPs!/b&bo=ZQTQAgAAAAADB5E!&rf=viewer_4");
//                    viewModel.urls.get().add("http://m.qpic.cn/psc?/V14aPuF23M185L/TmEUgtj9EK6.7V8ajmQrEAB1gaYCDKz48yMV0PUJsSslcZeQZeCESCq4SDw8W2tnSflniXSjv9Mz76zornBskwJMuOvn4d6hFE5Uoa.u2Mc!/b&bo=ZQTQAgAAAAADN6E!&rf=viewer_4");
//                    binding.banner.setViewUrls(viewModel.urls.get());
//                }
//            }
//        }

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

}
