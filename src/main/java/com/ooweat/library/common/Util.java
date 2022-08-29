package com.ooweat.library.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    //경로 관련 Utils
    public static PathUtil pathUtil() {
        return new PathUtil();
    }

    public static class PathUtil {
        public String className() {
            return Thread.currentThread().getStackTrace()[2].getClassName();
        }

        public String methodName() {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        }

        public String classWithMethodName() {
            return
                    Thread.currentThread().getStackTrace()[2].getClassName() +"."+
                            Thread.currentThread().getStackTrace()[2].getMethodName();
        }
    }

    //로그 관련 Utils
    public static LogUtil logUtil() {
        return new LogUtil();
    }

    public static class LogUtil {
        public String now() {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "[" + sdf.format(date) + "] ";
        }
    }

    //날짜 & 시간 관련 Utils
    public static DateUtil dateUtil() {
        return new DateUtil();
    }

    public static class DateUtil {
        Date date = new Date();

        public String sysdate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }

        public String yyyyMMdd() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format(date);
        }

        public String yyyyMMddWithHypen() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }

        public String yyyyMMddWithSlash() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format(date);
        }

        public String hhmmss() {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
            return sdf.format(date);
        }

        public String hhmmssWithColon() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(date);
        }
    }

    //문자열 Util
    public static StringUtil stringUtil(String param) {
        return new StringUtil(param);
    }

    public static class StringUtil {
        private String value;

        public StringUtil(String value) {
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