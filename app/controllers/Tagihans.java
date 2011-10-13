package controllers;

import java.util.List;

import models.Tagihan;

@CRUD.For(Tagihan.class)
public class Tagihans extends CRUD {

    public static void showTagihan(String idTagihan) {
        Tagihan tagihan = Tagihan.findById (idTagihan);
        if (tagihan==null) tagihan = new Tagihan();
        render (tagihan);
    }

    public static void listTagihan() {
        List<Tagihan> tagihans = Tagihan.findAll ();
        render (tagihans);
    }
    
    public static void searchXmlTagihan () {
        render();
    }
    
    public static void searchJsonTagihan () {
        render();
    }    
    
}

