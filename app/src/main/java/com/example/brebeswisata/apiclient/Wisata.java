package com.example.brebeswisata.apiclient;

import com.google.gson.annotations.SerializedName;

public class Wisata {
    @SerializedName("id")
    private int id;
    @SerializedName("nama_wisata")
    private String nama_wisata;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("harga_tiket")
    private String harga_tiket;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("latitut")
    private double latitut;
    @SerializedName("longitut")
    private double longitut;



    public Wisata() {
    }

    public Wisata(int id, String harga_tiket, String nama_wisata, String deskripsi, String alamat, String gambar, double latitut, double longitut) {
        this.id = id;
        this.harga_tiket = harga_tiket;
        this.nama_wisata = nama_wisata;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.gambar = gambar;
        this.latitut = latitut;
        this.longitut = longitut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHarga_tiket() {
        return harga_tiket;
    }

    public void setHarga_tiket(String harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public double getLatitut() {
        return latitut;
    }

    public void setLatitut(double latitut) {
        this.latitut = latitut;
    }

    public double getLongitut() {
        return longitut;
    }

    public void setLongitut(double longitut) {
        this.longitut = longitut;
    }
}
