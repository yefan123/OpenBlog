package blog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    /**
     * date对象转换成String (datetime)
     * yyyy-MM-dd_HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd__HH:mm:ss");
        return format.format(date);
    }


    /**
     * String(datetime)转换成date对象
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    /**
     * 获取博龄(天数)(自2017-09-19)
     */
    public static int getAgeDays() {
        String date = "2017-09-19";
        long birth = 0;
        long now = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birth = simpleDateFormat.parse(date).getTime();
            now = new Date().getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (int) ((now - birth) / 86400000);
    }
}
