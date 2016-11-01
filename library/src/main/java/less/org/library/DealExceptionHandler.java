package less.org.library;

import android.text.TextUtils;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/11/1 <br/>
 * DESCRIPTION :
 */
public class DealExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public void attachToApplication(String logName,boolean isDebug) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        FileLog.init(TextUtils.isEmpty(logName) ? "less" : logName);
        LogUtil.isDebug = isDebug;
    }

    public void attachToApplication(boolean isDebug) {
        attachToApplication(null,isDebug);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogUtil.runErrorF(e);
        mDefaultHandler.uncaughtException(t, e);
    }
}
