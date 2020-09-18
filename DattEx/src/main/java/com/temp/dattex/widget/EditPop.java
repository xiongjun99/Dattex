package com.temp.dattex.widget;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.temp.dattex.Application;
import com.temp.dattex.R;
import com.temp.dattex.databinding.PopuItemBinding;

import java.util.List;

/**
 * Created by 李嘉伦.
 * Date: 2020/5/15
 * Time: 上午 11:25
 */
public class EditPop extends ListPopupWindow {


    private final Context context;
    private final int position;

    public EditPop(@NonNull Context context, int position) {
        super(context);
        this.context = context;
        this.position = position;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAdapterData(List<String> list, View view) {
        ArrayAdapter<String> poAdapter = new ArrayAdapter<>(Application.getInstance(), R.layout.popu_item, list);
        this.setAdapter(poAdapter);
        this.setAnchorView(view);
        this.setModal(false);
        this.setListSelector(context.getResources().getDrawable(R.drawable.pop_selector));
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_pop));
    }


    /**
     * 页面的弹出
     *
     * @param isShow
     */
    public void popState(boolean isShow) {
        if (isShow) {
            this.show();
        } else {
            this.dismiss();
        }
    }

}
