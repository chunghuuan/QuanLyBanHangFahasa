package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_Voucher {
    public static final String DB_Name = "quanlybanhangfahasa.db";
    public static final String tableSQL = "Voucher";
    public static final String cot_MaVoucher = "MaVoucher";
    public static final String cot_NoiDung = "NoiDung";
    public static final String cot_TyLeGiam = "TyLeGiam";

    SQLiteDatabase database;
    Activity activity;

    public Data_Voucher(Activity activity) {
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

    public float getTyLeGiamVoucher(String maVoucher) {
        openDB();
        String caulenh = "SELECT " + cot_TyLeGiam + " FROM " + tableSQL + " WHERE " + cot_MaVoucher + "='" + maVoucher + "'";
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        float tyLeGiam = cursor.getFloat(0);
        closeDB();
        return tyLeGiam;
    }

    public ArrayList<_Voucher> getDLVoucher() {
        openDB();
        ArrayList<_Voucher> voucherList = new ArrayList<>();
        String caulenh = "SELECT * FROM " + tableSQL;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String maVoucher = cursor.getString(0);
            String noiDung = cursor.getString(1);
            float tylegiam = cursor.getFloat(2);
            _Voucher voucher = new _Voucher(maVoucher, noiDung, tylegiam);
            voucherList.add(voucher);
        }
        closeDB();
        return voucherList;
    }


    public boolean themVoucher(_Voucher voucher) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_MaVoucher, voucher.getMaVoucher());
        contentValues.put(cot_NoiDung, voucher.getNoiDung());
        contentValues.put(cot_TyLeGiam, voucher.getTyLeGiam());
        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public boolean xoaVoucher(String maVoucher) {
        openDB();
        int kq = database.delete(tableSQL, cot_MaVoucher + "=?", new String[]{maVoucher});
        if (kq > 0) {
            closeDB();
            return true;
        }
        closeDB();
        return false;
    }

    public boolean suaVoucher(_Voucher voucher) {
        openDB();
        ContentValues contentValues = new ContentValues();
        String maVoucher = voucher.getMaVoucher();
        String noidung = voucher.getNoiDung();
        Float tyLeGiam = voucher.getTyLeGiam();
        contentValues.put(cot_NoiDung, noidung);
        contentValues.put(cot_TyLeGiam, tyLeGiam);
        int i = database.update(tableSQL, contentValues, cot_MaVoucher + "=?", new String[]{maVoucher});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

}
