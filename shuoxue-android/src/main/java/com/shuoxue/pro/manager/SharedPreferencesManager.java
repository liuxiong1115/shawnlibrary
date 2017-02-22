package com.shuoxue.pro.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.shuoxue.pro.MyApplication;
import com.shuoxue.pro.model.User;
import com.shuoxue.pro.model.UserToken;


public class SharedPreferencesManager {

	public static final String Token_ID = "Token_ID";
	public static final String Token_String = "Token";
	public static final String Token_Time = "Token_Time";



	public static final String User_Account = "account";
	public static final String User_Pwd = "pwd";


	private static SharedPreferencesManager sharedPreferencesManager;

	public static SharedPreferencesManager getInstance() {
		if (sharedPreferencesManager == null)
			sharedPreferencesManager = new SharedPreferencesManager();
		return sharedPreferencesManager;
	}


	/**
	 *
	 * @param account
	 * @param pwd
     */
	public void updateUserInfo(String account ,  String pwd){
		SharedPreferences preferences = MyApplication.getContext().getSharedPreferences(
				MyApplication.getContext().getPackageName(), Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(User_Account, account);
		editor.putString(User_Pwd, pwd);
		editor.commit();
	}


	/**
	 *
	 * @return
     */
	public User getUserInfo(){
		SharedPreferences preferences = MyApplication.getContext().getSharedPreferences(
				MyApplication.getContext().getPackageName(), Context.MODE_PRIVATE);
		String account = preferences.getString(User_Account, "");
		String pwd = preferences.getString(User_Pwd, "");

		if(!TextUtils.isEmpty(account)){
			User user = new User();
			user.setAccount(account);
			user.setPwd(pwd);
			return user;
		}
		return null;
	}


	/**
	 * 缓存Token
	 * @param
	 * @param token
	 * @param time
	 */
	public void updateToken(String id ,  String token , long time){
		SharedPreferences preferences = MyApplication.getContext().getSharedPreferences(
				MyApplication.getContext().getPackageName(), Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Token_ID, id);
		editor.putString(Token_String, token);
		editor.putLong(Token_Time, time);
		editor.commit();
	}


	/**
	 * 获取Token
	 * @param
	 * @return
	 */
	public UserToken getToken(){
		SharedPreferences preferences = MyApplication.getContext().getSharedPreferences(
				MyApplication.getContext().getPackageName(), Context.MODE_PRIVATE);
		String id = preferences.getString(Token_ID, "");
		String token = preferences.getString(Token_String, "000000000000000000");
		long time = preferences.getLong(Token_Time, 0);

		UserToken userToken = new UserToken();
		userToken.setUserId(id);
		userToken.setToken(token);
//		userToken.setTime(time);
		return userToken;
	}




	/**
	 * 清除零时数据
	 * 
	 * @author 刘雄 2015-1-13 下午5:59:17
	 * @param
	 * @return void
	 */
	public void deleteSharedPreferences() {
		SharedPreferences preferences = MyApplication.getContext().getSharedPreferences(
				MyApplication.getContext().getPackageName(), Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}

}
