package com.e.simampuscrud.Peminjaman;

public class PinjamData {
    private String id, nama_peminjam, judul_buku, tgl_pinjam, status;
    public PinjamData (String id, String nama_peminjam, String judul_buku, String tgl_pinjam, String status){
        this.id = id;
        this.nama_peminjam = nama_peminjam;
        this.judul_buku = judul_buku;
        this.tgl_pinjam = tgl_pinjam;
        this.status = status;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNama_peminjam(){
        return nama_peminjam;
    }

    public void setNama_peminjam(String nama_peminjam){
        this.nama_peminjam = nama_peminjam;
    }

    public String getJudul_buku(){
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku){
        this.judul_buku = judul_buku;
    }

    public String getTgl_pinjam(){
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam){
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}