package tam.pa.galeriislam.Model;

public class DataListNabi {
    private String nama, tahun, usia, descripsi, tempat, gambar;

    public DataListNabi() {
    }

    public DataListNabi(String nama, String tahun, String usia, String descripsi, String tempat, String gambar) {
        this.nama = nama;
        this.tahun = tahun;
        this.usia = usia;
        this.descripsi = descripsi;
        this.tempat = tempat;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getTahun() {
        return tahun;
    }

    public String getUsia() {
        return usia;
    }

    public String getDescripsi() {
        return descripsi;
    }

    public String getTempat() {
        return tempat;
    }

    public String getGambar() {
        return gambar;
    }

}
