package com.temp.dattex.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.temp.dattex.factory.OrderFragmentFactory;

/**
 * @Package: com.temp.dattex.adapter
 * @ClassName: OrderFragmentAdapter
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 15:39
 * @Email: 86152
 */

public class OrderFragmentAdapter extends FragmentStatePagerAdapter {

    public OrderFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return OrderFragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return OrderFragmentFactory.SIZE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return OrderFragmentFactory.getTitles(position);
    }
}
