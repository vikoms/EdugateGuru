package com.example.edugateguru.Models;

public class IzinPiket {
    private String idIzinPiket, namaMurid, waktuIzin, alasanIzin, keyGuru, status, keyMurid,keyGuru_status;

    public IzinPiket() {}


    public IzinPiket(String idIzinPiket, String namaMurid, String waktuIzin, String alasanIzin, String keyGuru, String status, String keyMurid,String keyGuru_status) {
        this.idIzinPiket = idIzinPiket;
        this.namaMurid = namaMurid;
        this.waktuIzin = waktuIzin;
        this.alasanIzin = alasanIzin;
        this.keyGuru = keyGuru;
        this.status = status;
        this.keyMurid = keyMurid;
        this.keyGuru_status = keyGuru_status;
    }

    public String getIdIzinPiket() {
        return idIzinPiket;
    }

    public void setIdIzinPiket(String idIzinPiket) {
        this.idIzinPiket = idIzinPiket;
    }

    public String getNamaMurid() {
        return namaMurid;
    }

    public void setNamaMurid(String namaMurid) {
        this.namaMurid = namaMurid;
    }

    public String getWaktuIzin() {
        return waktuIzin;
    }

    public void setWaktuIzin(String waktuIzin) {
        this.waktuIzin = waktuIzin;
    }

    public String getAlasanIzin() {
        return alasanIzin;
    }

    public void setAlasanIzin(String alasanIzin) {
        this.alasanIzin = alasanIzin;
    }

    public String getKeyGuru() {
        return keyGuru;
    }

    public void setKeyGuru(String keyGuru) {
        this.keyGuru = keyGuru;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyMurid() {
        return keyMurid;
    }

    public void setKeyMurid(String keyMurid) {
        this.keyMurid = keyMurid;
    }


    public String getKeyGuru_status() {
        return keyGuru_status;
    }

    public void setKeyGuru_status(String keyGuru_status) {
        this.keyGuru_status = keyGuru_status;
    }
}
