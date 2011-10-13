package controllers;

import java.util.List;

import models.DetilPembelian;

@CRUD.For(DetilPembelian.class)
public class DetilPembelians extends CRUD {

    public static void showDetilPembelian(String idPembelian) {
        DetilPembelian detilPembelian = DetilPembelian.findById (idPembelian);
        if (detilPembelian==null) detilPembelian = new DetilPembelian();
        render (detilPembelian);
    }

    public static void listDetilPembelian() {
        List<DetilPembelian> detilPembelians = DetilPembelian.findAll ();
        render (detilPembelians);
    }
    
    public static void searchXmlDetilPembelian () {
        render();
    }
    
    public static void searchJsonDetilPembelian () {
        render();
    }    
    
}

