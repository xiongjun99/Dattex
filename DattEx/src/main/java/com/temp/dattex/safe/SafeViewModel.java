package com.temp.dattex.safe;

import android.app.Application;

import androidx.annotation.NonNull;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
<<<<<<< HEAD:DattEx/src/main/java/com/temp/dattex/safe/SafeViewModel.java
import com.temp.dattex.resetpwd.ResetPasswordActivity;
=======
import com.temp.buda.resetpwd.ResetPasswordActivity;
import com.temp.buda.resetpwd.modifyPasswordActivity;
>>>>>>> master:DattEx/src/main/java/com/temp/buda/safe/SafeViewModel.java

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.safe
 * @FileName     : SafeViewModel.java
 * @Author       : chao
 * @Date         : 2020/6/20
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
public class SafeViewModel extends BaseViewModel {
    public SafeViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void resetPassword() {
        startActivity(modifyPasswordActivity.class);
    }
}
