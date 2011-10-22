package controllers;

import java.util.List;

import models.DetailResep;

@CRUD.For(DetailResep.class)
public class DetailReseps extends CRUD {

    public static void showDetailResep(String idResepDtl) {
        DetailResep detailResep = DetailResep.findById (idResepDtl);
        if (detailResep==null) detailResep = new DetailResep();
        render (detailResep);
    }

    public static void listDetailResep() {
        List<DetailResep> detailReseps = DetailResep.findAll ();
        render (detailReseps);
    }
    
    public static void searchXmlDetailResep () {
        render();
    }
    
    public static void searchJsonDetailResep () {
        render();
    }    
    
}

