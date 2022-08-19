package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _Sach {
    int maSach, sLmua, giaBanKM = 0;
    String tenSach, moTaSach, uRLHinhAnh, namXB, tenTheLoai;
    int giaBanGoc, sLTon, maTheLoai = -1, maNXB, maTacGia, maCTKM;

    boolean addCart = false;

    public boolean isAddCart() {
        return addCart;
    }

    public void setAddCart(boolean addCart) {
        this.addCart = addCart;
    }

    public _Sach(int maSach, String tenSach, String moTaSach, String uRLHinhAnh,
                 String namXB, int giaBanGoc, int sLTon, int maTheLoai, int maNXB, int maTacGia, int maCTKM) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.moTaSach = moTaSach;
        this.uRLHinhAnh = uRLHinhAnh;
        this.namXB = namXB;
        this.giaBanGoc = giaBanGoc;
        this.sLTon = sLTon;
        this.maTheLoai = maTheLoai;
        this.maNXB = maNXB;
        this.maTacGia = maTacGia;
        this.maCTKM = maCTKM;
    }

    public int getGiaBanKM() {
        return giaBanKM;
    }

    public void setGiaBanKM(int giaBanKM) {
        this.giaBanKM = giaBanKM;
    }

    public _Sach() {

    }

    public int getsLmua() {
        return sLmua;
    }

    public void setsLmua(int sLmua) {
        this.sLmua = sLmua;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMoTaSach() {
        return moTaSach;
    }

    public void setMoTaSach(String moTaSach) {
        this.moTaSach = moTaSach;
    }

    public String getuRLHinhAnh() {
        return uRLHinhAnh;
    }

    public void setuRLHinhAnh(String uRLHinhAnh) {
        this.uRLHinhAnh = uRLHinhAnh;
    }

    public String getNamXB() {
        return namXB;
    }

    public void setNamXB(String namXB) {
        this.namXB = namXB;
    }

    public int getGiaBanGoc() {
        return giaBanGoc;
    }

    public void setGiaBanGoc(int giaBanGoc) {
        this.giaBanGoc = giaBanGoc;
    }

    public int getsLTon() {
        return sLTon;
    }

    public void setsLTon(int sLTon) {
        this.sLTon = sLTon;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public int getMaCTKM() {
        return maCTKM;
    }

    public void setMaCTKM(int maCTKM) {
        this.maCTKM = maCTKM;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
