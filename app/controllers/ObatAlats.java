package controllers;

import java.util.List;

import models.ObatAlat;

@CRUD.For(ObatAlat.class)
public class ObatAlats extends CRUD {

    public static void showObatAlat(String idObatAlat) {
        ObatAlat obatAlat = ObatAlat.findById (idObatAlat);
        if (obatAlat==null) obatAlat = new ObatAlat();
        render (obatAlat);
    }

    public static void listObatAlat() {
        List<ObatAlat> obatAlats = ObatAlat.findAll ();
        render (obatAlats);
    }
    
    public static void searchXmlObatAlat () {
        render();
    }
    
    public static void searchJsonObatAlat () {
        render();
    }    
    
}

