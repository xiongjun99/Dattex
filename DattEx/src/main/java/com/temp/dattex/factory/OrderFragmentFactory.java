package com.temp.dattex.factory;

import androidx.fragment.app.Fragment;

import com.temp.dattex.Constants;
import com.temp.dattex.order.fragment.CurrentOrderFragment;
import com.temp.dattex.order.fragment.HistoryOrderFragment;

import java.util.LinkedHashMap;

/**
 * @Package: com.temp.dattex.factory
 * @ClassName: OrderFragmentFactory
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 15:41
 * @Email: 86152
 */
public class OrderFragmentFactory {

    public static final String TITLE_CURRENT = "当前订单";
    public static final String TITLE_HISTORY = "历史订单";
    public static final int SIZE = 2;
    private static LinkedHashMap<Integer, Fragment> fragments = new LinkedHashMap<>();
    private static LinkedHashMap<Integer, String> titles = new LinkedHashMap<>();

    public static String getTitles(int position) {
        String title = titles.get(position);
        if (title == null) {
            switch (position) {
                case Constants.INDEX_ZEO:
                    title = TITLE_CURRENT;
                    break;
                case Constants.INDEX_ONE:
                    title = TITLE_HISTORY;
                    break;
            }
            titles.put(position,title);
        }
        return title;
    }


    public static Fragment getFragment(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment == null) {
            switch (position) {
                case Constants.INDEX_ZEO:
                    fragment = new CurrentOrderFragment();
                    break;
                case Constants.INDEX_ONE:
                    fragment = new HistoryOrderFragment();
                    break;
            }
            fragments.put(position, fragment);
        }
        return fragment;
    }
}
