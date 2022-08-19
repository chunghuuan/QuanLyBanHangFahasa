package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_SoHuu;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Voucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_ThemVoucherKH extends AppCompatDialogFragment {
    private View view;
    private Spinner spVoucher;
    private TextView lbTyLeGiam;
    private ImageButton btnTang, btnGiam;
    private EditText txtSoLuong;
    private Data_Voucher voucherData;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_themvoucherkh, null);
        anhXa();
        nhanGT();
        setBtnTangGiam();
        setSpVoucher();
        builder.setView(view).setTitle("Thêm Voucher").setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (themVoucherKH()) {
                    listener.CapNhatListView();
                }
            }
        });
        return builder.create();
    }

    private int maTK = -1;

    private void nhanGT() {
        Bundle bundle = getArguments();
        this.maTK = bundle.getInt("maTK");
    }

    //---------------------------------- Implement
    private ExampleDialogListener listener;

    public interface ExampleDialogListener {
        void CapNhatListView(); // QUAN TRONG
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {

        }
    }

    //---------------------------------- Implement
    private void anhXa() {
        spVoucher = view.findViewById(R.id.spVoucher);
        lbTyLeGiam = view.findViewById(R.id.lbTyLeGiam);
        btnTang = view.findViewById(R.id.btnTang);
        btnGiam = view.findViewById(R.id.btnGiam);
        txtSoLuong = view.findViewById(R.id.txtSoLuong);
        lbTyLeGiam = view.findViewById(R.id.lbTyLeGiam);
    }

    private String maVoucher = "";

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    private ArrayList<_Voucher> voucherList;

    public void setSpVoucher() {
        voucherData = new Data_Voucher(getActivity());
        voucherList = new ArrayList<>();
        voucherList = voucherData.getDLVoucher();
        int dodai = voucherList.size();
        String[] arr = new String[dodai];
        ArrayList<String> dlVoucher = new ArrayList<>();
        for (int i = 0; i < dodai; i++) {
            dlVoucher.add(voucherList.get(i).getMaVoucher());
        }
        arr = dlVoucher.toArray(arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVoucher.setAdapter(adapter);
        spVoucher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setMaVoucher(spVoucher.getSelectedItem().toString());
                float tyLeGiam = voucherList.get(position).getTyLeGiam();
                tyLeGiam = tyLeGiam * 100;
                String sTyLeGiam = String.valueOf(tyLeGiam);
                sTyLeGiam = sTyLeGiam.substring(0, sTyLeGiam.length() - 2) + "%";
                lbTyLeGiam.setText(sTyLeGiam);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setMaVoucher(spVoucher.getSelectedItem().toString());
            }
        });
    }

    private void setBtnTangGiam() {
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(txtSoLuong.getText().toString());
                if (soLuong == 50) {
                    return;
                }
                soLuong++;
                txtSoLuong.setText(soLuong + "");
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(txtSoLuong.getText().toString());
                if (soLuong == 0) {
                    return;
                }
                soLuong--;
                txtSoLuong.setText(soLuong + "");
            }
        });
    }

    private boolean themVoucherKH() {
        maVoucher = getMaVoucher();
        int soluong = Integer.parseInt(txtSoLuong.getText().toString());
        Data_SoHuu soHuuData = new Data_SoHuu(getActivity());
        if (!soHuuData.themVoucherKH(maTK, maVoucher, soluong)) {
            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
