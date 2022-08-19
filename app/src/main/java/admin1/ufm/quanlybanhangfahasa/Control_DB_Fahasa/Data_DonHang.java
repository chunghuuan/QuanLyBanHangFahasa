package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.KetNoiCSDL;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ThongKeThang;

public class Data_DonHang {
    private static final String DB_Name = "quanlybanhangfahasa.db";
    private static final String tableSQL = "DonHang";
    private static final String cot_MaDH = "MaDH";
    private static final String cot_NgayLap = "NgayLap";
    private static final String cot_YeuCauKH = "YeuCauKH";
    private static final String cot_PhiGiaoHang = "PhiGiaoHang";
    private static final String cot_NgayXuLy = "NgayXuLy";
    private static final String cot_GhiChu = "GhiChu";
    private static final String cot_MaHT = "MaHT";
    private static final String cot_MaVoucher = "MaVoucher";
    private static final String cot_MaKH = "MaKH";
    private static final String cot_MaNV = "MaNV";
    private static final String cot_DiaChiGiaoHang = "DiaChiGiaoHang";
    private static final String cot_ThanhTien = "ThanhTien";

    private SQLiteDatabase database;
    private Activity activity;

    public Data_DonHang(Activity activity) {
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

    public int getMaDHCuoi() {
        openDB();
        String cauLenh = "SELECT " + cot_MaDH + " FROM " + tableSQL + " ORDER BY " + cot_MaDH + " DESC LIMIT 1";
        Cursor cursor = database.rawQuery(cauLenh, null);
        cursor.moveToFirst();
        int maDH = cursor.getInt(0);
        closeDB();
        return maDH;
    }

    public ArrayList<_ThongKeThang> thongKeThang(int YEAR) {
        openDB();
        ArrayList<_ThongKeThang> thongKeThang = new ArrayList<>();
        String month = " SUBSTR(" + cot_NgayXuLy + ",4,2) ";
        String year = " SUBSTR(" + cot_NgayXuLy + ",7,8) "; // ALT CRTL L
        String cauLenh = "SELECT " + month + " as month,COUNT(" + month + ") as SL FROM " + tableSQL + " WHERE " + cot_NgayXuLy + " IS NOT NULL AND " +
                cot_GhiChu + "='Hoàn tất' AND " + year + "='" + YEAR + "' GROUP BY " + month;
        Cursor cursor = database.rawQuery(cauLenh, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int thang = Integer.parseInt(cursor.getString(0));
            int sLDH = cursor.getInt(1);
            thongKeThang.add(new _ThongKeThang("Tháng " + thang, sLDH));
        }
        closeDB();
        return thongKeThang;
    }

    public boolean themDH(_DonHang donHang, int maTK) {
        openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cot_NgayLap, donHang.getNgayLap());
        contentValues.put(cot_YeuCauKH, donHang.getYeuCauKH());
        contentValues.put(cot_MaHT, donHang.getMaHT());
        contentValues.put(cot_MaVoucher, donHang.getMaVoucher());
        contentValues.put(cot_MaKH, donHang.getMaKH());
        contentValues.put(cot_DiaChiGiaoHang, donHang.getDiaChiGiaoHang());
        contentValues.put(cot_ThanhTien, donHang.getTongTien());
        long kq = database.insert(tableSQL, null, contentValues);
        if (kq > 0) {
            closeDB();
            Data_SoHuu soHuuData = new Data_SoHuu(activity);
            if (!donHang.getMaVoucher().equals("0")) {
                if (soHuuData.suaSoHuu(maTK, donHang.getMaVoucher())) {
                    return true;
                }
                return false;
            }
            return true;
        }
        closeDB();
        return false;
    }

