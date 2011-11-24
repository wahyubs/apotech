package models;

public class ApotekConstant {
	public static final String KONFIGURASI_WARNA = "KONFIGURASI_WARNA";
	public static final Object KODE_BUATAN = "KODE_BUATAN";
	public static final String KONFIGURASI_HAK = "KONFIGURASI_HAK";
	public static final String ADMIN = "A";
	
	public static String getNamaBulan(int bulan) {
		String namaBulan = null;
		switch (bulan) {
		case 1:
			namaBulan="Januari";
			break;		
		case 2:
			namaBulan="Februari";
			break;
		case 3:
			namaBulan="Maret";
			break;
		case 4:
			namaBulan="April";
			break;
		case 5:
			namaBulan="Mei";
			break;
		case 6:
			namaBulan="Juni";
			break;
		case 7:
			namaBulan="Juli";
			break;
		case 8:
			namaBulan="Agustus";
			break;
		case 9:
			namaBulan="September";
			break;
		case 10:
			namaBulan="Oktober";
			break;
		case 11:
			namaBulan="November";
			break;
		case 12:
			namaBulan="Desember";
			break;
		default:
			break;
		}
		return namaBulan;
	}
}
