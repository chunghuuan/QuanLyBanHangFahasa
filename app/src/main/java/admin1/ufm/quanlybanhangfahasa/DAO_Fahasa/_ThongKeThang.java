package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _ThongKeThang {
    String thang;
    int slDH;

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getSlDH() {
        return slDH;
    }

    public void setSlDH(int slDH) {
        this.slDH = slDH;
    }

    public _ThongKeThang(String thang, int slDH) {
        this.thang = thang;
        this.slDH = slDH;
    }
}