    public ArrayList<_DonHang> getDLDonHang(int maDH) {
        openDB();
        String caulenh = "SELECT * FROM " + tableSQL;
        ;
        if (maDH != -1) {
            caulenh = caulenh + " WHERE " + cot_MaDH + "=" + maDH;
        }
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        Cursor cursor = database.rawQuery(caulenh, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            _DonHang donHang = new _DonHang();
            donHang.setPhiGiaoHang(cursor.getInt(3));
            donHang.setGhiChu(cursor.getString(5));
            String ngayXuLy = cursor.getString(4);
            if (ngayXuLy == null) { // CHỈ ĐỂ NHƯ THẾ NÀY NẾU MUỐN KIỂM TRA STRING ĐÓ CÓ NULL HAY KHÔNG !
                donHang.setNgayXuLy("");
            } else {
                donHang.setNgayXuLy(cursor.getString(4));
            }
            donHang.setTongTien(cursor.getFloat(11));
            donHangList.add(donHang);
        }
        closeDB();
        return donHangList;
    }

    public ArrayList<String> getThongKeDHKH(int maKH) {
        openDB();
        ArrayList<String> thongTin = new ArrayList<>();
        int a = 0, b = 0, c = 0;
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaKH + "=" + maKH;
        Cursor cursor = database.rawQuery(caulenh, null);
        if (cursor.getCount() < 1) {
            closeDB();
            return thongTin;
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            if (cursor.getString(5) != null && cursor.getString(5).equals("Hoàn tất")) {
                a++;
                c = c + cursor.getInt(11);
            }
        }
        b = cursor.getCount() - a;
        thongTin.add(a + "");
        thongTin.add(b + "");
        thongTin.add(c + "");
        closeDB();
        return thongTin;
    }

    public ArrayList<_DonHang> layDLDonHangXL(int maKH) {
        openDB();
        Data_HTTT htttData = new Data_HTTT(activity);
        Data_Voucher voucherData = new Data_Voucher(activity);
        Data_NhanVien nhanVienData = new Data_NhanVien(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        Data_ChiTietDH chiTietDHData = new Data_ChiTietDH(activity);
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        String caulenh;
        if (maKH < 1) {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayXuLy + " IS NOT NULL";
        } else {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaKH + "=" + maKH + " AND " + cot_NgayXuLy + " IS NOT NULL";
        }
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String ngayXL = cursor.getString(4);
            try {
                int maDH = cursor.getInt(0);
                String ngayLap = cursor.getString(1);
                String yeuCauKH = cursor.getString(2);
                int phiGiaoHang = cursor.getInt(3);
                String ghiChu = cursor.getString(5);
                int maHT = cursor.getInt(6);
                String maVoucher = cursor.getString(7);
                int maKH1 = cursor.getInt(8);
                int maNV = cursor.getInt(9);
                String diaChiGiaoHang = cursor.getString(10);
                float tyLeGiamVoucher = voucherData.getTyLeGiamVoucher(maVoucher);
                float tongTien = cursor.getFloat(11);
                _DonHang donHang = new _DonHang(maDH, ngayLap, yeuCauKH, ngayXL, phiGiaoHang, ghiChu, maHT, maVoucher, maKH1, maNV, diaChiGiaoHang);
                donHang.setTenHTTT(htttData.getTenHTTT(maHT));
                donHang.setTyLeGiam(tyLeGiamVoucher);
                donHang.setTenKH(khachHangData.layTenKH2(maKH1));
                donHang.setTenNV(nhanVienData.layTenNV2(maNV)); // SAI Ở ĐÂY
                donHang.setTongTien(tongTien);
                donHangList.add(donHang);
            } catch (NullPointerException e) {
                closeDB();
                return donHangList;
            }
        }
        closeDB();
        return donHangList;
    }

