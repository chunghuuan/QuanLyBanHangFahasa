package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_ChucVu {
    public static final String DB_Name ="quanlybanhangfahasa.db";
    public static final String tableSQL="ChucVu";
    public static final String cot_MaCV = "MaCV";
    public static final String cot_TenCV = "TenCV";

    SQLiteDatabase database;
    Activity activity;

    public Data_ChucVu(Activity activity) {
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

    public String layTenCV(int maCV){
        String caulenh = "SELECT "+cot_TenCV+" FROM "+tableSQL+" WHERE "+cot_MaCV+" = "+maCV;
        openDB();
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        String tenCV = cursor.getString(0);
        closeDB();
        return tenCV;
    }
}
