package com.xwtiger.devrescollect.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.TextUtils;

public class TimeUtils {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat DATA_FORMAT_TIME = new SimpleDateFormat("MM-dd HH:mm");

	private TimeUtils() {
		throw new AssertionError();
	}

	/**
	 * long time to string
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取当前时间yyyyMMdd
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getCurrDateTime() {
		return getTime(System.currentTimeMillis(), DATE_FORMAT);
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(String timeInMillis) {
		return getTime(Long.parseLong(TextUtils.isEmpty(timeInMillis) ? System.currentTimeMillis() + "" : timeInMillis),
				DEFAULT_DATE_FORMAT);
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getFormatTime(long timeInMillis) {
		return getTime(timeInMillis, DATE_FORMAT_DATE);
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}

	/**
	 * 返回日期格式yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeForString(String time) {
		return getFormatTime(Long.parseLong(TextUtils.isEmpty(time) ? System.currentTimeMillis() + "" : time));
	}


	/**
	 * 返回日期格式yyyy-MM-dd
	 *
	 * @param time
	 * @return
	 */
	public static String getTimeForGoldDetail(String time) {
		return getTime(Long.parseLong(TextUtils.isEmpty(time) ? System.currentTimeMillis() + "" : time)*1000, DATA_FORMAT_TIME);
	}

	/**
	 * 返回信息确认页面日期格式yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String getInfoTimeForString(String time) {
		if (TextUtils.isEmpty(time)) {
			return getTime(System.currentTimeMillis());
		}
		return getTime(Long.parseLong(time.trim()) * 1000);
	}

	/**
	 * 毫秒转分钟
	 * 
	 * @param duration
	 * @return
	 */
	public static String timeParse(long duration) {
		String time = "";

		long minute = duration / 60000;
		long seconds = duration % 60000;

		long second = Math.round((float) seconds / 1000);

		if (minute < 10) {
			time += "0";
		}
		time += minute + ":";

		if (second < 10) {
			time += "0";
		}
		time += second;

		return time;
	}

