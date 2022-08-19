package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._SoHuu;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_SoHuu {
    public static final String DB_Name = "quanlybanhangfahasa.db";
    public static final String tableSQL = "SoHuu";
    public static final String cot_MaTK = "MaTK";
    public static final String cot_MaVoucher = "MaVoucher";
    public static final String cot_SoLuong = "SoLuong";

    SQLiteDatabase database;
    Activity activity;

    public Data_SoHuu(Activity activity) {
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

    public boolean themVoucherKH(int maTK, String maVoucher, int soLuong) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MaTK, maTK);
        contentValues.put(cot_MaVoucher, maVoucher);
        contentValues.put(cot_SoLuong, soLuong);
        long kq = 0;
        try {
            kq = database.insert(tableSQL, null, contentValues);
        } catch (SQLiteConstraintException e) {
            kq = database.update(tableSQL, contentValues, cot_MaVoucher + "=?", new String[]{maVoucher});
        }
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public ArrayList<_SoHuu> getDLSoHuu(int maTK) {
        openDB();
        Data_Voucher voucherData = new Data_Voucher(activity);
        ArrayList<_SoHuu> soHuuList = new ArrayList<>();
        String caulenh = "SELECT " + cot_MaVoucher + "," + cot_SoLuong + " FROM " + tableSQL + " WHERE " + cot_MaTK + "=" + maTK;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        if ((cursor != null) && (cursor.getCount() > 0)) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                String maVoucher = cursor.getString(0);
                int soLuong = cursor.getInt(1);
                float tyLeGiam = voucherData.getTyLeGiamVoucher(maVoucher);
                _SoHuu soHuu = new _SoHuu(maTK, maVoucher, soLuong);
                soHuu.setTyLeGiam(tyLeGiam);
                soHuuList.add(soHuu);
            }
            closeDB();
            return soHuuList;

        } else {
            closeDB();
            return soHuuList;
        }

    }

    private int slVoucher(int maTK,String maVoucher){
        openDB();
        String caulenh = "SELECT "+cot_SoLuong+" FROM "+tableSQL+" WHERE "+cot_MaTK+" = "+maTK+" AND "+cot_MaVoucher+"='"+maVoucher+"'";
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        int kq = cursor.getInt(0);
        closeDB();
        return kq;
    }

    public boolean suaSoHuu(int maTK,String maVoucher) {
        int slVoucher = slVoucher(maTK,maVoucher);
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MaTK, maTK);
        contentValues.put(cot_MaVoucher, maVoucher);
        contentValues.put(cot_SoLuong, slVoucher - 1); // Giảm đi 1
        int i = database.update(tableSQL, contentValues, cot_MaTK + "=? AND " + cot_MaVoucher + " =?", new String[]{String.valueOf(maTK),maVoucher});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }
}
