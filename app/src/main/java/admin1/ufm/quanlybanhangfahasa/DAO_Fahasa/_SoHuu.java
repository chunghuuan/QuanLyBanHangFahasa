package admin1.ufm.quanlybanhangfahasa.DAO_Fahasa;

public class _SoHuu {
    private String maVoucher;
    private int soLuong,maTK;
    private float tyLeGiam;

    public String getTyLeGiam() {
        tyLeGiam = tyLeGiam*100;
        String sTyLeGiam = String.valueOf(tyLeGiam);
        String sTyLeGiam2 = sTyLeGiam.substring(0,sTyLeGiam.length()-2)+"%";
        return sTyLeGiam2;
    }
    public float getTyLeGiam2() {
        return tyLeGiam;
    }

    public void setTyLeGiam(float tyLeGiam) {
        this.tyLeGiam = tyLeGiam;
    }

    public _SoHuu(int maTK, String maVoucher, int soLuong) {
        this.maTK = maTK;
        this.maVoucher = maVoucher;
        this.soLuong = soLuong;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
