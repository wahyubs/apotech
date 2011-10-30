package controllers;

import java.util.List;

import models.JenisObatAlat;

@CRUD.For(JenisObatAlat.class)
public class JenisObatAlats extends CRUD {

    public static void showJenisObatAlat(String idJnsObatAlat) {
        JenisObatAlat jenisObatAlat = JenisObatAlat.findById (idJnsObatAlat);
        if (jenisObatAlat==null) jenisObatAlat = new JenisObatAlat();
        render (jenisObatAlat);
    }

    public static void listJenisObatAlat() {
        List<JenisObatAlat> jenisObatAlats = JenisObatAlat.findAll ();
        render (jenisObatAlats);
    }
    
    public static void searchXmlJenisObatAlat () {
        render();
    }
    
    public static void searchJsonJenisObatAlat () {
        render();
    }    
    
}

