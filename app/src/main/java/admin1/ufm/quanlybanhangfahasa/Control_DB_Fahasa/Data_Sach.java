package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_Sach {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "Sach";
    private static final String cot_MaSach = "MaSach";
    private static final String cot_TenSach = "TenSach";
    private static final String cot_MoTaSach = "MoTaSach";
    private static final String cot_URLHinhAnh = "URLHinhAnh";
    private static final String cot_NamXB = "NamXB";
    private static final String cot_GiaBanGoc = "GiaBanGoc";
    private static final String cot_SLTon = "SLTon";
    private static final String cot_MaTheLoai = "MaTheLoai";
    private static final String cot_MaNXB = "MaNXB";
    private static final String cot_MaTacGia = "MaTacGia";
    private static final String cot_MaCTKM = "MaCTKM";
    private SQLiteDatabase database;
    private Activity activity;

    public Data_Sach(Activity activity) {
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

    public boolean thayDoiSLTon(int maSach,int slMua){
        openDB();
        String caulenh = "SELECT "+cot_SLTon+" FROM "+tableSQL+" WHERE "+cot_MaSach+"="+maSach;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        int SlTon = cursor.getInt(0)-slMua;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_SLTon, SlTon);
        int i = database.update(tableSQL, contentValues, cot_MaSach + "=?", new String[]{String.valueOf(maSach)});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<_Sach> getDLSach() {
        openDB();
        ArrayList<_Sach> sachList = new ArrayList<>();
        String caulenh = "SELECT * FROM " + tableSQL;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSach = cursor.getInt(0);
            String tenSach = cursor.getString(1);
            String moTaSach = cursor.getString(2);
            String uRLHinhAnh = cursor.getString(3);
            String namXB = cursor.getString(4);
            int giaBanGoc = cursor.getInt(5);
            int sLTon = cursor.getInt(6);
            int maTheLoai = cursor.getInt(7);
            int maNXB = cursor.getInt(8);
            int maTacGia = cursor.getInt(9);
            int maCTKM = cursor.getInt(10);
            _Sach sach = new _Sach(maSach, tenSach, moTaSach, uRLHinhAnh, namXB, giaBanGoc, sLTon, maTheLoai, maNXB, maTacGia, maCTKM);
            Data_TheLoai theLoaiData = new Data_TheLoai(activity);
            int giaKM = Integer.parseInt(getGiaKM(maSach));
            sach.setGiaBanKM(giaKM);
            if (maTheLoai > 0) {
                sach.setTenTheLoai(theLoaiData.layTenTL(maTheLoai));
            }
            sachList.add(sach);
        }
        closeDB();
        return sachList;
    }

    public ArrayList<_Sach> getDLSachTheoTL(int maTL) {
        openDB();
        ArrayList<_Sach> sachList = new ArrayList<>();
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaTheLoai + "=" + maTL;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSach = cursor.getInt(0);
            String tenSach = cursor.getString(1);
            String moTaSach = cursor.getString(2);
            String uRLHinhAnh = cursor.getString(3);
            String namXB = cursor.getString(4);
            int giaBanGoc = cursor.getInt(5);
            int sLTon = cursor.getInt(6);
            int maTheLoai = cursor.getInt(7);
            int maNXB = cursor.getInt(8);
            int maTacGia = cursor.getInt(9);
            int maCTKM = cursor.getInt(10);
            _Sach sach = new _Sach(maSach, tenSach, moTaSach, uRLHinhAnh, namXB, giaBanGoc, sLTon, maTheLoai, maNXB, maTacGia, maCTKM);
            int giaKM = Integer.parseInt(getGiaKM(maSach));
            sach.setGiaBanKM(giaKM);
            Data_TheLoai theLoaiData = new Data_TheLoai(activity);
            if (maTheLoai > 0) {
                sach.setTenTheLoai(theLoaiData.layTenTL(maTheLoai));
            }
            sachList.add(sach);
        }
        closeDB();
        return sachList;
    }


    public boolean themSach(_Sach sach) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_TenSach, sach.getTenSach());
        contentValues.put(cot_MoTaSach, sach.getMoTaSach());
        contentValues.put(cot_URLHinhAnh, sach.getuRLHinhAnh());
        contentValues.put(cot_NamXB, sach.getNamXB());
        contentValues.put(cot_GiaBanGoc, sach.getGiaBanGoc());
        contentValues.put(cot_SLTon, sach.getsLTon());
        contentValues.put(cot_MaTheLoai, sach.getMaTheLoai());
        contentValues.put(cot_MaNXB, sach.getMaNXB());
        contentValues.put(cot_MaTacGia, sach.getMaTacGia());
        contentValues.put(cot_MaCTKM, sach.getMaCTKM());
        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            return true;
        }
        return false;
    }

    public boolean xoaSach(int maSach) {
        openDB();
        int kq = database.delete(tableSQL, cot_MaSach + "=?", new String[]{String.valueOf(maSach)});
        if (kq > 0) {
            return true;
        }
        return false;
    }

    public boolean suaSach(_Sach sach) {
        openDB();
        ContentValues contentValues = new ContentValues();
        String maSach = String.valueOf(sach.getMaSach());
        contentValues.put(cot_TenSach, sach.getTenSach());
        contentValues.put(cot_MoTaSach, sach.getMoTaSach());
        contentValues.put(cot_URLHinhAnh, sach.getuRLHinhAnh());
        contentValues.put(cot_NamXB, sach.getNamXB());
        contentValues.put(cot_GiaBanGoc, sach.getGiaBanGoc());
        contentValues.put(cot_SLTon, sach.getsLTon());
        contentValues.put(cot_MaTheLoai, sach.getMaTheLoai());
        contentValues.put(cot_MaNXB, sach.getMaNXB());
        contentValues.put(cot_MaTacGia, sach.getMaTacGia());
        contentValues.put(cot_MaCTKM, sach.getMaCTKM());
        int i = database.update(tableSQL, contentValues, cot_MaSach + "=?", new String[]{maSach});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<String[]> giaSachKM() {
        openDB();
        ArrayList<String[]> list = new ArrayList<>();
        Data_CTKM ctkmData = new Data_CTKM(activity);
        String caulenh = "SELECT " + cot_MaSach + "," + cot_MaCTKM + "," + cot_GiaBanGoc + " FROM " + tableSQL;
        Cursor cursor = database.rawQuery(caulenh, null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maCTKM = cursor.getInt(1);
            float tyLeGiam = ctkmData.getTyLeGiamCTKM(maCTKM);
            float giaban = cursor.getFloat(2);
            float giakm = giaban - giaban * tyLeGiam;
            String sGiaKM = String.valueOf(giakm);
            String[] sGiaSach = new String[]{cursor.getString(0), sGiaKM};
            list.add(sGiaSach);
        }
        closeDB();
        return list;
    }

    public String getTenSach(int maSach) {
        openDB();
        String caulenh = "SELECT " + cot_TenSach + " FROM " + tableSQL + " WHERE " + cot_MaSach + " = " + maSach;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String tenSach = cursor.getString(0);

        closeDB();
        return tenSach;
    }

    public int getGiaBanGoc(int maSach) {
        openDB();
        String caulenh = "SELECT " + cot_GiaBanGoc + " FROM " + tableSQL + " WHERE " + cot_MaSach + " = " + maSach;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int giaBan = cursor.getInt(0);
        closeDB();
        return giaBan;
    }

    public String getuRLHinhAnh(int maSach) {
        openDB();
        String caulenh = "SELECT " + cot_URLHinhAnh + " FROM " + tableSQL + " WHERE " + cot_MaSach + " = " + maSach;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String urlHinhAnh = cursor.getString(0);
        closeDB();
        return urlHinhAnh;
    }

    public String getGiaKM(int maSach) {
        openDB();
        String caulenh = "SELECT " + cot_MaCTKM + "," + cot_GiaBanGoc + " FROM " + tableSQL+ " WHERE " + cot_MaSach + " = " + maSach;
        Cursor cursor = database.rawQuery(caulenh, null);
        Data_CTKM ctkmData = new Data_CTKM(activity);
        cursor.moveToFirst();
        int maCTKM = cursor.getInt(0);
        float tyLeGiam = ctkmData.getTyLeGiamCTKM(maCTKM);
        float giaban = cursor.getFloat(1);
        float giakm = giaban - giaban * tyLeGiam;
        String sGiaKM = String.valueOf(giakm);
        return sGiaKM.substring(0,sGiaKM.length()-2);
    }
}
