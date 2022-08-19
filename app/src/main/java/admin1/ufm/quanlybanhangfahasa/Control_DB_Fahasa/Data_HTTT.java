package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._HTTT;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_HTTT {
    private static final String DB_Name ="quanlybanhangfahasa.db";
    private static final String tableSQL="HTTT";
    private static final String tableSQL2="DonHang";
    private static final String cot_MaHTTT = "MaHTTT";
    private static final String cot_TenHTTT = "TenHTTT";

    private SQLiteDatabase database;
    private Activity activity;

    public Data_HTTT(Activity activity) {
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

    public ArrayList<_HTTT> getDLHTTT(){
        openDB();
        ArrayList<_HTTT> htttList = new ArrayList<>();
        String caulenh = "SELECT * FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        for (int i = 0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String MaHTTT = cursor.getString(0);
            String TenHTTT = cursor.getString(1);
            _HTTT httt = new _HTTT(MaHTTT,TenHTTT);
            htttList.add(httt);
        }
        closeDB();
        return htttList;
    }

    public String getTenHTTT(int maHT){
        openDB();
        String caulenh = "SELECT "+cot_TenHTTT+" FROM "+tableSQL+" WHERE "+cot_MaHTTT+"="+maHT;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        String tenHTTT = cursor.getString(0);
        closeDB();
        return tenHTTT;
    }
}
