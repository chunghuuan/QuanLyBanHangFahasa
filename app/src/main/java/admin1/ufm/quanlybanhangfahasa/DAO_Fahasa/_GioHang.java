package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _GioHang {
    private int maTK,maSach,slMua;
    private int giaGoc,giaKM;
    private String tenSach,urlHinhAnh;
    private boolean AddCart;

    public boolean isAddCart() {
        return AddCart;
    }

    public void setAddCart(boolean addCart) {
        AddCart = addCart;
    }

    public String getUrlHinhAnh() {
        return urlHinhAnh;
    }

    public void setUrlHinhAnh(String urlHinhAnh) {
        this.urlHinhAnh = urlHinhAnh;
    }

    public int getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(int giaKM) {
        this.giaKM = giaKM;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(int giaGoc) {
        this.giaGoc = giaGoc;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSlMua() {
        return slMua;
    }

    public void setSlMua(int slMua) {
        this.slMua = slMua;
    }

    public _GioHang(int maTK, int maSach, int slMua) {
        this.maTK = maTK;
        this.maSach = maSach;
        this.slMua = slMua;
    }
}
