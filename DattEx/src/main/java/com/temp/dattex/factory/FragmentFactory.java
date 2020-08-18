package com.temp.dattex.factory;

import androidx.fragment.app.Fragment;

import com.temp.dattex.Constants;
import com.temp.dattex.fragments.home.HomeFragment;
import com.temp.dattex.fragments.market.MarketFragment;
import com.temp.dattex.fragments.my.MyFragment;
import com.temp.dattex.fragments.trade.TradeFragment;

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
