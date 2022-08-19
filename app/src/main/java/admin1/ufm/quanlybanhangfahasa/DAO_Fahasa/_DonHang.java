package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _DonHang {
    private String ngayLap, yeuCauKH, ngayXuLy, ghiChu, maVoucher, diaChiGiaoHang;
    private int phiGiaoHang, maHT, maKH, maNV, maDH;
    private float tyLeGiam, tongTien = -1;
    private String tenHTTT, tenKH, tenNV;

    private String sDT = "", email = "";

    public _DonHang(int maDH, String ngayLap, String yeuCauKH, String ngayXuLy, int phiGiaoHang, String ghiChu, int maHT, String maVoucher, int maKH, int maNV, String diaChiGiaoHang) {
        this.maDH = maDH;
        this.ngayLap = ngayLap;
        this.yeuCauKH = yeuCauKH;
        this.ngayXuLy = ngayXuLy;
        this.ghiChu = ghiChu;
        this.phiGiaoHang = phiGiaoHang;
        this.maHT = maHT;
        this.maVoucher = maVoucher;
        this.maKH = maKH;
        this.maNV = maNV;
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public _DonHang() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getYeuCauKH() {
        return yeuCauKH;
    }

    public void setYeuCauKH(String yeuCauKH) {
        this.yeuCauKH = yeuCauKH;
    }

    public String getNgayXuLy() {
        return ngayXuLy;
    }

    public void setNgayXuLy(String ngayXuLy) {
        this.ngayXuLy = ngayXuLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getPhiGiaoHang() {
        return phiGiaoHang;
    }

    public void setPhiGiaoHang(int phiGiaoHang) {
        this.phiGiaoHang = phiGiaoHang;
    }

    public int getMaHT() {
        return maHT;
    }

    public void setMaHT(int maHT) {
        this.maHT = maHT;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public float getTyLeGiam() {
        return tyLeGiam;
    }

    public void setTyLeGiam(float tyLeGiam) {
        this.tyLeGiam = tyLeGiam;
    }

    public String getTenHTTT() {
        return tenHTTT;
    }

    public void setTenHTTT(String tenHTTT) {
        this.tenHTTT = tenHTTT;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
