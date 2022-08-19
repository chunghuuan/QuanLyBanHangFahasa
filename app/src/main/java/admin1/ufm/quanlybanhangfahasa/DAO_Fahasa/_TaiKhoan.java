package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _TaiKhoan {
    private String maTK,matkhau,ngayDangKy,sdt,email;

    private int maCV;

    public _TaiKhoan(String maTK, String matkhau, String ngayDangKy, String sdt, String email,int maCV) {
        this.maTK = maTK;
        this.matkhau = matkhau;
        this.ngayDangKy = ngayDangKy;
        this.sdt = sdt;
        this.email = email;
        this.maCV = maCV;
    }
    public _TaiKhoan() {

    }

    public int getMaCV() {
        return maCV;
    }

    public void setMaCV(int maCV) {
        this.maCV = maCV;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
