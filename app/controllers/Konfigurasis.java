package controllers;

import java.util.List;

import models.Konfigurasi;

@CRUD.For(Konfigurasi.class)
public class Konfigurasis extends CRUD {

    public static void showKonfigurasi(String idKonfig) {
        Konfigurasi konfigurasi = Konfigurasi.findById (idKonfig);
        if (konfigurasi==null) konfigurasi = new Konfigurasi();
        render (konfigurasi);
    }

    public static void listKonfigurasi() {
        List<Konfigurasi> konfigurasis = Konfigurasi.findAll ();
        render (konfigurasis);
    }
    
    public static void searchXmlKonfigurasi () {
        render();
    }
    
    public static void searchJsonKonfigurasi () {
        render();
    }    
    
}

