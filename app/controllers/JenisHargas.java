package controllers;

import java.util.List;

import models.JenisHarga;

@CRUD.For(JenisHarga.class)
public class JenisHargas extends CRUD {

    public static void showJenisHarga(String idJnsHarga) {
        JenisHarga jenisHarga = JenisHarga.findById (idJnsHarga);
        if (jenisHarga==null) jenisHarga = new JenisHarga();
        render (jenisHarga);
    }

    public static void listJenisHarga() {
        List<JenisHarga> jenisHargas = JenisHarga.findAll ();
        render (jenisHargas);
    }
    
    public static void searchXmlJenisHarga () {
        render();
    }
    
    public static void searchJsonJenisHarga () {
        render();
    }    
    
}

