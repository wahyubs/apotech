package controllers;

import java.util.List;

import models.Supplier;

@CRUD.For(Supplier.class)
public class Suppliers extends CRUD {

    public static void showSupplier(String idSupplier) {
        Supplier supplier = Supplier.findById (idSupplier);
        if (supplier==null) supplier = new Supplier();
        render (supplier);
    }

    public static void listSupplier() {
        List<Supplier> suppliers = Supplier.findAll ();
        render (suppliers);
    }
    
    public static void searchXmlSupplier () {
        render();
    }
    
    public static void searchJsonSupplier () {
        render();
    }    
    
}

