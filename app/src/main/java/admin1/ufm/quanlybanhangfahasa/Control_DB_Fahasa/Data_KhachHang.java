package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._KhachHang;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_KhachHang {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "KhachHang";
    private static final String cot_MaKH = "MaKH";
    private static final String cot_TenKH = "TenKH";
    private static final String cot_NgaySinh = "NgaySinh";
    private static final String cot_QueQuan = "QueQuan";
    private static final String cot_MaTK = "MaTK";

    private SQLiteDatabase database;
    private Activity activity;

    public Data_KhachHang(Activity activity) {
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

    public int layMaKH(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_MaKH + " FROM " + tableSQL + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int maKH = cursor.getInt(0);
        closeDB();
        closeDB();
        return maKH;
    }

    public int layMaTK(int maKH) {
        openDB();
        String caulenh = "SELECT " + cot_MaTK + " FROM " + tableSQL + " WHERE " + cot_MaKH + " = " + maKH;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int maTK = cursor.getInt(0);
        closeDB();
        closeDB();
        return maTK;
    }

    public String layTenKH1(int maTK) {
        openDB();
        String caulenh = "SELECT " + cot_TenKH + " FROM " + tableSQL + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String tenKH = cursor.getString(0);
        closeDB();
        closeDB();
        return tenKH;
    }

    public String layEmail(int maTK){
        openDB();
        String caulenh = "SELECT " + cot_Email + " FROM " + tableSQL2 + " WHERE " + cot_MaTK + " = " + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String email = cursor.getString(0);
        closeDB();
        closeDB();
        return email;
    }

    public String layTenKH2(int maKH) {
        openDB();
        String caulenh = "SELECT " + cot_TenKH + " FROM " + tableSQL + " WHERE " + cot_MaKH + " = " + maKH;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String tenKH = cursor.getString(0);
        closeDB();
        closeDB();
        return tenKH;
    }

    public _KhachHang dLKH(int maKH) {
        openDB();
        _KhachHang khachHang = new _KhachHang();
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaKH + "=" + maKH;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        khachHang.setTenKH(cursor.getString(1));
        khachHang.setMaTK(cursor.getString(4));
        closeDB();
        return khachHang;
    }

    private static final String tableSQL2 = "TaiKhoan";
    private static final String cot_SDT = "SDT";
    private static final String cot_Email = "Email";

    public ArrayList<_KhachHang> getDLKH() {
        openDB();
        ArrayList<_KhachHang> khachHangList = new ArrayList<>();
        String caulenh = "SELECT " + cot_TenKH + "," + cot_NgaySinh + "," + cot_QueQuan + "," + cot_SDT + "," + cot_Email+","+cot_MaKH+",b."+cot_MaTK
                + " FROM " + tableSQL + " a," + tableSQL2 + " b WHERE a." + cot_MaTK + "=b." + cot_MaTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String tenKH = cursor.getString(0);
            String ngaySinh = cursor.getString(1);
            String queQuan = cursor.getString(2);
            String sdt = cursor.getString(3);
            String email = cursor.getString(4);
            String maKH = cursor.getString(5);
            String maTK = cursor.getString(6);
            _KhachHang khachHang = new _KhachHang();
            khachHang.setTenKH(tenKH);
            khachHang.setNgaySinh(ngaySinh);
            khachHang.setQueQuan(queQuan);
            khachHang.setSdtKH(sdt);
            khachHang.setEmailKH(email);
            khachHang.setMaKH(maKH);
            khachHang.setMaTK(maTK);
            khachHangList.add(khachHang);
        }
        closeDB();
        return khachHangList;
    }

    public boolean suaKH(_KhachHang khachHang){
        openDB();
        ContentValues contentValues = new ContentValues();
        String maKH = khachHang.getMaKH();
        String tenKH = khachHang.getTenKH();
        String queQuan = khachHang.getQueQuan();
        String ngaySinh = khachHang.getNgaySinh();
        contentValues.put(cot_TenKH,tenKH);
        contentValues.put(cot_QueQuan,queQuan);
        contentValues.put(cot_NgaySinh,ngaySinh);
        int i = database.update(tableSQL,contentValues,cot_MaKH+"=?",new String[]{maKH});
        closeDB();
        if (i>0){
            return true;
        }
        return false;
    }

    public boolean themKH(_KhachHang khachHang) {
        openDB();
        String cauLenh = "SELECT "+cot_MaTK+" FROM "+tableSQL2+" ORDER BY "+cot_MaTK+" DESC LIMIT 1";
        Cursor cursor = database.rawQuery(cauLenh,null);
        cursor.moveToFirst();
        int maTK = cursor.getInt(0);
        cursor.close();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_TenKH, khachHang.getTenKH());
        contentValues.put(cot_NgaySinh, khachHang.getNgaySinh());
        contentValues.put(cot_QueQuan, khachHang.getTinhThanh());
        contentValues.put(cot_MaTK, maTK);
        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public ArrayList<String> layThongTinKH(String maTK) {
        openDB();
        String caulenh = "SELECT " + cot_TenKH + "," + cot_NgaySinh + "," + cot_QueQuan + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        ArrayList<String> dulieu = new ArrayList<>();
        dulieu.add(cursor.getString(0));
        dulieu.add(cursor.getString(1));
        dulieu.add(cursor.getString(2));
        closeDB();
        return dulieu;
    }
}
