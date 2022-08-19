package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _NhaXuatBan {
    String maNXB,tenNXB,namThanhLap;

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(String namThanhLap) {
        this.namThanhLap = namThanhLap;
    }

    public _NhaXuatBan(String maNXB, String tenNXB, String namThanhLap) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.namThanhLap = namThanhLap;
    }
}
