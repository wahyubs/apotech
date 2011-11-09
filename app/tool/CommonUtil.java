package tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	public static boolean isEmpty(String tmp) {
		return tmp == null || "".equals(tmp);
	}

	public static boolean isNotEmpty(String tmp) {
		return tmp != null && !"".equals(tmp);
	}
	
	public static String getFormatThnBln(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		return dateFormat.format(date);
	}
	
	public static String getFormatThn(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
	}
}
