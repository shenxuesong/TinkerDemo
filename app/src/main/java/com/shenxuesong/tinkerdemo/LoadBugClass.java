package com.shenxuesong.tinkerdemo;

import android.content.Context;

/**
 * 加载Bug的类.
 *
 * @author devilwwj
 * @since 2016/10/25
 */
public class LoadBugClass {

    /**
     * 获取bug字符串.
     *
     * @return 返回bug字符串
     */
    public static String getBugString(Context context) {
        BugClass bugClass = new BugClass();
        return bugClass.bug(context);
    }
}
