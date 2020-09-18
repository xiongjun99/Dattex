package com.temp.dattex.binding.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

/**
 * @Package: com.temp.dattex.binding.adapter
 * @ClassName: EditTextBinding
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/14 23:28
 * @Email: 86152
 */
public class EditTextBinding {
    @BindingAdapter("onTextChange")
    public static void setOnTextChange(EditText editText, ITextWatcher iTextWatcher) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (iTextWatcher != null) {
                    iTextWatcher.afterTextChanged(s);
                }
            }
        });
    }

    public interface ITextWatcher {
        void afterTextChanged(Editable s);
    }

    @BindingAdapter("afterTextChanged")
    public static void setOnTextChange(EditText editText, EditListener listener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != listener) {
                    listener.afterTextChanged(editText, null == s ? "" : s.toString());
                }
            }
        });
    }

    public interface EditListener {
        void afterTextChanged(EditText view, String s);
    }

}
