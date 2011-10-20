package controllers;

import java.util.List;

import models.DetilTransferStok;

@CRUD.For(DetilTransferStok.class)
public class DetilTransferStoks extends CRUD {

    public static void showDetilTransferStok(String idTransfer) {
        DetilTransferStok detilTransferStok = DetilTransferStok.findById (idTransfer);
        if (detilTransferStok==null) detilTransferStok = new DetilTransferStok();
        render (detilTransferStok);
    }

    public static void listDetilTransferStok() {
        List<DetilTransferStok> detilTransferStoks = DetilTransferStok.findAll ();
        render (detilTransferStoks);
    }
    
    public static void searchXmlDetilTransferStok () {
        render();
    }
    
    public static void searchJsonDetilTransferStok () {
        render();
    }    
    
}

