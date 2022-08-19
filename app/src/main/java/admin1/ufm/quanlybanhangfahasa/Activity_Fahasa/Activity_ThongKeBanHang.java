package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_DonHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ThongKeThang;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;
import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_ThongKeBanHang extends AppCompatActivity {

    private ImageButton imgBtnBack;
    private PieChart bieuDoTron;
    private BarChart bieuDoCot;
    private TextView lbNgayHienTai, lbTongSLDH, lbSLDHXL, lbTongTien, lbDoanhSoNgay, lbDoanhSoThang, lbDoanhSoNam;
    private Spinner spFilter;
    private Data_DonHang donHangData;
    private Data_ChiTietDH chiTietDHData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahasa_thongkebanhang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setBieuDoTron1();
        setBieuDoCot();
        setSpFilter();
        setThongTin();
        setImgBtnBack();
    }

    private String chuyenDoi(float doanhThu){
        long number = Double.valueOf(doanhThu).longValue();
        String sNumber = String.valueOf(number);
        sNumber = themPhay(sNumber);
        return sNumber;
    }

    private void setThongTin() {
        lbNgayHienTai.setText(layNgayHT());
        lbTongSLDH.setText(sTongCong);
        lbSLDHXL.setText(slDHXL + "");
        lbTongTien.setText(chuyenDoi(doanhThuNam));
        lbDoanhSoNam.setText(chuyenDoi(doanhThuNam));
        lbDoanhSoThang.setText(chuyenDoi(doanhThuThang));
        lbDoanhSoNgay.setText(chuyenDoi(doanhThuNgay));
    }

    public String themPhay(String a) {
        int b = Integer.parseInt(a);
        String kq = String.format("%,d%n", b);
        return kq;
    }

    private void setBieuDoCot() {
        BarChart barChart = findViewById(R.id.bieuDoCot);
        donHangData = new Data_DonHang(this);
        ArrayList<_ThongKeThang> tkThangList = donHangData.thongKeThang(2021);
        ArrayList<String> labelNames = new ArrayList<>();
        ArrayList<BarEntry> barEntryThongKeDH = new ArrayList<>();

        for (int i = 0; i < tkThangList.size(); i++) {
            String thang = tkThangList.get(i).getThang();
            int slDH = tkThangList.get(i).getSlDH();
            barEntryThongKeDH.add(new BarEntry(i, slDH)); // dữ liệu
            labelNames.add(thang); // Nhãn
        }
        BarDataSet barDataSet = new BarDataSet(barEntryThongKeDH, "Thống kê số lượng đơn hàng");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("Tháng");
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelNames.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();
    }

    private float hoantat = 0, chuaxuly = 0, bihuy = 0, slDHXL = 0;
    private float doanhThuNam = 0, doanhThuThang = 0, doanhThuNgay = 0;
    private String sTongCong;

    private void setBieuDoTron1() {
        donHangData = new Data_DonHang(this);
        ArrayList<_DonHang> donHangList = new ArrayList<>();
        donHangList = donHangData.getDLDonHang(-1);

        for (int i = 0; i < donHangList.size(); i++) {
            if (!donHangList.get(i).getNgayXuLy().equals("")) {
                if (donHangList.get(i).getGhiChu().equals("Hoàn tất")) {
                    slDHXL++;
                    hoantat++;
                    doanhThuNam = doanhThuNam + donHangList.get(i).getTongTien();
                    String ngayXuLy = donHangList.get(i).getNgayXuLy();
                    String thang = ngayXuLy.substring(3, 5); //    07/12/2000
                    String thangHT = layNgayHT().substring(3, 5);
                    if (thang.equals(thangHT)) {
                        doanhThuThang = doanhThuThang + donHangList.get(i).getTongTien();
                        System.out.println("doanhthuThang:"+doanhThuThang);
                        String ngay = ngayXuLy.substring(0, 2);
                        String ngayHT = layNgayHT().substring(0, 2);
                        if (ngay.equals(ngayHT)) {
                            doanhThuNgay = doanhThuNgay + donHangList.get(i).getTongTien();
                            System.out.println("doanhThuNgay:"+doanhThuNgay);
                        }
                    }

                } else {
                    bihuy++;
                }
            } else {
                chuaxuly++;
            }
        }
        float tongCong = hoantat + bihuy + chuaxuly;
        ArrayList<Float> thongKe = new ArrayList<>();
        hoantat = (hoantat / tongCong) * 100;
        bihuy = (bihuy / tongCong) * 100;
        chuaxuly = (chuaxuly / tongCong) * 100;

        sTongCong = String.valueOf(tongCong);
        sTongCong = sTongCong.substring(0, sTongCong.length() - 2);

        ArrayList<PieEntry> visitor = new ArrayList<>();
        visitor.add(new PieEntry(hoantat, "Hoàn tất"));
        visitor.add(new PieEntry(bihuy, "Chưa xử lý"));
        visitor.add(new PieEntry(chuaxuly, "Bị hủy"));

        PieDataSet pieDataSet = new PieDataSet(visitor, "Tổng SL đơn hàng: " + sTongCong);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f); // size của giá trị value trên

        PieData pieData = new PieData(pieDataSet);

        bieuDoTron.setData(pieData);
        bieuDoTron.getDescription().setEnabled(false);
        bieuDoTron.setCenterText("Xử lý đơn hàng");
        bieuDoTron.animate();
    }


    private void setSpFilter() {
        String[] arr = {"Biểu đồ tỷ lệ đơn hàng", "Thống kê đơn hàng qua các tháng"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFilter.setAdapter(adapter);
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    bieuDoTron.setVisibility(View.VISIBLE);
                    bieuDoCot.setVisibility(View.GONE);
                } else {
                    bieuDoTron.setVisibility(View.GONE);
                    bieuDoCot.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setImgBtnBack() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        bieuDoTron = findViewById(R.id.bieuDoTron);
        bieuDoCot = findViewById(R.id.bieuDoCot);
        lbNgayHienTai = findViewById(R.id.lbNgayHienTai);
        lbTongSLDH = findViewById(R.id.lbTongSLDH);
        lbSLDHXL = findViewById(R.id.lbSLDHXL);
        lbTongTien = findViewById(R.id.lbTongTien);
        lbDoanhSoNgay = findViewById(R.id.lbDoanhSoNgay);
        lbDoanhSoThang = findViewById(R.id.lbDoanhSoThang);
        lbDoanhSoNam = findViewById(R.id.lbDoanhSoNam);
        spFilter = findViewById(R.id.spFilter);
        imgBtnBack = findViewById(R.id.imgBtnBack);
    }

    private String layNgayHT() {
        Calendar cld = Calendar.getInstance();
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH) + 1;
        int day = cld.get(Calendar.DAY_OF_MONTH);
        String Nday = "" + day;
        String Nmonth = "" + month;
        if (day < 10 && day > 0) {
            Nday = "0" + day;
        }
        if (month < 10 && month > 0) {
            Nmonth = "0" + month;
        }
        String ngayHienTai = Nday + "/" + Nmonth + "/" + year;
        return ngayHienTai;
    }
}