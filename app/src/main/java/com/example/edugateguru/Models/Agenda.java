package com.example.edugateguru.Models;

public class Agenda {

    private String jamMulai,jamSelesai,kelas,materi,pelajaran,siswaTidakMasuk,tanggal;

    public Agenda(String jamMulai, String jamSelesai, String kelas, String materi, String pelajaran, String siswaTidakMasuk, String tanggal) {
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.kelas = kelas;
        this.materi = materi;
        this.pelajaran = pelajaran;
        this.siswaTidakMasuk = siswaTidakMasuk;
        this.tanggal = tanggal;
    }


    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getSiswaTidakMasuk() {
        return siswaTidakMasuk;
    }

    public void setSiswaTidakMasuk(String siswaTidakMasuk) {
        this.siswaTidakMasuk = siswaTidakMasuk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
