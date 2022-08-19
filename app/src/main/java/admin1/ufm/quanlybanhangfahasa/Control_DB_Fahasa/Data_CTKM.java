package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._CTKM;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;

public class Data_CTKM {
    public static final String DB_Name ="quanlybanhangfahasa.db";
    public static final String tableSQL="CTKM";
    public static final String cot_MaCTKM = "MaCTKM";
    public static final String cot_TenCTKM = "TenCTKM";
    public static final String cot_TyLeGiam = "TyLeGiam";
    public static final String cot_TGApDung = "TGApDung";
    public static final String cot_TGKetThuc = "TGKetThuc";

    SQLiteDatabase database;
    Activity activity;

    public Data_CTKM(Activity activity) {
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
    public ArrayList<_CTKM> layDlCTKM(){
        openDB();
        ArrayList<_CTKM> ctkmList = new ArrayList<>();
        String caulenh = "SELECT * FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        cursor.moveToFirst();
        for (int i = 0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String maCTKM = cursor.getString(0);
            String tenCTKM = cursor.getString(1);
            float tylegiam = cursor.getFloat(2);
            String tGApDung = cursor.getString(3);
            String tGKetThuc = cursor.getString(4);
            _CTKM ctkm = new _CTKM(maCTKM,tenCTKM,tylegiam,tGApDung,tGKetThuc);
            ctkmList.add(ctkm);
        }
        closeDB();
        return ctkmList;
    }

    public float getTyLeGiamCTKM(int maCTKM){
        openDB();
        String caulenh = "SELECT "+cot_TyLeGiam+","+cot_TGKetThuc+" FROM "+tableSQL+" WHERE "+cot_MaCTKM+" = "+maCTKM;
        Cursor cursor = database.rawQuery(caulenh,null);
            cursor.moveToFirst();
            float tyLeGiam = cursor.getFloat(0);
            closeDB();
            return tyLeGiam;
    }
    public ArrayList<Float> getTyLeGiamCTKM2(){
        openDB();
        ArrayList<Float> tyLeGiam = new ArrayList<>();
        String caulenh = "SELECT "+cot_TyLeGiam+" FROM "+tableSQL;
        Cursor cursor = database.rawQuery(caulenh,null);
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            tyLeGiam.add(cursor.getFloat(0));
        }
        closeDB();
        return tyLeGiam;
    }
//    private Date layNgayHT() {
//        Calendar cld = Calendar.getInstance();
//        int year = cld.get(Calendar.YEAR);
//        int month = cld.get(Calendar.MONTH) + 1;
//        int day = cld.get(Calendar.DAY_OF_MONTH);
//        String Nday = "" + day;
//        String Nmonth = "" + month;
//        if (day < 10 && day > 0) {
//            Nday = "0" + day;
//        }
//        if (month < 10 && month > 0) {
//            Nmonth = "0" + month;
//        }
//        String ngayHienTai = Nday + "/" + Nmonth + "/" + year;
//        return chuyenDoiKDLNgay(ngayHienTai);
//    }
//
//    private Date chuyenDoiKDLNgay(String Date){
//        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
//        Date date1 = null;
//        try {
//            date1 = formatter1.parse(Date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(activity,"Ngày bị Sai !",Toast.LENGTH_SHORT).show();
//        }
//        return date1;
//    }
}
