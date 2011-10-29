package controllers;

import java.util.List;

import models.HargaObat;

@CRUD.For(HargaObat.class)
public class HargaObats extends CRUD {

    public static void showHargaObat(String idObatAlat) {
        HargaObat hargaObat = HargaObat.findById (idObatAlat);
        if (hargaObat==null) hargaObat = new HargaObat();
        render (hargaObat);
    }

    public static void listHargaObat() {
        List<HargaObat> hargaObats = HargaObat.findAll ();
        render (hargaObats);
    }
    
    public static void searchXmlHargaObat () {
        render();
    }
    
    public static void searchJsonHargaObat () {
        render();
    }    
    
}

