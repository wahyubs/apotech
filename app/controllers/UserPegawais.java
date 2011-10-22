package controllers;

import java.util.List;

import models.UserPegawai;

@CRUD.For(UserPegawai.class)
public class UserPegawais extends CRUD {

    public static void showUserPegawai(String userId) {
        UserPegawai userPegawai = UserPegawai.findById (userId);
        if (userPegawai==null) userPegawai = new UserPegawai();
        render (userPegawai);
    }

    public static void listUserPegawai() {
        List<UserPegawai> userPegawais = UserPegawai.findAll ();
        render (userPegawais);
    }
    
    public static void searchXmlUserPegawai () {
        render();
    }
    
    public static void searchJsonUserPegawai () {
        render();
    }    
    
}

