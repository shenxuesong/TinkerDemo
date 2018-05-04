package com.shenxuesong.tinkerdemo;

import android.util.Log;

/**
 * 测试bug类.
 *
 * @author devilwwj
 * @since 2016/10/25
 */
public class BugClass {
    // 这段代码会报空指针异常
    public String bug() {
        // 这段代码会报空指针异常
  /*      String str = null;
        Log.e("BugClass", "get String length:" + str.length());*/
        return "现在以及修复好了，开心吗？";
    }

}
