package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TacGia;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_TacGia {
    public static final String DB_Name ="quanlybanhangfahasa.db";
    public static final String tableSQL="TacGia";
    public static final String cot_MaTacGia = "MaTacGia";
    public static final String cot_TenTacGia = "TenTacGia";
    public static final String cot_QuocTich = "QuocTich";

    SQLiteDatabase database;
    Activity activity;

    public Data_TacGia(Activity activity) {
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
    public ArrayList<_TacGia> layDLTacGia(){
        openDB();
        ArrayList<_TacGia> tacGiaList = new ArrayList<>();
        String caulenh = "SELECT * FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        for (int i = 0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String maTacGia = cursor.getString(0);
            String tenTacGia = cursor.getString(1);
            String quocTich = cursor.getString(2);

            _TacGia tacGia = new _TacGia(maTacGia,tenTacGia,quocTich);
            tacGiaList.add(tacGia);
        }
        closeDB();
        return tacGiaList;
    }
}
