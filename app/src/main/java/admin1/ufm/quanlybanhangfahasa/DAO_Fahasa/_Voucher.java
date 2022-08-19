package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _Voucher {
    String maVoucher,noiDung;
    float tyLeGiam;

    public _Voucher(String maVoucher, String noiDung, float tyLeGiam) {
        this.maVoucher = maVoucher;
        this.noiDung = noiDung;
        this.tyLeGiam = tyLeGiam;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public float getTyLeGiam() {
        return tyLeGiam;
    }

    public void setTyLeGiam(float tyLeGiam) {
        this.tyLeGiam = tyLeGiam;
    }
}
