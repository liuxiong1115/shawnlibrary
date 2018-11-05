package com.liuxiong.library.weights;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.liuxiong.library.R;


public class BaseDialog extends Dialog {
	private final int default_width = WindowManager.LayoutParams.WRAP_CONTENT;
	private final int default_height = WindowManager.LayoutParams.WRAP_CONTENT;
	private final int default_gravity = Gravity.CENTER;
	private final int default_anim = R.style.AnimBottom;

	public Context mContext;
	public Window mWindow;
	public View mRootView;
	public int mWidth = default_width;
	public int mHeight = default_height;
	public int mGravity = default_gravity;

	public int mAnimations = default_anim;

	public BaseDialog(Context context) {
		super(context , R.style.dialog);
		this.mContext = context;
		this.mWindow = getWindow();
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
	}

	/**
	 * 设置是否模态   默认点击空白区域不可消失（模态）
	 * @author 刘雄   2014-9-1 下午2:14:00
	 * @param isCancele
	 * @return void
	 */
	public void setOnTouchOutside(boolean isCancele){
		this.setCanceledOnTouchOutside(isCancele);
	}
	
	/**
	 * On dismiss
	 */
	protected void onDismiss() {		
	}
	
	/**
	 * On show
	 */
	protected void onShow() {		
	}
	
	/**
	 * seting Dialog Wight
	 * @author 刘雄   2015-5-22 下午3:17:12
	 * @param mWidth
	 * @return void
	 */
	protected void setWidth(int mWidth){
		this.mWidth = mWidth;
	}
	
	
	/**
	 * Seting Dialog height
	 * @author 刘雄   2015-5-22 下午3:17:37
	 * @param mHight
	 * @return void
	 */
	protected void setHeight(int mHight){
		this.mHeight = mHight;
	}
	
	/**
	 * Seting Dialog Gravity
	 * @author 刘雄   2015-5-22 下午3:20:20
	 * @param gravity
	 * @return void
	 */
	protected void setGravity(int gravity){
		this.mGravity = gravity;
	}


	public void setmAnimations(int rId){
		this.mAnimations = rId;
	}


	/**
	 * Set content view.
	 * 
	 * @param root Root view
	 */
	public void setContentView(View root) {
		this.mRootView = root;
		super.setContentView(root);
	}
	
	
	 public void setContentView(int layoutResID) {
	        super.setContentView(layoutResID);
	    }
	
	/**
	 * On pre show
	 */
	protected void preShow() {
		if (mRootView == null) 
			throw new IllegalStateException("setContentView was not called with a view to display.");
		onShow();
		WindowManager.LayoutParams params = mWindow.getAttributes();
		float density = getDensity(mContext);
//		params.width = (int) (mWidth * density);
//		params.height = (int) (mHeight * density);
		params.width = mWidth;
		params.height = mHeight;
		params.gravity = mGravity;
		mWindow.setWindowAnimations(mAnimations); //设置窗口弹出动画
		mWindow.setAttributes(params);
	}
	
	
	// 取出设备像素值
	private float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}
	
	public interface DialogButtonClickCallBack {
		/**
		 * 按钮点击回调<br>
		 * 通过View。getId来确定用户点击的是哪个按钮<br>
		 * 目前有以下几个按钮  <br>
		 * 			下一题   R.id.next_test_button<br>
		 * 			再答       R.id.again_answer_button<br>
		 * 			返回主页 R.id.go_home_button <br>
		 * 				
		 * 
		 * */
		public void onClick(View view);
	}
}
