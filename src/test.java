import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {


    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static int hex16To10(String strHex) {
        BigInteger integer = new BigInteger(strHex, 16);
        return integer.intValue();
    }

    public static Long humiYsw(String out) {
        String sa = out.substring(22, 24);
        String sb = out.substring(24, 26);
        String sc = out.substring(26, 28);
        String sd = out.substring(28, 30);
        double humi = (double) (hex16To10(sd) * hex16To10("1000000") + hex16To10(sc) * hex16To10("10000") +
                hex16To10(sb) * hex16To10("100") + hex16To10(sa)) / hex16To10("64");
        return Math.round(humi);
    }

    public static Long tempYsw(String out) {
        String wa = out.substring(6, 8);
        String wb = out.substring(8, 10);
        String wc = out.substring(10, 12);
        String wd = out.substring(12, 14);
        double temp = (double) (hex16To10(wd) * hex16To10("1000000") + hex16To10(wc) * hex16To10("10000") +
                hex16To10(wb) * hex16To10("100") + hex16To10(wa)) / hex16To10("64");
        return Math.round(temp);
    }


    public static void test() throws ParseException {
        String dateTime = "2016-12-31" + " " + "12:30:45";
        System.out.println(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));
        System.out.println("日期[2016-12-31 12:30:45 123]对应毫秒：" + calendar.getTimeInMillis());
    }


    public static void main(String[] args) throws ParseException {
      int a =  hex16To10("041A") ;
        Double b =a/10.0;
        System.out.println("值:"+a);
        System.out.println("值:"+String.valueOf(b));
        System.out.println("值:"+"\u0002");

 


       /* String beginTime = "2018-07-30";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(1647853200000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//设置起时间*/
        //System.out.println("111111111::::"+cal.getTime());
        //cal.add(Calendar.YEAR, 1);//增加一年
        //cal.add(Calendar.DATE, 10);//增加一天
        //cd.add(Calendar.DATE, -10);//减10天
        //cd.add(Calendar.MONTH, n);//增加一个月
        //System.out.println("输出::"+format.format(cal.getTime()));
      /*  String a ="24 48 30 33 2F 03 48 B1 01";
       ;
        String [] arr =a.split(" ");
        for(String aa :arr){
            System.out.println(hex16To10(aa));
        }*/

       /* String beginTime = "2018-07-30";
        String endTime = "2018-07-29";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);

            int compareTo = date1.compareTo(date2);

            System.out.println(compareTo);

        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        /*Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE, 0);
        //String yesterday = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(cal.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        //String b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(calendar.getTime());
        Long timw =(calendar.getTimeInMillis()-cal.getTimeInMillis())/1000/60;
        System.out.println(cal.getTimeInMillis());
        System.out.println(timw);*/
   /*    String a = new Date(1647822600000L).toLocaleString();
        System.out.println(a);*/
      /* String acv="A2 079.8 080.0";
        String [] arr=acv.split(" ");
        String b =String.valueOf(Double.valueOf(arr[1]));
        System.err.println(b);
*/
        //test();
       /* String arr="006301AB0D0000AC0D0000D4260000720E0000740E0000A412CC";
       Long a = humiYsw(arr);
        Long b = tempYsw(arr);
        System.out.println("温度："+b+",湿度:"+a);*/

       /* String str = "&#x8BF7;&#x6C42;&#x53C2;&#x6570;&#x4E0D;&#x4E3A;&#x7A7A;!";
        str = str.replace("&#x", "%u");
        str = str.replace(";", "");
        System.out.println(unescape(str));*/
        /*String str = "11332145ptsample999999999";
        String [] a =str.split("ptsample");
        *//*String str1=str.substring(0, str.indexOf("ptsample"));
        str =str.substring(str1.length());*//*
        for(int b =0;b<a.length;b++){
            System.out.println(a[b]);
        }*/

        //try {
        //Files.write(Paths.get("C:/Users/12105/Desktop/kk.sql"),"12365442".getBytes());
           /* InputStream in = new BufferedInputStream(new FileInputStream(new File("C:/Users/12105/Desktop/kk.sql")), 8);
            byte [] tmp = new byte[600];
            in.read(tmp, 0, 200);
            System.out.println("字节流的前5个字节为: " + new String(tmp));*/
        //BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:/Users/12105/Desktop/bos.txt")) ;

        //写数据
        //bos.write("hello".getBytes());
        //释放资源
        //bos.close();
        //} catch (IOException e) {
        //e.printStackTrace();
        //}
    }

    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("&#x");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

}
