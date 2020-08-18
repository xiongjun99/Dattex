package com.temp.dattex.fragments.home;

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
import com.temp.dattex.Application;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.PagerAdapter;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.databinding.FragmentHomeBinding;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.util.Utils;

import java.util.ArrayList;
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

    private ViewPager view_pager;
    private PagerAdapter mAdapter;
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
        initViews();
    }
    private void initViews() {
        //初始化ViewPager
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeViewPagerFragment("0"));
        list.add(new HomeViewPagerFragment("1"));
        view_pager = getActivity().findViewById(R.id.view_pager);
        mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), list);
        view_pager.setAdapter(mAdapter);
        view_pager.setCurrentItem(0);  //初始化显示第一个页面
        CircleIndicator indicator = (CircleIndicator)getActivity(). findViewById(R.id.indicator);
        indicator.setViewPager(view_pager);

       ImageView iv_ip = getActivity().findViewById(R.id.iv_ip);
       iv_ip.setOnClickListener(view -> {
           showDialog();
       });
    }

    public  void showDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_ipadress,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        EditText etIpadress = view.findViewById(R.id.et_ipadress);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvCancel.setText("取消");
        tvConfirm.setText("确定");

        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetrofitClient.getInstance().initRetrofit(ApiAddress.BASE_URL);
                RetrofitClient.getInstance().initRetrofit("http://"+etIpadress.getText().toString());
                //... To-do
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(getActivity())/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public void initViewObservable() {
        viewModel.urls.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.urls.get()!=null&&viewModel.urls.get().size()>0){
                    binding.banner.setViewUrls(viewModel.urls.get());
                }else {
                    binding.banner.setBackground(getActivity().getResources().getDrawable(R.mipmap.banner));
                }
            }
        });
    }
}
