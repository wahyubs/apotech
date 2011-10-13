package controllers;

import java.util.List;

import models.ObatResep;

@CRUD.For(ObatResep.class)
public class ObatReseps extends CRUD {

    public static void showObatResep(String idResepDtl) {
        ObatResep obatResep = ObatResep.findById (idResepDtl);
        if (obatResep==null) obatResep = new ObatResep();
        render (obatResep);
    }

    public static void listObatResep() {
        List<ObatResep> obatReseps = ObatResep.findAll ();
        render (obatReseps);
    }
    
    public static void searchXmlObatResep () {
        render();
    }
    
    public static void searchJsonObatResep () {
        render();
    }    
    
}

