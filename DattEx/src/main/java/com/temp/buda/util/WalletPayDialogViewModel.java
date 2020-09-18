package com.temp.buda.util;

import android.app.Dialog;

import com.common.framework.click.SingleClick;

public class WalletPayDialogViewModel{

        private Dialog dialog;

        public Dialog getDialog() {
            return dialog;
        }

        public void setDialog(Dialog dialog) {
            this.dialog = dialog;
        }

        @SingleClick
        public void closeDialog() {
            if (null != getDialog()) {
                getDialog().dismiss();
                setDialog(null);
            }
        }
}