    public ArrayList<_DonHang> layDLDonHangXLNV() {
        openDB();
        Data_HTTT htttData = new Data_HTTT(activity);
        Data_Voucher voucherData = new Data_Voucher(activity);
        Data_NhanVien nhanVienData = new Data_NhanVien(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        Data_ChiTietDH chiTietDHData = new Data_ChiTietDH(activity);
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        String caulenh;
        caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayXuLy + " IS NOT NULL";
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String ngayXL = cursor.getString(4);
            try {
                int maDH = cursor.getInt(0);
                String ngayLap = cursor.getString(1);
                String yeuCauKH = cursor.getString(2);
                int phiGiaoHang = cursor.getInt(3);
                String ghiChu = cursor.getString(5);
                int maHT = cursor.getInt(6);
                String maVoucher = cursor.getString(7);
                int maKH1 = cursor.getInt(8);
                int maNV = cursor.getInt(9);
                String diaChiGiaoHang = cursor.getString(10);
                float tyLeGiamVoucher = voucherData.getTyLeGiamVoucher(maVoucher);
                float tongTien = cursor.getFloat(11);
                _DonHang donHang = new _DonHang(maDH, ngayLap, yeuCauKH, ngayXL, phiGiaoHang, ghiChu, maHT, maVoucher, maKH1, maNV, diaChiGiaoHang);
                donHang.setTenHTTT(htttData.getTenHTTT(maHT));
                donHang.setTyLeGiam(tyLeGiamVoucher);
                donHang.setTenKH(khachHangData.layTenKH2(maKH1));
                donHang.setTenNV(nhanVienData.layTenNV2(maNV)); // SAI Ở ĐÂY
                donHang.setTongTien(tongTien);
                donHangList.add(donHang);
            } catch (NullPointerException e) {
                closeDB();
                return donHangList;
            }
        }
        closeDB();
        return donHangList;
    }

    public ArrayList<_DonHang> layDLDonHangChuaXLKH(int maKH) {
        openDB();
        Data_HTTT htttData = new Data_HTTT(activity);
        Data_Voucher voucherData = new Data_Voucher(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        Data_ChiTietDH chiTietDHData = new Data_ChiTietDH(activity);
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        String caulenh;
        if (maKH < 1) {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayXuLy + " IS NOT NULL";
        } else {
            caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaKH + "=" + maKH + " AND " + cot_NgayXuLy + " IS NULL";
        }
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String ngayXL = cursor.getString(4);
            try {
                int maDH = cursor.getInt(0);
                String ngayLap = cursor.getString(1);
                String yeuCauKH = cursor.getString(2);
                int phiGiaoHang = cursor.getInt(3);
                String ghiChu = cursor.getString(5);
                int maHT = cursor.getInt(6);
                String maVoucher = cursor.getString(7);
                int maKH1 = cursor.getInt(8);
                int maNV = cursor.getInt(9);
                String diaChiGiaoHang = cursor.getString(10);
                float tyLeGiamVoucher = voucherData.getTyLeGiamVoucher(maVoucher);
                float tongTien = cursor.getFloat(11);
                _DonHang donHang = new _DonHang(maDH, ngayLap, yeuCauKH, ngayXL, phiGiaoHang, ghiChu, maHT, maVoucher, maKH1, maNV, diaChiGiaoHang);
                donHang.setTenHTTT(htttData.getTenHTTT(maHT));
                donHang.setTyLeGiam(tyLeGiamVoucher);
                donHang.setTenKH(khachHangData.layTenKH2(maKH1));
                donHang.setTongTien(tongTien);
                donHangList.add(donHang);
            } catch (NullPointerException e) {
                closeDB();
                return donHangList;
            }
        }
        closeDB();
        return donHangList;
    }

    public ArrayList<_DonHang> layDLDonHangChuaXL() {
        openDB();
        Data_HTTT htttData = new Data_HTTT(activity);
        Data_Voucher voucherData = new Data_Voucher(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        Data_ChiTietDH chiTietDHData = new Data_ChiTietDH(activity);
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_NgayXuLy + " IS NULL";
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String ngayXL = cursor.getString(4);
            try {
                int maDH = cursor.getInt(0);
                String ngayLap = cursor.getString(1);
                String yeuCauKH = cursor.getString(2);
                int phiGiaoHang = cursor.getInt(3);
                String ghiChu = cursor.getString(5);
                int maHT = cursor.getInt(6);
                String maVoucher = cursor.getString(7);
                int maKH = cursor.getInt(8);
                int maNV = cursor.getInt(9);
                String diaChiGiaoHang = cursor.getString(10);
                float tyLeGiamVoucher = voucherData.getTyLeGiamVoucher(maVoucher);
                float tongTien = cursor.getFloat(11);
                _DonHang donHang = new _DonHang(maDH, ngayLap, yeuCauKH, ngayXL, phiGiaoHang, ghiChu, maHT, maVoucher, maKH, maNV, diaChiGiaoHang);
                donHang.setTenHTTT(htttData.getTenHTTT(maHT));
                donHang.setTyLeGiam(tyLeGiamVoucher);
                donHang.setTenKH(khachHangData.layTenKH2(maKH));
                donHang.setTongTien(tongTien);
                donHangList.add(donHang);
            } catch (NullPointerException e) {
                closeDB();
                return donHangList;
            }
        }
        closeDB();
        return donHangList;
    }

