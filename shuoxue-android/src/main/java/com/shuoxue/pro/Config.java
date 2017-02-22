package com.shuoxue.pro;

import com.liuxiong.library.BaseConf;

/**
 * 作者：刘雄  2015/10/23 0023.17 05
 * 邮箱：liuxiong1115@gmail.com
 */
public class Config extends BaseConf{
    //家=5D:C4:B4:C4:FA:4E:69:04:4F:C0:83:05:32:FF:00:72:F6:CC:93:85
    //工=24:5E:96:97:94:08:D9:74:77:8D:88:4F:43:92:86:64:71:F4:19:2F



    public static final int DEFAULT_START_PAGE = 1;
    public static final int PAGE_SIZE = 10;

    public static final String ThumbPath = "/thumb";
    public static final String PathSlash = "/";
    public static final String ThumbSuffix = ".png";
    public static final String ThumbName = "thumb";


    public static int RESEND_SMS_TIME = 60 * 1000;

    /**
     * 每隔五分钟发送一次心跳机制
     */
    public static final int HEART_SKIP_NUMBER = 5 * 60 * 1000;


}
