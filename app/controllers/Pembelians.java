package controllers;

import java.util.List;

import models.Pembelian;

@CRUD.For(Pembelian.class)
public class Pembelians extends CRUD {

    public static void showPembelian(String idPembelian) {
        Pembelian pembelian = Pembelian.findById (idPembelian);
        if (pembelian==null) pembelian = new Pembelian();
        render (pembelian);
    }

    public static void listPembelian() {
        List<Pembelian> pembelians = Pembelian.findAll ();
        render (pembelians);
    }
    
    public static void searchXmlPembelian () {
        render();
    }
    
    public static void searchJsonPembelian () {
        render();
    }    
    
}

