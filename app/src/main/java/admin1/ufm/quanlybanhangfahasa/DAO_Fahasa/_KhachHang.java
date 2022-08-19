package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _KhachHang {
    String maKH,tenKH,ngaySinh,queQuan,maTK;
    String sdtKH,emailKH;

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTinhThanh() {
        return queQuan;
    }

    public void setTinhThanh(String tinhThanh) {
        this.queQuan = tinhThanh;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public _KhachHang(String maKH, String tenKH, String ngaySinh, String tinhThanh, String maTK) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.queQuan = tinhThanh;
        this.maTK = maTK;
    }
    public _KhachHang() {

    }
}
