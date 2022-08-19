package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_ChiTietDH {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "ChiTietDH";
    private static final String tableSQL2 = "DonHang";
    private static final String cot_MaDH = "MaDH";
    private static final String cot_MaSach = "MaSach";
    private static final String cot_SoLuong = "SoLuong";
    private SQLiteDatabase database;
    private Activity activity;

    public Data_ChiTietDH(Activity activity) {
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

//    private static final String cot_TenSach = "TenSach";
//    private static final String tableSQL3 = "Sach";

//    public ArrayList<_ChiTietDH> thongKeSach() {
//        openDB();
//        String cauLenh = "SELECT SUM(" + cot_SoLuong + ") FROM " + tableSQL;
//        Cursor cursor = database.rawQuery(cauLenh, null);
//        cursor.moveToFirst();
//        ArrayList<_ChiTietDH> chiTietDHList = new ArrayList<>();
//        int tongSL = cursor.getInt(0);
//        cursor.close();
//
//        String caulenh2 = "SELECT " + cot_TenSach + ",SUM(" + cot_SoLuong + ") as tongCong FROM " + tableSQL + " a," + tableSQL3 + " b WHERE a."
//                + cot_MaSach + "=b." + cot_MaSach + " GROUP BY a." + cot_MaSach + " ORDER BY tongCong DESC LIMIT 3";
//        Cursor cursor1 = database.rawQuery(caulenh2, null);
//
//        for (int i = 0; i < cursor1.getCount(); i++) {
//            cursor1.moveToPosition(i);
//            _ChiTietDH chiTietDH = new _ChiTietDH(0,0,0);
//            chiTietDH.setSLTongCong(cursor1.getInt(1));
//            chiTietDH.setTenSach(cursor1.getString(0));
//            chiTietDHList.add(chiTietDH);
//        }
//        _ChiTietDH chiTietDH1 = new _ChiTietDH(0,0,0);
//        chiTietDH1.setSLTongCong(tongSL);
//        chiTietDHList.add(chiTietDH1);
//        closeDB();
//        return chiTietDHList;
//    }

    public boolean themMH(ArrayList<_GioHang> gioHangList) {
        openDB();
        String caulenh = "SELECT " + cot_MaDH + " FROM " + tableSQL2 + " ORDER BY " + cot_MaDH + " DESC LIMIT 1";
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        int maDH = cursor.getInt(0);
        closeDB();
        openDB();
        int count = 0;
        Data_Sach sachData = new Data_Sach(activity);
        for (int i = 0; i < gioHangList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(cot_MaDH, maDH);
            int maSach = gioHangList.get(i).getMaSach();
            int slMua = gioHangList.get(i).getSlMua();
            contentValues.put(cot_MaSach, maSach);
            contentValues.put(cot_SoLuong, slMua);
            long kq = database.insert(tableSQL, null, contentValues);
            if (kq > 0) {
                sachData.thayDoiSLTon(maSach, slMua);
                count++;
            }
        }
        closeDB();
        if (gioHangList.size() == count) {
            return true;
        } else
            return false;
    }

    public float getTongTien(int maDH, float tyLeGiamVoucher) {
        openDB();
        Data_Sach sachData = new Data_Sach(activity);
        ArrayList<String[]> giaSachList = new ArrayList<>(sachData.giaSachKM());
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaDH + "=" + maDH;
        Cursor cursor = database.rawQuery(caulenh, null);
        float tongTien = 0;
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            for (int j = 0; j < giaSachList.size(); j++) {
                if (cursor.getString(1).equals(giaSachList.get(j)[0])) {
                    float tongTienChuaVoucher = Float.parseFloat(giaSachList.get(j)[1]) * cursor.getFloat(2);
                    tongTien = tongTien + (tongTienChuaVoucher - tongTienChuaVoucher * tyLeGiamVoucher);
                }
            }
        }
        closeDB();
        return tongTien;
    }


    public ArrayList<_ChiTietDH> layDLChiTietDH(int maDH) {
        openDB();
        Data_Sach sachData = new Data_Sach(activity);
        ArrayList<String[]> giaSachList = new ArrayList<>(sachData.giaSachKM());
        ArrayList<_ChiTietDH> chiTietDHList = new ArrayList<>();
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaDH + "=" + maDH;
        System.out.println(caulenh);
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSach = cursor.getInt(1);
            int soLuongMua = cursor.getInt(2);
            String tenSach = sachData.getTenSach(maSach);
            String uRlHinhAnh = sachData.getuRLHinhAnh(maSach);
            int giaBanGoc = sachData.getGiaBanGoc(maSach);
            int giaKM = -1;
            String sGiaKM = "";
            for (int j = 0; j < giaSachList.size(); j++) {
                if (giaSachList.get(j)[0].equals(String.valueOf(maSach))) {
                    sGiaKM = giaSachList.get(j)[1];
                    sGiaKM = sGiaKM.substring(0, sGiaKM.length() - 2);
                    giaKM = Integer.parseInt(sGiaKM);
                    break;
                }
            }
            _ChiTietDH chiTietDH = new _ChiTietDH(maDH, maSach, soLuongMua);
            chiTietDH.setTenSach(tenSach);
            chiTietDH.setGiaBanGoc(giaBanGoc);
            chiTietDH.setGiaKM(giaKM);
            chiTietDH.setuRLHinhAnh(uRlHinhAnh);
            chiTietDHList.add(chiTietDH);
        }

        closeDB();
        return chiTietDHList;
    }


}
