package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_TaiKhoan {
    public static final String DB_Name = "quanlybanhangfahasa.db";
    public static final String tableSQL = "TaiKhoan";
    public static final String cot_MaTK = "MaTK";
    public static final String cot_MatKhau = "MatKhau";
    public static final String cot_NgayDangKy = "NgayDangKy";
    public static final String cot_SDT = "SDT";
    public static final String cot_Email = "Email";
    public static final String cot_MaCV = "MaCV";

    SQLiteDatabase database;
    Activity activity;

    public Data_TaiKhoan(Activity activity) {
        this.activity = activity;
    }

    public void openDB() {
        if (database != null && database.isOpen()) {
            return;
        }
        database = KetNoiCSDL.nhanCSDL(activity, DB_Name);
    }

    public void closeDB() {
        if (database != null) {
            database.close();
        }
    }

    private String maTK;

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getMaTK() {
        return maTK;
    }

    private int maCV;

    public int getMaCV() {
        return maCV;
    }

    public void setMaCV(int maCV) {
        this.maCV = maCV;
    }

    public int kiemTraTK(String SDT_OR_Email, String matKhau) {
        openDB();
        SDT_OR_Email = SDT_OR_Email.trim();
        matKhau = matKhau.trim();
        String caulenh1 = "SELECT " + cot_MatKhau + "," + cot_MaTK + "," + cot_MaCV + " FROM " + tableSQL + " WHERE " + cot_Email + " = '" + SDT_OR_Email + "'";
        String caulenh2 = "SELECT " + cot_MatKhau + "," + cot_MaTK + "," + cot_MaCV + " FROM " + tableSQL + " WHERE " + cot_SDT + " = '" + SDT_OR_Email + "'";
        Cursor cursor = database.rawQuery(caulenh1, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String matkhauSQL = cursor.getString(0);
            if (matKhau.equals(matkhauSQL)) {
                setMaTK(cursor.getString(1));
                int maCV = cursor.getInt(2);
                setMaCV(maCV);
                closeDB();
                return maCV;
            } else {
                closeDB();
                return -1;
            }
        } else {
            cursor = database.rawQuery(caulenh2, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                String matkhauSQL = cursor.getString(0);
                if (matKhau.equals(matkhauSQL)) {
                    setMaTK(cursor.getString(1));
                    int maCV = cursor.getInt(2);
                    setMaCV(maCV);
                    closeDB();
                    return maCV;
                } else {
                    closeDB();
                    return -1;
                }
            } else
                closeDB();
            return -1;
        }
    }

    public _TaiKhoan layThongTin2(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_SDT + "," + cot_Email + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        _TaiKhoan taiKhoan = new _TaiKhoan();
        taiKhoan.setSdt(cursor.getString(0));
        taiKhoan.setEmail(cursor.getString(1));
        closeDB();
        return taiKhoan;
    }

    public ArrayList<String> layThongTin(String maTK) {
        openDB();
        String caulenh2 = "SELECT " + cot_SDT + "," + cot_Email + "," + cot_NgayDangKy + "," + cot_MaCV + " FROM " + tableSQL + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh2, null);
        cursor.moveToFirst();
        int maCV = cursor.getInt(3);

        ArrayList<String> thongtin = new ArrayList<>();
        Data_ChucVu chucVu = new Data_ChucVu(activity);
        thongtin.add(chucVu.layTenCV(maCV));
        if (maCV == 2) {
            Data_NhanVien nhanVienData = new Data_NhanVien(activity);
            thongtin.add(nhanVienData.layThongTinNV(maTK).get(0));
            thongtin.add(nhanVienData.layThongTinNV(maTK).get(1));
            thongtin.add(nhanVienData.layThongTinNV(maTK).get(2));
        } else if (maCV == 3) {
            Data_KhachHang khachHangData = new Data_KhachHang(activity);
            thongtin.add(khachHangData.layThongTinKH(maTK).get(0));
            thongtin.add(khachHangData.layThongTinKH(maTK).get(1));
            thongtin.add(khachHangData.layThongTinKH(maTK).get(2));
        }
        thongtin.add(cursor.getString(0));
        thongtin.add(cursor.getString(1));
        thongtin.add(cursor.getString(2));

        closeDB();
        return thongtin;
    }

    public String layMatKhau(String maTK) {
        openDB();
        setMaTK(maTK);
        String caulenh = "SELECT " + cot_MatKhau + " FROM " + tableSQL + " WHERE " + cot_MaTK + "='" + maTK + "'";
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String matkhau = cursor.getString(0);
        closeDB();
        return matkhau;
    }

    public boolean doiMatKhau(String matKhau) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MatKhau, matKhau);
        System.out.println("Ma TK:" + getMaTK());
        int i = database.update(tableSQL, contentValues, cot_MaTK + "=?", new String[]{getMaTK()});
        if (i > 0) {
            closeDB();
            return true;
        } else
            closeDB();
        return false;
    }

    public _TaiKhoan getDLTK(int maTK) {
        openDB();
        _TaiKhoan taiKhoan = new _TaiKhoan();
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        taiKhoan.setEmail(cursor.getString(4));
        taiKhoan.setSdt(cursor.getString(3));
        closeDB();
        return taiKhoan;
    }

    public boolean themTK(_TaiKhoan taiKhoan) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MatKhau, taiKhoan.getMatkhau());
        contentValues.put(cot_NgayDangKy, taiKhoan.getNgayDangKy());
        contentValues.put(cot_SDT, taiKhoan.getSdt());
        contentValues.put(cot_Email, taiKhoan.getEmail());
        contentValues.put(cot_MaCV, taiKhoan.getMaCV());
        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public int getMaCV2(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_MaCV + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int maCV = cursor.getInt(0);
        closeDB();
        return maCV;
    }

    public static final String cot_MaNV = "MaNV";
    public static final String tableSQL2 = "NhanVien";
    public int getMaTKNV(int maNV) {
        openDB();
        String cauLenh = "SELECT " + cot_MaTK + " FROM " + tableSQL2 + " WHERE " + cot_MaNV + "=" + maNV;
        Cursor cursor = database.rawQuery(cauLenh,null );
        cursor.moveToFirst();
        int maTK = cursor.getInt(0);
        closeDB();
        return maTK;
    }


}