	/**
	 * 计算时间差，剩余天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String getSubtractTiemInString(String date1, String date2) {
		try {
			Date d1 = DEFAULT_DATE_FORMAT.parse(date1);
			Date d2 = DEFAULT_DATE_FORMAT.parse(date2);
			long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			String time = days + "天" + hours + "小时" + minutes + "分";
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String formatDateForYear(String long_time) {

		// "2016-08-01 16:23"

		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat time3 = new SimpleDateFormat("MM月dd日");
		// SimpleDateFormat time4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

		String result = "";
		Date date = null;
		try {
			date = time.parse(long_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date currentDate = new Date();

		if (date.getYear() < currentDate.getYear()) {
			// 去年
			return result = time1.format(date);
		}
		if (date.getMonth() < currentDate.getMonth()) {
			// 今年
			return result = time3.format(date);

		} else if (date.getMonth() == currentDate.getMonth()) {

			if (date.getDay() == currentDate.getDay()) {
				// 今天
				return result = "今天 " ;//time2.format(date)
			} else if (date.getDay() == currentDate.getDay() - 1) {
				// 昨天
				return result = "昨天 " ;//time2.format(date)
			}
			return result = time3.format(date);
		}
		return result;
	}
	
	
	
	public static String formatDateForNoYear(String long_time) {

		// "2016-08-01 16:23"

		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat time3 = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat time4 = new SimpleDateFormat("ddM月");

		String result = "";
		Date date = null;
		try {
			date = time.parse(long_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date currentDate = new Date();

		if (date.getYear() < currentDate.getYear()) {
			// 去年
			return result = time4.format(date);
		}
		if (date.getMonth() < currentDate.getMonth()) {
			// 今年
			return result = time4.format(date);

		} else if (date.getMonth() == currentDate.getMonth()) {

			if (date.getDay() == currentDate.getDay()) {
				// 今天
				return result = "今天" ;//time2.format(date)
			} else if (date.getDay() == currentDate.getDay() - 1) {
				// 昨天
				return result = "昨天" ;//time2.format(date)
			}
			return result = time4.format(date);
		}
		return result;
	}
	
	public static String formatDate(String long_time) {

        // "2016-08-01 16:23"

        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat time3 = new SimpleDateFormat("MM-dd HH:mm");
        // SimpleDateFormat time4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        String result = "";
        Date date = null;
        try {
            date = DEFAULT_DATE_FORMAT.parse(long_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        
        Calendar instance = Calendar.getInstance();
    	Calendar instance_current = Calendar.getInstance();
    	instance.setTime(date);
    	instance_current.setTime(currentDate);
        
        if (instance.get(Calendar.YEAR) < instance_current.get(Calendar.YEAR)) {
            // 去年
            return result = DEFAULT_DATE_FORMAT.format(date);
        }
        if (instance.get(Calendar.MONTH) < instance_current.get(Calendar.MONTH)) {
            // 今年
            return result = time3.format(date);

        } else if (instance.get(Calendar.MONTH) == instance_current.get(Calendar.MONTH)) {

            if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH)) {
                // 今天
                long diff = currentDate.getTime() - date.getTime();// 这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);
                long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                if (hours < 6) {
                    if (hours < 1 && minutes < 60) {
                        return result = minutes + "分钟前";
                    } else {
                        return result = hours + "小时前";
                    }
                } else {
                    return result = "今天 " + time2.format(date);
                }
            } else if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH) - 1) {
                // 昨天
                return result = "昨天 " + time2.format(date);
            }
            return result = time3.format(date);
        }
        return result;
    }
	
	
	public static String formatDateForShowOrder(String long_time) {

        // "2016-08-01 16:23"

        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat time3 = new SimpleDateFormat("MM-dd HH:mm");
        // SimpleDateFormat time4 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        String result = "";
        Date date = null;
        try {
            date = time.parse(long_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        Calendar instance = Calendar.getInstance();
    	Calendar instance_current = Calendar.getInstance();
    	instance.setTime(date);
    	instance_current.setTime(currentDate);
        
        
        if (instance.get(Calendar.YEAR)< instance_current.get(Calendar.YEAR)) {
            // 去年
            return result = time.format(date);
        }
        if (instance.get(Calendar.MONTH)< instance_current.get(Calendar.MONTH)) {
            // 今年
            return result = time3.format(date);

        } else if (instance.get(Calendar.MONTH) == instance_current.get(Calendar.MONTH)) {

            if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH)) {
                // 今天
                long diff = currentDate.getTime() - date.getTime();// 这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);
                long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                if (hours < 6) {
                    if (hours < 1 && minutes < 60) {
                        return result = minutes + "分钟前";
                    } else {
                        return result = hours + "小时前";
                    }
                } else {
                    return result = "今天 " + time2.format(date);
                }
            } else if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH) - 1) {
                // 昨天
                return result = "昨天 " + time2.format(date);
            }
            return result = time3.format(date);
        }
        return result;
    }
	// /**
	// * 计算时间差，剩余天数
	// *
	// * @param date1
	// * @param date2
	// * @return
	// */
	// public static long getSubtractTiemInString(String date2) {
	// try {
	// Date d1 =
	// DEFAULT_DATE_FORMAT.parse(getTime(GTApplication.getAPP().getServerCurrentTime()));
	// Date d2 = DEFAULT_DATE_FORMAT.parse(date2);
	// long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
	// long days = diff / (1000 * 60 * 60 * 24);
	// long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	// long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60
	// * 60)) / (1000 * 60);
	// // String time = days + "天" ;//+ hours + "小时" + minutes + "分"
	// return days;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }

	// /**
	// * 当前项目是否完成
	// * @param date2
	// * @return
	// */
	// public static boolean isFinish(String date2) {
	// try {
	// Date d1 =
	// DEFAULT_DATE_FORMAT.parse(getTime(GTApplication.getAPP().getServerCurrentTime()));
	// Date d2 = DEFAULT_DATE_FORMAT.parse(date2);
	// long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
	// if (diff > 0) {
	// return false;
	// }
	// return true;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return true;
	// }

}