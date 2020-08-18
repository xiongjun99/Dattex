package com.temp.dattex.widget.sort;

import androidx.databinding.ObservableField;

import com.common.framework.click.SingleClick;
import com.temp.dattex.R;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.widget.sort
 * @FileName     : MarketSortViewModel.java
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
public class MarketSortViewModel {

    private enum SortState {

        Normal, PriceAscending, PriceDescending, ChangeAscending, ChangeDescending
    }

    private ObservableField<Integer> sortPriceImageResource = new ObservableField<>(R.mipmap.icon_sort_normal);
    private ObservableField<Integer> sortChangeImageResource = new ObservableField<>(R.mipmap.icon_sort_normal);
    private ObservableField<SortState> sortStateObservableField = new ObservableField<>(SortState.Normal);

    private MarketSortView.OnSortChangeListener onSortChangeListener;

    public ObservableField<SortState> getSortStateObservableField() {
        return sortStateObservableField;
    }

    public void setSortStateObservableField(ObservableField<SortState> sortStateObservableField) {
        this.sortStateObservableField = sortStateObservableField;
    }

    public ObservableField<Integer> getSortPriceImageResource() {
        return sortPriceImageResource;
    }

    public void setSortPriceImageResource(ObservableField<Integer> sortPriceImageResource) {
        this.sortPriceImageResource = sortPriceImageResource;
    }

    public ObservableField<Integer> getSortChangeImageResource() {
        return sortChangeImageResource;
    }

    public void setSortChangeImageResource(ObservableField<Integer> sortChangeImageResource) {
        this.sortChangeImageResource = sortChangeImageResource;
    }

    public void setOnSortChangeListener(MarketSortView.OnSortChangeListener onSortChangeListener) {
        this.onSortChangeListener = onSortChangeListener;
    }

    @SingleClick
    public void priceSort() {
        switch (sortStateObservableField.get()) {
            case PriceDescending:
                sortPriceImageResource.set(R.mipmap.icon_sort_normal);
                sortStateObservableField.set(SortState.Normal);
                onSortChangeListener.sortNormal();
                break;
            case PriceAscending:
                sortPriceImageResource.set(R.mipmap.icon_sort_down);
                sortStateObservableField.set(SortState.PriceDescending);
                onSortChangeListener.priceDescending();
                break;
            default:
                sortPriceImageResource.set(R.mipmap.icon_sort_up);
                sortStateObservableField.set(SortState.PriceAscending);
                onSortChangeListener.priceAscending();
                break;
        }
    }

    @SingleClick
    public void changeSort() {
        switch (sortStateObservableField.get()) {
            case ChangeDescending:
                sortPriceImageResource.set(R.mipmap.icon_sort_normal);
                sortStateObservableField.set(SortState.Normal);
                onSortChangeListener.sortNormal();
                break;
            case ChangeAscending:
                sortPriceImageResource.set(R.mipmap.icon_sort_down);
                sortStateObservableField.set(SortState.ChangeDescending);
                onSortChangeListener.changeDescending();
                break;
            default:
                sortPriceImageResource.set(R.mipmap.icon_sort_up);
                sortStateObservableField.set(SortState.ChangeAscending);
                onSortChangeListener.changeAscending();
                break;
        }
    }
}
