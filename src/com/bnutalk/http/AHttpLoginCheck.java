package com.bnutalk.http;

import org.apache.http.Header;

//import com.bnutalk.cache.PrefCacheUserInfo;
import com.bnutalk.ui.LoginActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class AHttpLoginCheck {
	private String uid;
	private String passwd;
	private String result;
	private Handler handler;
	private Message msg;
	private Context context;
	public AHttpLoginCheck(Handler handler, String uid, String passwd) {
		this.uid = uid;
		this.passwd = passwd;
		this.handler = handler;
	}

	public void doLoginCheck() {
		String ip = new GetServerIp().getServerIp();
		String url = "http://" + ip + ":8080/web/LogServlet";
		RequestParams params = new RequestParams();
		params.put("uid", uid);
		params.put("passwd", passwd);
		AsyncHttpClient client = new AsyncHttpClient();
		final Message msg = new Message();
		client.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int status, Header[] headers, byte[] responseBody) {
				// called when response HTTP status is "200 OK"
				result = new String(responseBody);
				System.out.println(result);
				if (result.equals("success"))
					msg.what = 1;// user info is right
				else {
					msg.what = 2;// 表示登录失败
				}
				System.out.println("what"+" "+msg.what);
				handler.sendMessage(msg);
				
				/*write uid and passwd to local cache*/
			}

			@Override
			public void onFailure(int status, Header[] header, byte[] eResponseBody, Throwable error) {
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
				error.printStackTrace();
			}
		});
		
	}
	
}
