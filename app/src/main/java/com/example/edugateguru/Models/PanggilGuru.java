package com.example.edugateguru.Models;

public class PanggilGuru {
    private String namaGuru,PelajaranGuru;
    private String uidGuru;
    private String namaMurid;
    private String keyKelasMurid;

    public PanggilGuru() {}

    public PanggilGuru(String namaGuru, String pelajaranGuru, String uidGuru,String namaMurid,String keyKelasMurid) {
        this.namaGuru = namaGuru;
        PelajaranGuru = pelajaranGuru;
        this.uidGuru = uidGuru;
        this.namaMurid = namaMurid;
        this.keyKelasMurid = keyKelasMurid;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public String getPelajaranGuru() {
        return PelajaranGuru;
    }

    public void setPelajaranGuru(String pelajaranGuru) {
        PelajaranGuru = pelajaranGuru;
    }

    public String getUidGuru() {
        return uidGuru;
    }

    public void setUidGuru(String uidGuru) {
        this.uidGuru = uidGuru;
    }

    public String getnamaMurid() {
        return namaMurid;
    }

    public void setnamaMurid(String namaMurid) {
        this.namaMurid = namaMurid;
    }

    public String getKeyKelasMurid() {
        return keyKelasMurid;
    }

    public void setKeyKelasMurid(String keyKelasMurid) {
        this.keyKelasMurid = keyKelasMurid;
    }
}
