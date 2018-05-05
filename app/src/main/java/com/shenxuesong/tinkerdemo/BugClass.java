package com.shenxuesong.tinkerdemo;

import android.content.Context;
import android.util.Log;

import com.meituan.android.walle.ChannelInfo;
import com.meituan.android.walle.WalleChannelReader;

/**
 * 测试bug类.
 *
 * @author devilwwj
 * @since 2016/10/25
 */
public class BugClass {
    // 这段代码会报空指针异常
    public String bug(Context context) {

        // 这段代码会报空指针异常
       /* String str = null;
        Log.e("BugClass", "get String length:" + str.length());*/

        // return "这是一个很糟糕的Bug类";
        return "这个bug已经被修复了，开心";
    }

}
