package controllers;

import java.util.List;

import models.DetilReturPembelian;

@CRUD.For(DetilReturPembelian.class)
public class DetilReturPembelians extends CRUD {

    public static void showDetilReturPembelian(String idReturBeli) {
        DetilReturPembelian detilReturPembelian = DetilReturPembelian.findById (idReturBeli);
        if (detilReturPembelian==null) detilReturPembelian = new DetilReturPembelian();
        render (detilReturPembelian);
    }

    public static void listDetilReturPembelian() {
        List<DetilReturPembelian> detilReturPembelians = DetilReturPembelian.findAll ();
        render (detilReturPembelians);
    }
    
    public static void searchXmlDetilReturPembelian () {
        render();
    }
    
    public static void searchJsonDetilReturPembelian () {
        render();
    }    
    
}

