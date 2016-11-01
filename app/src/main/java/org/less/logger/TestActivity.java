package org.less.logger;

import android.app.Activity;
import android.os.Bundle;

import less.org.library.LogUtil;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/11/1 <br/>
 * DESCRIPTION :
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);

        LogUtil.netCrashF("test............dcskjcdskjcvndfavhdvdkjavndvdfakvndfakjvndfkjvnd" +
                "fakjvndfakljvhdfskjv ndfksvndfavvdnavdfkvndfk");
    }
}
