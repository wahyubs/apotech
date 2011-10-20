package controllers;

import java.util.List;

import models.ReturPembelian;

@CRUD.For(ReturPembelian.class)
public class ReturPembelians extends CRUD {

    public static void showReturPembelian(String idReturBeli) {
        ReturPembelian returPembelian = ReturPembelian.findById (idReturBeli);
        if (returPembelian==null) returPembelian = new ReturPembelian();
        render (returPembelian);
    }

    public static void listReturPembelian() {
        List<ReturPembelian> returPembelians = ReturPembelian.findAll ();
        render (returPembelians);
    }
    
    public static void searchXmlReturPembelian () {
        render();
    }
    
    public static void searchJsonReturPembelian () {
        render();
    }    
    
}

