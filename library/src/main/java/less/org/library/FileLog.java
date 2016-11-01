
package less.org.library;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/9/21 <br/>
 * DESCRIPTION :
 */
class FileLog {

    public static final String NET_CRASH = "NetCrash";
    public static final String NET_SERVER_ERROR = "NetServerError";
    public static final String RUN_ERROR = "RunError";

    public  static File DIR_ROOT;
    public  static File DIR_LOG_PATH;
    public  static File NET_CRASH_FILE_PATH ;
    public  static File NET_SERVER_FILE_PATH ;
    public  static File RUN_ERROR_PATH;

    private static FileLog sFileLog;

    protected static FileLog getInstance() {
        if(sFileLog == null){
            sFileLog = new FileLog();
        }
        return sFileLog;
    }

    private HashMap<String, File> mHashMap;

    protected static void init(String fileName){
        DIR_ROOT = new File(Environment.getExternalStorageDirectory(), fileName);
        DIR_LOG_PATH = new File(DIR_ROOT, ".log");
        NET_CRASH_FILE_PATH = new File(DIR_LOG_PATH, "net_crash");
        NET_SERVER_FILE_PATH = new File(DIR_LOG_PATH, "net_server");
        RUN_ERROR_PATH = new File(DIR_LOG_PATH, "run_error");
    }

    private FileLog() {
        mHashMap = new HashMap<String, File>();
        mHashMap.put(NET_CRASH, NET_CRASH_FILE_PATH);
        mHashMap.put(NET_SERVER_ERROR, NET_SERVER_FILE_PATH);
        mHashMap.put(RUN_ERROR, RUN_ERROR_PATH);

        for (Map.Entry<String, File> map : mHashMap.entrySet()) {
            try {
                initPath(map.getKey(), map.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void info(String tag, String message) {
        if (!mHashMap.containsKey(tag)) {
            throw new RuntimeException("no support tag type");
        }
        Logger logger = getLogger(tag);
        logger.info(message);
    }

    private void initPath(String tag, File mFilePath) throws IOException {
        Logger logger = Logger.getLogger(tag);
        if (!DIR_LOG_PATH.exists()) {
            boolean pathB = DIR_LOG_PATH.mkdirs();
            LogUtil.d("pathB:" + pathB);
        }
        AsyncFileHandler asyncFileHandler = new AsyncFileHandler(mFilePath.getAbsolutePath()+ "%g" + ".log",1024 * 1024, 9999, true);
        asyncFileHandler.setFormatter(new LogFormatter());
        logger.addHandler(asyncFileHandler);
    }

}
