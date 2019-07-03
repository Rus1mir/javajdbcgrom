package jdbc.lesson4.hw.utils;

public class Utils {

    public static String arrayToString (String[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();

        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.toString();
            b.append(",");
        }
    }

    public static String[] stringToArray (String s) {
        if (s == null)
            return null;

        return s.trim().split(",");
    }
 }
