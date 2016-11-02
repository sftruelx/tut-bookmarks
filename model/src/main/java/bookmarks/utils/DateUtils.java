package bookmarks.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoxiang on 2016/10/13.
 */
public class DateUtils {

    public static String getDateString(){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
