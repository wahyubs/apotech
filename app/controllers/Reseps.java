package controllers;

import java.util.List;

import models.Resep;

@CRUD.For(Resep.class)
public class Reseps extends CRUD {

    public static void showResep(String idResep) {
        Resep resep = Resep.findById (idResep);
        if (resep==null) resep = new Resep();
        render (resep);
    }

    public static void listResep() {
        List<Resep> reseps = Resep.findAll ();
        render (reseps);
    }
    
    public static void searchXmlResep () {
        render();
    }
    
    public static void searchJsonResep () {
        render();
    }    
    
}

