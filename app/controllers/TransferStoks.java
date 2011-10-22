package controllers;

import java.util.List;

import models.TransferStok;

@CRUD.For(TransferStok.class)
public class TransferStoks extends CRUD {

    public static void showTransferStok(String idTransfer) {
        TransferStok transferStok = TransferStok.findById (idTransfer);
        if (transferStok==null) transferStok = new TransferStok();
        render (transferStok);
    }

    public static void listTransferStok() {
        List<TransferStok> transferStoks = TransferStok.findAll ();
        render (transferStoks);
    }
    
    public static void searchXmlTransferStok () {
        render();
    }
    
    public static void searchJsonTransferStok () {
        render();
    }    
    
}

