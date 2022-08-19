package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _TacGia {
    String maTacGia,tenTacGia,quocTich;

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public _TacGia(String maTacGia, String tenTacGia, String quocTich) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.quocTich = quocTich;
    }
}
