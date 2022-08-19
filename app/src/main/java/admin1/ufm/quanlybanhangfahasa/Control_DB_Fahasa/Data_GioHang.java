package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_GioHang {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "GioHang";
    private static final String cot_MaTK = "MaTK";
    private static final String cot_MaSach = "MaSach";
    private static final String cot_SLMua = "SLMua";
    private SQLiteDatabase database;
    private Activity activity;

    public Data_GioHang(Activity activity) {
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

    private static final String cot_TenSach = "TenSach";
    private static final String cot_URLHinhAnh = "URLHinhAnh";
    private static final String cot_GiaBanGoc = "GiaBanGoc";
    private static final String tableSQL2 = "Sach";

    public ArrayList<_GioHang> getDLGioHang(int maTK) {
        openDB();
        Data_Sach sachData = new Data_Sach(activity);
        ArrayList<_GioHang> gioHangList = new ArrayList<>();
        String caulenh = "SELECT a." + cot_MaSach + ",a." + cot_SLMua + ",b." + cot_TenSach + ",b." + cot_URLHinhAnh + ",b." + cot_GiaBanGoc +
                " FROM " + tableSQL + " a," + tableSQL2 + " b " + " WHERE a." + cot_MaSach + "=b." + cot_MaSach + " AND " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int maSach = cursor.getInt(0);
                int slMua = cursor.getInt(1);
                String tenSach = cursor.getString(2);
                String urlHinhAnh = cursor.getString(3);
                int giaBanGoc = cursor.getInt(4);
                String giaKM = sachData.getGiaKM(maSach);
                _GioHang gioHang = new _GioHang(maTK, maSach, slMua);
                gioHang.setTenSach(tenSach);
                gioHang.setUrlHinhAnh(urlHinhAnh);
                gioHang.setGiaGoc(giaBanGoc);
                gioHang.setGiaKM(Integer.parseInt(giaKM));
                gioHang.setAddCart(true);
                gioHangList.add(gioHang);
                // Trả về : Mã sách, số lượng mua (bổ sung: tên sách, urlHinhAnh, giá gốc, giá KM, IsAddCart)
            }
            closeDB();
            return gioHangList;
        } else
            closeDB();
        return gioHangList;
    }

    public boolean themGioHang(int maTK, ArrayList<_Sach> sachList) {
        openDB();
        int count = 0;
        for (int i = 0; i < sachList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(cot_MaTK, maTK);
            contentValues.put(cot_MaSach, sachList.get(i).getMaSach());
            contentValues.put(cot_SLMua, sachList.get(i).getsLmua());
            long kq = database.insert(tableSQL, null, contentValues);
            if (kq > 0) {
                count++;
            }
        }
        closeDB();
        if (count > 0) {
            return true;
        } else
            return false;
    }

    public boolean xoaGioHang(int maTK){
        openDB();
        int kq = database.delete(tableSQL,cot_MaTK+"=?",new String[]{String.valueOf(maTK)});
        if (kq>0){
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }
}
