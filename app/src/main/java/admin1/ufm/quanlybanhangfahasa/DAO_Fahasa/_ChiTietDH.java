package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _ChiTietDH {
    private int maDH,maSach;
    private int soLuong,giaBanGoc,GiaKM;
    private String tenSach,uRLHinhAnh;
//    private int SLTongCong;

//    public int getSLTongCong() {
//        return SLTongCong;
//    }
//
//    public void setSLTongCong(int SLTongCong) {
//        this.SLTongCong = SLTongCong;
//    }

    public String getuRLHinhAnh() {
        return uRLHinhAnh;
    }

    public void setuRLHinhAnh(String uRLHinhAnh) {
        this.uRLHinhAnh = uRLHinhAnh;
    }

    public int getGiaBanGoc() {
        return giaBanGoc;
    }

    public void setGiaBanGoc(int giaBanGoc) {
        this.giaBanGoc = giaBanGoc;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaKM() {
        return GiaKM;
    }

    public void setGiaKM(int giaKM) {
        GiaKM = giaKM;
    }

    public _ChiTietDH(int maDH, int maSach, int soLuong) {
        this.maDH = maDH;
        this.maSach = maSach;
        this.soLuong = soLuong;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
