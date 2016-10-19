package com.example.administrator.myapplication;
import java.math.BigDecimal;
/**
 * Created by Administrator on 2016-10-14.
 */
public class operationSelf {
        /**
         * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。
         */

        // 默认除法运算精度
        private static final int DEF_DIV_SCALE = 10;

        // 这个类不能实例化
        private operationSelf() {
        }
        /**
         * 提供精确的加法运算。
         * 被加数 v1
         * 加数 v2
         * return 两个参数的和
         */
        public static double addSelf(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.add(b2).doubleValue();
        }

        /**
         * 提供精确的减法运算。
         *
         * 被减数v1
         * 减数v2
         * return 两个参数的差
         */
        public static double subSelf(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.subtract(b2).doubleValue();
        }

        /**
         * 提供精确的乘法运算。
         *
         * 被乘数v1
         * 乘数v2
         * return 两个参数的积
         */
        public static double mulSelf(double v1, double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.multiply(b2).doubleValue();
        }

        /**
         * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
         *
         * 被除数v1
         * 除数v2
         * return 两个参数的商
         */
        public static double divSelf(double v1, double v2) {
            return divSelf(v1, v2, DEF_DIV_SCALE);
        }

        /**
         * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
         *
         * 被除数v1
         *除数v2
         * scale表示需要精确到小数点以后几位。
         * return 两个参数的商
         */
        public static double divSelf(double v1, double v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        /**
         * 提供精确的小数位四舍五入处理。
         *
         *需要四舍五入的数字v
         * 小数点后保留几位 scale
         * return 四舍五入后的结果
         */
        public static double round(double v, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException(
                        "The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    /*
    * 浮点数转二进制
    * */
    public static String doubleToBits(double num){
        try {
            Double n = num;
            int pos_num = n.intValue();
            Double dec = n - pos_num;
            String temp = "";
            temp = Integer.toBinaryString(pos_num);
            String temps = "";
            for (int i = 0; i < DEF_DIV_SCALE; i++) {
                dec *= 2;
                if (dec >= 1) {
                    temps += "1";
                    dec = dec - 1;
                } else {
                    temps += "0";
                }
            }
            return temp + "." + temps;
        }catch (Exception e) {

            //MainActivity.errorShow("转换出错");
            return null;
        }
    }
    /*
    * 二进制数转十六进制
    * */
    public static String doubleToHex(String bitsnum){
        String result="";
        String resultnav="";
        String pos;
        int n=bitsnum.indexOf(".");
        //没有小数位
        if(n==-1){
            pos=getAllBits(bitsnum);
            for (int i=0;i<pos.length();i+=4){
                result+=getHexChar(pos.substring(i,i+4));
            }
            return result;
        }
        //有小数
        else {
            pos=getAllBits(bitsnum.substring(0, n));
            for (int i=0;i<pos.length();i+=4){
                result+=getHexChar(pos.substring(i,i+4));
            }
            //小数部分
            String nav=bitsnum.substring(n+1, bitsnum.length());
            int b=nav.length()%4;
            if(b==1)
                nav+="000";
            if(b==2)
                nav+="00";
            if(b==3)
                nav+="0";
            for (int i=0;i<nav.length();i+=4){
                resultnav+=getHexChar(nav.substring(i,i+4));
            }
            return result+"."+resultnav;
        }
    }
    /**
     * 根据二进制数每四位为单位获得十六制的表示
     */

    public static String getHexChar(String str){
        if(str.equals("0000"))
            return "0";
        else if(str.equals("0001"))
            return "1";
        else if(str.equals("0010"))
            return "2";
        else if(str.equals("0011"))
            return "3";
        else if(str.equals("0100"))
            return "4";
        else if(str.equals("0101"))
            return "5";
        else if(str.equals("0110"))
            return "6";
        else if(str.equals("0111"))
            return "7";
        else if(str.equals("1000"))
            return "8";
        else if(str.equals("1001"))
            return "9";
        else if(str.equals("1010"))
            return "A";
        else if(str.equals("1011"))
            return "B";
        else if(str.equals("1100"))
            return "C";
        else if(str.equals("1101"))
            return "D";
        else if (str.equals("1110"))
            return "E";
        else
            return "F";
    }
    /**
     * 补全二进制数
     */
    public static String getAllBits(String pos){
            int b=pos.length()%4;
            if(b==1)
                pos="000"+pos;
            if(b==2)
                pos="00"+pos;
            if(b==3)
                pos="0"+pos;
            return pos;
    }
}
