package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._NhanVien;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_NhanVien {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "NhanVien";
    private static final String cot_MaNV = "MaNV";
    private static final String cot_TenNV = "TenNV";
    private static final String cot_GioiTinhNV = "GioiTinhNV";
    private static final String cot_NamSinh = "NamSinh";
    private static final String cot_QueQuanNV = "QueQuanNV";
    private static final String cot_MaTK = "MaTK";
    private static final String cot_NgayVaoLam = "NgayVaoLam";
    private static final String cot_NgayKetThuc = "NgayKetThuc";

    private SQLiteDatabase database;
    private Activity activity;

    public Data_NhanVien(Activity activity) {
        this.activity = activity;
    }

    private void openDB() {
        if (database != null && database.isOpen()) {
            return;
        }
        database = KetNoiCSDL.nhanCSDL(activity, DB_Name);
    }

    private void closeDB() {
        if (database != null) {
            database.close();
        }
    }

    public boolean choNghiViec(String maNV, String ngayKetThuc) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MaNV, maNV);
        contentValues.put(cot_NgayKetThuc, ngayKetThuc);
        int i = database.update(tableSQL, contentValues, cot_MaNV + "=?", new String[]{maNV});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean quayLaiLam(String maNV) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MaNV, maNV);
        contentValues.put(cot_NgayKetThuc, "");
        int i = database.update(tableSQL, contentValues, cot_MaNV + "=?", new String[]{maNV});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean suaNV(_NhanVien nhanVien) {
        openDB();
        ContentValues contentValues = new ContentValues();
        String maNV = nhanVien.getMaNV();
        String tenNV = nhanVien.getTenNV();
        String namSinh = nhanVien.getNamSinh();
        String queQuan = nhanVien.getQueQuanNV();
        String ngayVaoLam = nhanVien.getNgayVaoLam();
        String ngayKetThuc = nhanVien.getNgayKetThuc();
        contentValues.put(cot_TenNV, tenNV);
        contentValues.put(cot_NamSinh, namSinh);
        contentValues.put(cot_QueQuanNV, queQuan);
        contentValues.put(cot_NgayVaoLam, ngayVaoLam);
        if (!cot_NgayKetThuc.equals("")) {
            contentValues.put(cot_NgayKetThuc, ngayKetThuc);
        }
        int i = database.update(tableSQL, contentValues, cot_MaNV + "=?", new String[]{maNV});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<_NhanVien> getDLNV(int dk) {
        openDB();
        String caulenh = "";
        if (dk == 0) {
            caulenh = "SELECT * FROM " + tableSQL;
        }
        if (dk == 1) {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayKetThuc + " IS  NULL OR " + cot_NgayKetThuc + " = ''";
        }
        if (dk == 2) {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayKetThuc + " != ''";
        }

        ArrayList<_NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = database.rawQuery(caulenh, null);
        System.out.println(caulenh);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String maNV = cursor.getString(0);
            String tenNV = cursor.getString(1);
            String namSinh = cursor.getString(3);
            String queQuan = cursor.getString(4);
            String ngayVaoLam = cursor.getString(6);
            String ngayKetThuc = cursor.getString(7);
            _NhanVien nhanVien = new _NhanVien(maNV, tenNV, "", namSinh, queQuan, "");
            nhanVien.setNgayVaoLam(ngayVaoLam);
            Data_TaiKhoan taiKhoanData = new Data_TaiKhoan(activity);
            int maTK = taiKhoanData.getMaTKNV(Integer.parseInt(maNV));
            _TaiKhoan taiKhoan = taiKhoanData.getDLTK(maTK);
            nhanVien.setEmailNV(taiKhoan.getEmail());
            nhanVien.setSdtNV(taiKhoan.getSdt());
            if (ngayKetThuc == null || ngayKetThuc.equals("")) {
                nhanVien.setLamViec(true);
                nhanVien.setNgayKetThuc("");
            } else {
                System.out.println(tenNV + " đã nghỉ việc");
                nhanVien.setLamViec(false);
                nhanVien.setNgayKetThuc(ngayKetThuc);
            }
            nhanVienList.add(nhanVien);
        }
        closeDB();
        return nhanVienList;
    }

    public int layMaNV(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_MaNV + " FROM " + tableSQL + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int maNV = cursor.getInt(0);
        closeDB();
        return maNV;
    }

    public String layTenNV(String maTK) {
        openDB();
        String caulenh = "SELECT " + cot_TenNV + " FROM " + tableSQL + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String tenNV = cursor.getString(0);
        closeDB();
        return tenNV;
    }

    public String layTenNV2(int maNV) {
        openDB();
        System.out.println("maNV:" + maNV);
        String caulenh = "SELECT " + cot_TenNV + " FROM " + tableSQL + " WHERE " + cot_MaNV + " = " + maNV;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String tenNV = cursor.getString(0);
        closeDB();
        closeDB();
        return tenNV;
    }

    public ArrayList<String> layThongTinNV(String maTK) {
        openDB();
        String caulenh = "SELECT " + cot_TenNV + "," + cot_NamSinh + "," + cot_QueQuanNV + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        ArrayList<String> dulieu = new ArrayList<>();
        dulieu.add(cursor.getString(0));
        dulieu.add(cursor.getString(1));
        dulieu.add(cursor.getString(2));
        closeDB();
        return dulieu;
    }

    private static final String tableSQL2 = "TaiKhoan";

    public boolean themNV(_NhanVien nhanVien) {
        openDB();
        String cauLenh = "SELECT " + cot_MaTK + " FROM " + tableSQL2 + " ORDER BY " + cot_MaTK + " DESC LIMIT 1";
        Cursor cursor = database.rawQuery(cauLenh, null);
        cursor.moveToFirst();
        int maTK = cursor.getInt(0);
        cursor.close();
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_TenNV, nhanVien.getTenNV());
        contentValues.put(cot_GioiTinhNV, nhanVien.getGioiTinhNV());
        contentValues.put(cot_NamSinh, nhanVien.getNamSinh());
        contentValues.put(cot_QueQuanNV, nhanVien.getQueQuanNV());
        contentValues.put(cot_MaTK, maTK);
        contentValues.put(cot_NgayVaoLam, nhanVien.getNgayVaoLam());

        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public boolean kiemTraHieuLuc(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_NgayKetThuc + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String kq = cursor.getString(0);
        if (kq == null || kq.equals("")){
            return false;
        }
        return true;
    }
}
