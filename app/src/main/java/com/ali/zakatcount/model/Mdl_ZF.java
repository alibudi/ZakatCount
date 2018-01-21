package com.ali.zakatcount.model;

/**
 * Created by ALI on 21/01/2018.
 */

    public class Mdl_ZF {
    private     int id;
    private     int jumlahKel;
    private    int totalZakat;
    private     String namaKK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJumlahKel(int jumlahKel) {
        this.jumlahKel = jumlahKel;
    }

    public void setTotalZakat(int totalZakat) {
        this.totalZakat = totalZakat;
    }

    public void setNamaKK(String namaKK) {
        this.namaKK = namaKK;
    }

    public int getJumlahKel() {
        return jumlahKel;
    }

    public int getTotalZakat() {
        return totalZakat;
    }

    public String getNamaKK() {
        return namaKK;
    }
}
