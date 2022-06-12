package com.example.register_login;

public class Item {

    String item, harga, tipe, deskripsi;
    int imageId;

    public Item(String item, String harga, String tipe, String deskripsi, int imageId) {
        this.item = item;
        this.harga = harga;
        this.tipe = tipe;
        this.deskripsi = deskripsi;
        this.imageId = imageId;
    }
}
