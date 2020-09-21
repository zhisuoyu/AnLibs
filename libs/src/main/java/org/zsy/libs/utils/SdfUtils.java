package org.zsy.libs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SdfUtils {

//	public static void main(String[] args) {
//		String src="19991212181212";
//		try {
//			src=parse(src, "yyyyMMddHHmmss", "yyyy-MM-dd H:m:s");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(src);


//	}

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME,Locale.CHINA);
        System.out.println(sdf.format(System.currentTimeMillis()));
    }

    public static final String PATTERN_DATETIME = "yyyy-MM-dd H:m:s";

    public static String parse(String src, SimpleDateFormat srcSdf, SimpleDateFormat dstSdf) throws ParseException {
        src = dstSdf.format(srcSdf.parse(src));
        return src;
    }

    public static String parse(String src, String srcPtn, String dstPtn) throws ParseException {
        SimpleDateFormat srcSdf = new SimpleDateFormat(srcPtn, Locale.CHINA);
        SimpleDateFormat dstSdf = new SimpleDateFormat(dstPtn, Locale.CHINA);
        return parse(src, srcSdf, dstSdf);
    }
}
