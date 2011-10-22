package tool;

public class CommonUtil {
	public static boolean isEmpty(String tmp) {
		return tmp == null || "".equals(tmp);
	}

	public static boolean isNotEmpty(String tmp) {
		return tmp != null && !"".equals(tmp);
	}
}
