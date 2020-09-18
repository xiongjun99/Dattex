package com.temp.buda.factory;

import androidx.fragment.app.Fragment;

import com.temp.buda.Constants;
import com.temp.buda.fragments.home.HomeFragment;
import com.temp.buda.fragments.market.MarketFragment;
import com.temp.buda.fragments.my.MyFragment;
import com.temp.buda.fragments.trade.TradeFragment;

import java.util.LinkedHashMap;

/**
 * @Package: com.temp.dattex.factory
 * @ClassName: FragmentFactory
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 18:21
 * @Email: 86152
 */
public class FragmentFactory {

    public static final int SIZE = 4;
    private static LinkedHashMap<Integer, Fragment> fragments = new LinkedHashMap<>();


    public static Fragment getFragment(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment == null) {
            switch (position) {
                case Constants.INDEX_ZEO:
                    fragment = new HomeFragment();
                    break;
                case Constants.INDEX_ONE:
                    fragment = new MarketFragment();
                    break;
                case Constants.INDEX_TWO:
                    fragment = new TradeFragment();
                    break;
                case Constants.INDEX_THREE:
                    fragment = new MyFragment();
                    break;
            }
            fragments.put(position, fragment);
        }
        return fragment;
    }


}
