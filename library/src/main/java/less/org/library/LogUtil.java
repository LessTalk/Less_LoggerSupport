
package less.org.library;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;

import static less.org.library.FileLog.NET_CRASH;
import static less.org.library.FileLog.NET_SERVER_ERROR;
import static less.org.library.FileLog.RUN_ERROR;
import static less.org.library.LogUtil.Type.F;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/8/23 <br/>
 * DESCRIPTION :
 */
public class LogUtil {

    private static boolean isInit = false;

    protected static boolean isDebug = true;

    public static void d(String tag, String msg) {
        base(tag, msg, Type.D);
    }

    public static void d(String msg) {
        base(null, msg, Type.D);
    }

    public static void e(String tag, String msg) {
        base(tag, msg, Type.E);
    }

    public static void e(String msg) {
        base(null, msg, Type.E);
    }

    public static void j(String tag, String msg) {
        base(tag, msg, Type.J);
    }

    public static void j(String msg) {
        base(null, msg, Type.J);
    }

    public static void netCrashF(Throwable throwable) {
        base(NET_CRASH, throwableToString(throwable), F);
    }

    public static void netCrashF(String msg) {
        base(NET_CRASH, msg, F);
    }

    public static void netServerF(Throwable throwable) {
        base(NET_SERVER_ERROR, throwableToString(throwable), F);
    }

    public static void netServerF(String msg) {
        base(NET_SERVER_ERROR, msg, F);
    }

    public static void runErrorF(Throwable throwable) {
        base(RUN_ERROR, throwableToString(throwable), F);
    }

    private static void base(String tag, String msg, Type type) {
        if (!isDebug && type != F) {
            return;
        }
        if (!isInit) {
            Logger.init(LogUtil.class.getSimpleName()).methodCount(3).hideThreadInfo().methodOffset(2);
            isInit = true;
        }

        switch (type) {
            case D:
                if (TextUtils.isEmpty(tag)) {
                    Logger.d(msg);
                } else {
                    Logger.t(tag).d(msg);
                }
                break;
            case E:
                if (TextUtils.isEmpty(tag)) {
                    Logger.e(msg);
                } else {
                    Logger.t(tag).e(msg);
                }
                break;
            case J:
                if (TextUtils.isEmpty(tag)) {
                    Logger.json(msg);
                } else {
                    Logger.t(tag).json(msg);
                }
                break;
            case F:
                FileLog.getInstance().info(tag, msg);
                break;
        }
    }

    protected enum Type {
        D, E, J, F
    }

    private static String throwableToString(Throwable throwable) {
        if (throwable == null) {
            return "invalid throwable";
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        if (throwable instanceof ConnectException) {
            printStream.println("  " + throwable.getMessage());
            if (throwable.getCause() != null)
                printStream.println("  " + throwable.getCause().getMessage());
        } else if (throwable instanceof java.net.SocketException
                || throwable instanceof java.net.SocketTimeoutException
                || throwable instanceof java.net.HttpRetryException
                || throwable instanceof java.net.UnknownHostException) {
            if (throwable.getMessage() != null)
                printStream.print("  " + throwable.getMessage());
            else
                throwable.printStackTrace(printStream);
            printStream.println("");
        } else {
            throwable.printStackTrace(printStream);
            printStream.println("");
        }
        printStream.close();
        return out.toString();
    }

}
