package com.temp.dattex.database;

import android.text.TextUtils;

import com.exchange.utilslib.SPUtil;
import com.temp.dattex.Constants;

/**
 * @Package: com.temp.dattex.database
 * @ClassName: LoginInfo
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 22:25
 * @Email: 86152
 */
public class LoginInfo {

    public static boolean sign = false;


    /**
     * 登录
     *
     * @param account
     * @param token
     * @param memberId
     * @param recode
     */
    public static void sign(String passWord,String account, String token, int memberId, String recode,String isCertification) {
        SPUtil.setParam(Constants.SP_KEY_LOGIN_ACCOUNT, account);
        SPUtil.setParam(Constants.SP_KEY_LOGIN_TOKEN, token);
        SPUtil.setParam(Constants.SP_KEY_LOGIN_MEMBERID, memberId);
        SPUtil.setParam(Constants.SP_KEY_LOGIN_RECODE, recode);
        SPUtil.setParam(Constants.SP_KEY_LOGIN_ISCERTIFICATION, isCertification);
        SPUtil.setParam(Constants.SP_KEY_PASSWORD, passWord);

    }

    /**
     * 登出
     */
    public static void signOut() {
        SPUtil.setParam(Constants.SP_KEY_LOGIN_ACCOUNT, "");
        SPUtil.setParam(Constants.SP_KEY_LOGIN_TOKEN, "");
        SPUtil.setParam(Constants.SP_KEY_LOGIN_MEMBERID, -1);
        SPUtil.setParam(Constants.SP_KEY_LOGIN_RECODE, "");
        SPUtil.setParam(Constants.SP_KEY_PASSWORD, "");

    }

    /**
     * 用户登录token
     *
     * @return
     */
    public static String getUserToken() {
        return (String) SPUtil.getParam(Constants.SP_KEY_LOGIN_TOKEN, "");
    }

    /**
     * 是否是登录状态
     *
     * @return
     */
    public static boolean isSign() {
        return !TextUtils.isEmpty((CharSequence) SPUtil.getParam(Constants.SP_KEY_LOGIN_TOKEN, ""));
    }

    public static String getAccount() {
        return (String) SPUtil.getParam(Constants.SP_KEY_LOGIN_ACCOUNT, "");
    }
    public static String isCertification() {
        return (String) SPUtil.getParam(Constants.SP_KEY_LOGIN_ISCERTIFICATION, "");
    }
    public static void  getisCertification(String isCertification) {
        SPUtil.setParam(Constants.SP_KEY_LOGIN_ISCERTIFICATION, isCertification);
    }
    public static String getRecode() {
        return (String) SPUtil.getParam(Constants.SP_KEY_LOGIN_RECODE, "");
    }
    public static String getPassWord() {
        return (String) SPUtil.getParam(Constants.SP_KEY_PASSWORD, "");
    }
}
