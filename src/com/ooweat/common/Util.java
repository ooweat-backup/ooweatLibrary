package com.ooweat.common;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    //경로 관련 Utils
    public static PathUtils pathUtils() {
        return new PathUtils();
    }
    public static class PathUtils {
        public String className() {
            return Thread.currentThread().getStackTrace()[2].getClassName();
        }

        public String methodName() {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        public String classWithMethodName() {
            return
                    Thread.currentThread().getStackTrace()[2].getClassName() + " & " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + " //  ";
        }
    }

    //로그 관련 Utils
    public static LogUtils logUtils() {
        return new LogUtils();
    }
    public static class LogUtils {
        public String now() {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "[" + sdf.format(date) + "] ";
        }
    }

    //날짜 & 시간 관련 Utils
    public static DateUtils dateUtils() {
        return new DateUtils();
    }
    public static class DateUtils {
        Date date = new Date();

        public String sysdate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }

        public String yyyyMMdd() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format(date);
        }

        public String hhmmss() {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            return sdf.format(date);
        }
    }

    //문자열 Util
    public static StringUtils stringUtils(String param) {
        return new StringUtils(param);
    }
    public static class StringUtils {
        private String value;

        public StringUtils(String value) {
            this.value = value;
        }

        //SQL 의 그것과 같음 in
        public boolean in(String... values) {
            for (String v : values) {
                if (v.equals(value)) return true;
            }
            return false;
        }

        //SQL 의 그것과 같음 notIn
        public boolean notIn(String... values) {
            for (String v : values) {
                if (v.equals(value)) return false;
            }
            return true;
        }
    }
}