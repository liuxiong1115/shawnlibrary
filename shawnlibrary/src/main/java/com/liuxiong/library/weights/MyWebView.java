package com.liuxiong.library.weights;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liuxiong.library.R;
import com.orhanobut.logger.Logger;

public class MyWebView extends WebView {
	public static String JavascriptMethed = "javascript:showSolution";

	private ProgressBar progressbar;

	private Context mContext;

	private String webTitle;

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MyWebView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		this.mContext = context;

//		DensityUtil densityUtil = new DensityUtil(context);
		progressbar = (ProgressBar)LayoutInflater.from(context).inflate(R.layout.progress_bar , null);
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0, -18));
		addView(progressbar);

		this.getSettings().setDefaultTextEncodingName("GBK");
		this.getSettings().setJavaScriptEnabled(true); // 允许JS执行
		this.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// 不使用缓存
		// 增加接口方法,让html页面调用
		this.addJavascriptInterface(this, "JavascriptInterface");
		this.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);//点击超链接的时候重新在原来进程上加载URL
				return true;
			}
		});

		setWebChromeClient(new WebChromeClient());
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				progressbar.setVisibility(GONE);
			} else {
				if (progressbar.getVisibility() == GONE)
					progressbar.setVisibility(VISIBLE);
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			Logger.d("Web Title=" + title);
			webTitle = title;

			if(mOnWebViewListener != null)
				mOnWebViewListener.onReceivedTitle(MyWebView.this , title);
		}

	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}


	public String getWebTitle(){
		return webTitle;
	}


	public interface OnWebViewListener{
		public void onReceivedTitle(MyWebView view, String title);
	}

	OnWebViewListener mOnWebViewListener;

	public void setOnWebViewListener(OnWebViewListener listener){
		this.mOnWebViewListener = listener;
	}





//	/**
//	 * JS调用本地代码方法名
//	 *
//	 * @author 刘雄 2015-5-5 上午9:20:39
//	 * @return void
//	 */
//	public void startFunction() {
//		MyToast.makeText(mContext, MyToast.TYPE_SUCCEED, "请求成功！", Toast.LENGTH_SHORT).show();
//	}
}
