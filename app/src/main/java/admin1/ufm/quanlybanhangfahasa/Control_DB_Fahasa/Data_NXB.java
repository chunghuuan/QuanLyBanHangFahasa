package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._NhaXuatBan;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_NXB {
    public static final String DB_Name ="quanlybanhangfahasa.db";
    public static final String tableSQL="NhaXuatBan";
    public static final String cot_MaNXB = "MaNXB";
    public static final String cot_TenNXB = "TenNXB";
    public static final String cot_NamThanhLap = "NamThanhLap";

    SQLiteDatabase database;
    Activity activity;

    public Data_NXB(Activity activity) {
        this.activity = activity;
    }
    public void openDB(){
        if (database!=null&&database.isOpen()){
            return;
        }
        database = KetNoiCSDL.nhanCSDL(activity,DB_Name);
    }

    public void closeDB(){
        if (database!=null){
            database.close();
        }
    }
    public ArrayList<_NhaXuatBan> layDLNhaXuatBan(){
        openDB();
        ArrayList<_NhaXuatBan> nhaXuatBanList = new ArrayList<>();
        String caulenh = "SELECT * FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        for (int i = 0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String maNXB = cursor.getString(0);
            String tenNXB = cursor.getString(1);
            String namThanhLap = cursor.getString(2);
            _NhaXuatBan nhaXuatBan = new _NhaXuatBan(maNXB,tenNXB,namThanhLap);
            nhaXuatBanList.add(nhaXuatBan);
        }
        closeDB();
        return nhaXuatBanList;
    }
}
