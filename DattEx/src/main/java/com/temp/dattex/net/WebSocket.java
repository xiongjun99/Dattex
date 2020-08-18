package com.temp.dattex.net;

import com.exchange.utilslib.LogUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.net
 * @FileName     : WebSocket.java
 * @Author       : chao
 * @Date         : 2020/6/5
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
public class WebSocket extends WebSocketListener {

    private okhttp3.WebSocket socketClient;

    private WebSocket() {
    }

    private static volatile WebSocket webSocket;

    public static WebSocket getInstance() {
        if (null == webSocket) {
            synchronized (WebSocket.class) {
                if (null == webSocket) {
                    webSocket = new WebSocket();
                }
            }
        }
        return webSocket;
    }

    public void init(String ws) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(ws).build();
        socketClient = client.newWebSocket(request, this);
    }

    @Override
    public void onOpen(okhttp3.WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        if (null != socketListener) {
            socketListener.open();
        }
    }

    @Override
    public void onMessage(okhttp3.WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        if (null != socketListener) {
            socketListener.onMessage(text);
        }
//        LogUtil.e(text);
    }

    @Override
    public void onClosing(okhttp3.WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        if (null != socketListener) {
            socketListener.onClosed();
        }
    }

    @Override
    public void onFailure(okhttp3.WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        if (null != socketListener) {
            socketListener.onFailure();
        }
    }

    private SocketListener socketListener;

    public void setSocketListener(SocketListener socketListener) {
        this.socketListener = socketListener;
    }

    public interface SocketListener {

        void open();

        void onMessage(String message);

        void onClosed();

        void onFailure();
    }
}
