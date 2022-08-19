package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TheLoai;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_TheLoai {
    public static final String DB_Name ="quanlybanhangfahasa.db";
    public static final String tableSQL="TheLoai";
    public static final String cot_MaTheLoai = "MaTheLoai";
    public static final String cot_TenTheLoai = "TenTheLoai";
    SQLiteDatabase database;
    Activity activity;

    public Data_TheLoai(Activity activity) {
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

    public String layTenTL(int maTheLoai){
        openDB();
        String caulenh = "SELECT "+cot_TenTheLoai+" FROM "+tableSQL+" WHERE "+cot_MaTheLoai+"="+maTheLoai;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        String tenTL = cursor.getString(0);
        closeDB();
        return tenTL;
    }

    public ArrayList<_TheLoai> layDLTheLoai(){
        openDB();
        ArrayList<_TheLoai> theLoaiList = new ArrayList<>();
        String caulenh = "SELECT * FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        for (int i = 0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String maTheLoai = cursor.getString(0);
            String theTheLoai = cursor.getString(1);
            _TheLoai theLoai = new _TheLoai(maTheLoai,theTheLoai);
            theLoaiList.add(theLoai);
        }
        closeDB();
        return theLoaiList;
    }
}
