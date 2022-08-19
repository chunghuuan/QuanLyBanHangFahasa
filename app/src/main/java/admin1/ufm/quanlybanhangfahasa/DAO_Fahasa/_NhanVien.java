package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _NhanVien {
    private String maNV,tenNV,gioiTinhNV,namSinh,queQuanNV,maTK;
    private String sdtNV,emailNV;

    public _NhanVien() {
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getEmailNV() {
        return emailNV;
    }

    public void setEmailNV(String emailNV) {
        this.emailNV = emailNV;
    }

    private  boolean lamViec;

    private String ngayVaoLam,ngayKetThuc;

    public boolean isLamViec() {
        return lamViec;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setLamViec(boolean lamViec) {
        this.lamViec = lamViec;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinhNV() {
        return gioiTinhNV;
    }

    public void setGioiTinhNV(String gioiTinhNV) {
        this.gioiTinhNV = gioiTinhNV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuanNV() {
        return queQuanNV;
    }

    public void setQueQuanNV(String queQuanNV) {
        this.queQuanNV = queQuanNV;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public _NhanVien(String maNV, String tenNV, String gioiTinhNV, String namSinh, String queQuanNV, String maTK) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinhNV = gioiTinhNV;
        this.namSinh = namSinh;
        this.queQuanNV = queQuanNV;
        this.maTK = maTK;
    }
}
