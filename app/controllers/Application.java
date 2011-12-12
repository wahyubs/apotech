package controllers;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.ApotekConstant;
import models.DetailResep;
import models.Konfigurasi;
import models.ObatResep;
import models.Resep;
import models.UserPegawai;
import play.mvc.With;
import play.templates.Template;
import play.templates.TemplateLoader;
import tool.CommonUtil;
import tool.TextPrintUtil;
import controllers.Secure.Security;

@With(Secure.class)
public class Application extends BaseController {

	public static void index(boolean logout) {
		String hasil = logout ? "Anda Berhasil Logout" : null;
		render(hasil);
	}

	public static void saveConfigColor(String colorId) {
		String connected = Security.connected();
		List<Konfigurasi> fetch = Konfigurasi.find(
				"kode_konfig=? and user_id=?",
				ApotekConstant.KONFIGURASI_WARNA, connected).fetch();
		Konfigurasi konfigurasi;
		if (fetch.size() > 0) {
			konfigurasi = fetch.get(0);
			konfigurasi.setNilaiKonfig(colorId);
			konfigurasi.merge();
		} else {
			konfigurasi = new Konfigurasi();
			konfigurasi.setKodeKonfig(ApotekConstant.KONFIGURASI_WARNA);
			konfigurasi
					.setNamaKonfig("Konfigurasi untuk menyimpan warna dasar aplikasi apotek");
			konfigurasi
					.setUserId((UserPegawai) UserPegawai.findById(connected));
			konfigurasi.setNilaiKonfig(colorId);
		}
		konfigurasi.save();
	}

	public static void getConfigColor(String userId) {
		List<Konfigurasi> fetch = Konfigurasi.find(
				"kode_konfig=? and user_id=?",
				ApotekConstant.KONFIGURASI_WARNA, userId).fetch();
		if (fetch.size() > 0) {
			Konfigurasi konfigurasi = fetch.get(0);
			String data = konfigurasi.getNilaiKonfig();
			renderJSON(data, String.class);
		}
	}

	public static void generateDirectNota(String resepId) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("###########0");
			Map map = new HashMap();
			Resep tmp = Resep.findById(resepId);
			String hargaTotalPenjualan = null;
			Integer hargaTotal = 0;
			List<DetailResep> dtlResepList = DetailResep.find("id_resep=?",
					tmp.getIdResep()).fetch();
			for (Iterator iterator = dtlResepList.iterator(); iterator.hasNext();) {
				DetailResep detailResepTmp = (DetailResep) iterator.next();
				List<ObatResep> obatResepList = ObatResep.find("id_resep_dtl=?",
						detailResepTmp.getIdResepDtl()).fetch();
				for (Iterator iterator2 = obatResepList.iterator(); iterator2
						.hasNext();) {
					ObatResep obatResepTmp = (ObatResep) iterator2.next();
					if (obatResepTmp.getHargaObat() != null
							&& obatResepTmp.getJmlObatResep() != null)
						hargaTotal += obatResepTmp.getHargaObat()
								* obatResepTmp.getJmlObatResep();
				}
			}
			hargaTotalPenjualan = decimalFormat.format(hargaTotal);
			map.put("tmp", tmp);
			map.put("terbilang", CommonUtil.terbilang(hargaTotalPenjualan, true));
			TextPrintUtil a = new TextPrintUtil();
			Template templateXml = TemplateLoader
					.load("/app/views/print/nota.xml");
			String bodyXml = templateXml.render(map);
			a.readXml(bodyXml);
			String data = a.toString();
			renderJSON(data, String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// // Reset
		// a.addAsciiCode((char) 27);
		// a.addAsciiCode((char) 64);
		//
		// // Setengah Halaman
		// a.addAsciiCode((char) 27);
		// a.addAsciiCode((char) 67);
		// a.addAsciiCode((char) 33);
		//
		// // Set Bold Semua
		// a.beginBold();
		// int panjang = 70;
		// int col1 = 4;
		// int col2 = 30;
		// int col3 = 8;
		// int col4 = 8;
		// int col5 = 20;
		// Resep tmp = Resep.findById(resepId);
		// a.append("Apotek Timeberi");
		// a.append(a.alignString("Kepada",panjang-"Apotek Timeberi".length(),
		// 1));
		// a.append("\n");
		// a.append("Kaimana-Papua Barat");
		// a.append(a.alignString("Yth:              ",panjang-"Kaimana-Papua Barat".length(),
		// 1));
		// a.append("\n");
		// a.addLine(panjang/2);
		// a.append("\n");
		//
		// if(tmp!=null) {
		// a.append(a.alignString("Kode Resep: "+tmp.getKodeResep(), panjang,
		// -1));
		// a.append("\n");
		// a.append(a.alignString("Tgl Penjualan: "+CommonUtil.formatTanggal(tmp.getTglPenjualan()),
		// panjang, -1));
		// a.append("\n");
		// a.topHorizontalBorder(new int[] {col1,col2,col3,col4,col5});
		// a.append("\n");
		// a.verticalBorder(new int[] {col1,col2,col3,col4,col5}, new String[]
		// {"No","Nama Obat", "Jml","Harga","Total"});
		// a.append("\n");
		// a.setPer180LineSpacing(22);
		// int urut = 0;
		// int total = 0;
		// for (Iterator iter =
		// tmp.getDetailResepIdResep().iterator();iter.hasNext();) {
		// DetailResep dtlTmp = (DetailResep) iter.next();
		// for(Iterator iterator =
		// dtlTmp.getObatResepIdResepDtl().iterator();iterator.hasNext();) {
		// ObatResep obatTmp = (ObatResep) iterator.next();
		// urut++;
		// a.midHorizontalBorder(new int[] {col1,col2,col3,col4,col5});
		// a.append("\n");
		// a.verticalBorder(new int[] {col1,col2,col3,col4,col5}, new String[]
		// {String.valueOf(urut),obatTmp.getIdStok().getIdObatAlat().getNamaObatAlat(),
		// String.valueOf(obatTmp.getJmlObatResep()),String.valueOf(obatTmp.getHargaObat()),String.valueOf(obatTmp.getJmlObatResep()*obatTmp.getHargaObat())});
		// a.append("\n");
		// total+=obatTmp.getJmlObatResep()*obatTmp.getHargaObat();
		// }
		// }
		// a.bottomHorizontalBorder(new int[] {col1,col2,col3,col4,col5});
		// a.append("\n");
		// a.verticalBorder(new int[] {col1+col2+col3+col4+3,col5}, new String[]
		// {"Total",String.valueOf(total)});
		// a.append("\n");
		// a.bottomHorizontalBorder(new int[] {col1+col2+col3+col4+3,col5});
		// a.append("\n");
		// }
		// a.append("\n");
		// // Menonaktifkan bold
		// a.endBold();
		//
		// // Reset
		// a.addAsciiCode((char) 27);
		// a.addAsciiCode((char) 64);
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}