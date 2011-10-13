package controllers;

import java.util.List;

import models.StokObatAlat;

@CRUD.For(StokObatAlat.class)
public class StokObatAlats extends CRUD {

    public static void showStokObatAlat(String idStok) {
        StokObatAlat stokObatAlat = StokObatAlat.findById (idStok);
        if (stokObatAlat==null) stokObatAlat = new StokObatAlat();
        render (stokObatAlat);
    }

    public static void listStokObatAlat() {
        List<StokObatAlat> stokObatAlats = StokObatAlat.findAll ();
        render (stokObatAlats);
    }
    
    public static void searchXmlStokObatAlat () {
        render();
    }
    
    public static void searchJsonStokObatAlat () {
        render();
    }    
    
}

