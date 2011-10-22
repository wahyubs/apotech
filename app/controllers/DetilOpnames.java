package controllers;

import java.util.List;

import models.DetilOpname;

@CRUD.For(DetilOpname.class)
public class DetilOpnames extends CRUD {

    public static void showDetilOpname(String idStok) {
        DetilOpname detilOpname = DetilOpname.findById (idStok);
        if (detilOpname==null) detilOpname = new DetilOpname();
        render (detilOpname);
    }

    public static void listDetilOpname() {
        List<DetilOpname> detilOpnames = DetilOpname.findAll ();
        render (detilOpnames);
    }
    
    public static void searchXmlDetilOpname () {
        render();
    }
    
    public static void searchJsonDetilOpname () {
        render();
    }    
    
}

