package com.temp.dattex.fragments.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.common.framework.basic.BaseFragment;
import com.independ.framework.client.RetrofitClient;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.PagerAdapter;
import com.temp.dattex.bean.FuncListBean;
import com.temp.dattex.databinding.FragmentHomeBinding;
import com.temp.dattex.util.Utils;
import com.temp.dattex.widget.imageloader.GlideImageLoader;

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
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeViewPagerFragment(0));
//        list.add(new HomeViewPagerFragment(1));
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
       });
    }
    @Override
    public void initViewObservable() {
        binding.banner.setImageLoader(new GlideImageLoader());
        viewModel.urls.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.urls.get()!=null&&viewModel.urls.get().size()>0){
                    binding.banner.setViewUrls(viewModel.urls.get());
                } else {
                    List<String> urls = new ArrayList<>();
                    urls.add("http://a1.qpic.cn/psc?/V14aPuF23Yir43/bqQfVz5yrrGYSXMvKr.cqQGQ6g8Mm*ZRB6HskI7bacRjCnxjmogVHkn4ZfjYbDrLxhmyVM4ZhjRjT.drVXke9SJ4DzShvzYC8qEm*eDwsj4!/b&ek=1&kp=1&pt=0&bo=ZQTQAgAAAAADN6E!&tl=1&vuin=258325667&tm=1598256000&sce=60-2-2&rf=viewer_4");
                    binding.banner.setViewUrls(urls);
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
