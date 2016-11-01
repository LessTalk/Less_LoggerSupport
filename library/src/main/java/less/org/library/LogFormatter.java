package less.org.library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/11/1 <br/>
 * DESCRIPTION :
 */
public class LogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuffer buf = new StringBuffer();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        buf.append("<data>");
        buf.append(timeFormat.format(new Date()));
        buf.append("</data> \n");
        buf.append("<content>");
        buf.append(record.getMessage());
        buf.append("</content> \n");
        return buf.toString();
    }
}
