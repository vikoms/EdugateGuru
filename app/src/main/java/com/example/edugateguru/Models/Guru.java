package com.example.edugateguru.Models;

public class Guru {
    String nama,kota,nip,email,telp,pelajaran,uid;

    public Guru(String nama, String kota, String nip, String email, String telp, String pelajaran, String uid) {
        this.nama = nama;
        this.kota = kota;
        this.nip = nip;
        this.email = email;
        this.telp = telp;
        this.pelajaran = pelajaran;
        this.uid = uid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
