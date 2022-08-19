package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _CTKM {
    String maCTKM,tenCTKM,tgApDung,tgKetThuc;
    float tyLeGiam;

    public _CTKM(String maCTKM, String tenCTKM, float tyLeGiam, String tgApDung, String tgKetThuc) {
        this.maCTKM = maCTKM;
        this.tenCTKM = tenCTKM;
        this.tgApDung = tgApDung;
        this.tgKetThuc = tgKetThuc;
        this.tyLeGiam = tyLeGiam;
    }

    public String getMaCTKM() {
        return maCTKM;
    }

    public void setMaCTKM(String maCTKM) {
        this.maCTKM = maCTKM;
    }

    public String getTenCTKM() {
        return tenCTKM;
    }

    public void setTenCTKM(String tenCTKM) {
        this.tenCTKM = tenCTKM;
    }

    public String getTgApDung() {
        return tgApDung;
    }

    public void setTgApDung(String tgApDung) {
        this.tgApDung = tgApDung;
    }

    public String getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public float getTyLeGiam() {
        return tyLeGiam;
    }

    public void setTyLeGiam(float tyLeGiam) {
        this.tyLeGiam = tyLeGiam;
    }
}
