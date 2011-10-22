package controllers;

import java.util.List;

import models.StokOpname;

@CRUD.For(StokOpname.class)
public class StokOpnames extends CRUD {

    public static void showStokOpname(String idStokOpname) {
        StokOpname stokOpname = StokOpname.findById (idStokOpname);
        if (stokOpname==null) stokOpname = new StokOpname();
        render (stokOpname);
    }

    public static void listStokOpname() {
        List<StokOpname> stokOpnames = StokOpname.findAll ();
        render (stokOpnames);
    }
    
    public static void searchXmlStokOpname () {
        render();
    }
    
    public static void searchJsonStokOpname () {
        render();
    }    
    
}