    public _DonHang dLDH(int maDH) {
        Data_HTTT htttData = new Data_HTTT(activity);
        Data_Voucher voucherData = new Data_Voucher(activity);
        Data_NhanVien nhanVienData = new Data_NhanVien(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        openDB();
        _DonHang donHang;
        String caulenh = "SELECT * FROM " + tableSQL + " WHERE " + cot_MaDH + "=" + maDH;
        Cursor cursor = database.rawQuery(caulenh, null);
        cursor.moveToFirst();
        String ngayLap = cursor.getString(1);
        String yeuCauKH = cursor.getString(2);
        int phiGiaoHang = cursor.getInt(3);
        String ngayXL = cursor.getString(4);
        String ghiChu = cursor.getString(5);
        int maHT = cursor.getInt(6);
        String maVoucher = cursor.getString(7);
        int maKH1 = cursor.getInt(8);
        int maNV = cursor.getInt(9);
        String diaChiGiaoHang = cursor.getString(10);
        float tyLeGiamVoucher = voucherData.getTyLeGiamVoucher(maVoucher);
        float tongTien = cursor.getFloat(11);
        donHang = new _DonHang(maDH, ngayLap, yeuCauKH, ngayXL, phiGiaoHang, ghiChu, maHT, maVoucher, maKH1, maNV, diaChiGiaoHang);
        donHang.setTenHTTT(htttData.getTenHTTT(maHT));
        donHang.setTyLeGiam(tyLeGiamVoucher);
        donHang.setTenKH(khachHangData.layTenKH2(maKH1));
        donHang.setMaKH(maKH1);
        if (maNV > 0) {
            donHang.setTenNV(nhanVienData.layTenNV2(maNV)); // SAI Ở ĐÂY
        }
        donHang.setTongTien(tongTien);
        closeDB();
        return donHang;
    }


    public boolean xacNhanDH(_DonHang donHang, int maNV) {
        openDB();
        ContentValues contentValues = new ContentValues();
        int maDH = donHang.getMaDH();
        String ngayXuLy = donHang.getNgayXuLy();
        int phiGiaoHang = donHang.getPhiGiaoHang();
        String ghiChu = donHang.getGhiChu();
        contentValues.put(cot_NgayXuLy, ngayXuLy);
        contentValues.put(cot_PhiGiaoHang, phiGiaoHang);
        contentValues.put(cot_GhiChu, ghiChu);
        contentValues.put(cot_MaNV, maNV);
        int i = database.update(tableSQL, contentValues, cot_MaDH + "=?", new String[]{String.valueOf(maDH)});
        closeDB();
        if (i > 0) {
            return true;
        }
        return false;
    }

    public int tongTienDHNVXL(int maNV) {
        openDB();
        String cauLenh = "SELECT SUM(" + cot_ThanhTien + ") FROM " + tableSQL + " WHERE " + cot_MaNV + "=" + maNV;
        Cursor cursor = database.rawQuery(cauLenh, null);
        cursor.moveToFirst();
        int tongTien = cursor.getInt(0);
        closeDB();
        return tongTien;
    }

    public int slDHNVXL(int maNV) {
        openDB();
        String cauLenh = "SELECT COUNT(" + cot_MaDH + ") FROM " + tableSQL + " WHERE " + cot_MaNV + "=" + maNV;
        Cursor cursor = database.rawQuery(cauLenh, null);
        cursor.moveToFirst();
        int sl = cursor.getInt(0);
        closeDB();
        return sl;
    }
}
