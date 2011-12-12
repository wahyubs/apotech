package tool;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

	public static String getFormatThnBlnTgl(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}

	public static String formatTanggal(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}

	private static String getAngka(char angka, boolean spesial) {
		String satuan = "";
		switch (angka) {
		case '1':
			if (spesial)
				satuan = "SE";
			else
				satuan = "SATU ";
			break;
		case '2':
			satuan = "DUA ";
			break;
		case '3':
			satuan = "TIGA ";
			break;
		case '4':
			satuan = "EMPAT ";
			break;
		case '5':
			satuan = "LIMA ";
			break;
		case '6':
			satuan = "ENAM ";
			break;
		case '7':
			satuan = "TUJUH ";
			break;
		case '8':
			satuan = "DELAPAN ";
			break;
		case '9':
			satuan = "SEMBILAN ";
			break;
		default:
			break;
		}
		return satuan;
	}

	public static String terbilang(Number nominal, boolean rupiah) {
		DecimalFormat decimalFormat = new DecimalFormat("###########0");
		return terbilang(decimalFormat.format(nominal), rupiah);
	}

	public static String terbilang(String nominal, boolean rupiah) {
		if (nominal == null)
			return "";
		String terbilang = "";
		nominal = nominal.replace("Rp.", "").trim();
		nominal = nominal.replace("Rp", "").trim();

		nominal = (nominal.lastIndexOf(".00") == nominal.length() - 3)
				&& nominal.lastIndexOf(".00") > 0 ? nominal.substring(0,
				nominal.length() - 3) : nominal;
		nominal = (nominal.lastIndexOf(",00") == nominal.length() - 3)
				&& nominal.lastIndexOf(",00") > 0 ? nominal.substring(0,
				nominal.length() - 3) : nominal;
		nominal = nominal.replace(".", "");
		nominal = nominal.replace(",", "");
		nominal = nominal.trim();
		Map map = new LinkedHashMap();
		Integer indeks_pembagi = 0;
		for (int i = nominal.length(); i > 0; i -= 3) {
			String string;
			if (i >= 3) {
				string = nominal.substring(i - 3, i);
			} else {
				string = (i == 1 ? "00" : "0") + nominal.substring(0, i);
			}
			map.put(indeks_pembagi++, string);
		}
		Set set = map.keySet();
		String pembagi = "";
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Integer ind = (Integer) iter.next();
			switch (ind) {
			case 3:
				pembagi = "MILYAR ";
				break;
			case 2:
				pembagi = "JUTA ";
				break;
			case 1:
				pembagi = "RIBU ";
				break;
			default:
				pembagi = "";
				break;
			}
			String angka_cluster = (String) map.get(ind);
			char angkaSatuan = angka_cluster.charAt(2);
			char angkaPuluh = angka_cluster.charAt(1);
			char angkaRatus = angka_cluster.charAt(0);
			String strPuluh = "";
			if (angkaPuluh == '1' && angkaSatuan != '0') {
				strPuluh = getAngka(angkaSatuan, true) + "BELAS ";
			} else if (angkaPuluh == '0') {
				strPuluh = getAngka(angkaSatuan, ind == 1 && angkaRatus == '0');
			} else {
				strPuluh = getAngka(angkaPuluh, true) + "PULUH "
						+ getAngka(angkaSatuan, false);
			}
			String strRatus = getAngka(angkaRatus, true)
					+ (angkaRatus == '0' ? "" : "RATUS ");
			pembagi = (angkaRatus == '0' && angkaPuluh == '0' && angkaSatuan == '0') ? ""
					: pembagi;
			terbilang = strRatus + strPuluh + pembagi + terbilang;
		}
		if (terbilang == "" || terbilang.equals("")) {
			return "-";
		} else {
			return terbilang + (rupiah ? "RUPIAH" : "");
		}
	}
}
