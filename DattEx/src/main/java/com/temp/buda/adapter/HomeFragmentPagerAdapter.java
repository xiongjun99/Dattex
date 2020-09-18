package com.temp.buda.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.temp.buda.factory.FragmentFactory;

/**
 * @Package: com.common.framework.basic
 * @ClassName: BaseFragmentPagerAdapter
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 17:25
 * @Email: 86152
 */

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentFactory.SIZE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
