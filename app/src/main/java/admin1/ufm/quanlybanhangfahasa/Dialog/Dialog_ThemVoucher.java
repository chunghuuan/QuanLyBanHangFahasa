package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Voucher;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Voucher;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_ThemVoucher extends AppCompatDialogFragment {
    private EditText txtMaVoucher, txtTyLeGiam, txtNoiDung;
    Data_Voucher voucherData;
    _Voucher voucher;
    private View view;
    private String maVoucher, noiDung;
    private float tyLeGiam = -1;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_themvoucher, null);
        anhXa();
        builder.setView(view).setTitle("Thêm Voucher").setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (themVoucher()) {
                    Toast.makeText(getActivity(), "Đã thêm Voucher " + maVoucher, Toast.LENGTH_SHORT).show();
                    listener.CapNhatListView();
                } else
                    Toast.makeText(getActivity(), "Thêm Voucher thất bại ", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
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
            throw new ClassCastException(context.toString() + "");
        }
    }

    //---------------------------------- Implement
    private void anhXa() {
        txtMaVoucher = view.findViewById(R.id.txtMaVoucher);
        txtTyLeGiam = view.findViewById(R.id.txtTyLeGiam);
        txtNoiDung = view.findViewById(R.id.txtNoiDung);
    }

    private boolean themVoucher() {
        maVoucher = txtMaVoucher.getText().toString().trim();
        String string_tyLeGiam = txtTyLeGiam.getText().toString().trim();
        tyLeGiam = Float.parseFloat(txtTyLeGiam.getText().toString().trim());
        noiDung = txtNoiDung.getText().toString().trim();
        if (maVoucher.equals("") || noiDung.equals("") || string_tyLeGiam.equals("")) {
            Toast.makeText(getActivity(), " Không được để ô trống !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tyLeGiam > 1 || tyLeGiam < 0) {
            Toast.makeText(getActivity(), " Nhập sai tỷ lệ giảm (>1||<0)", Toast.LENGTH_SHORT).show();
            return false;
        }
        voucherData = new Data_Voucher(getActivity());
        if (voucherData.themVoucher(new _Voucher(maVoucher, noiDung, tyLeGiam))) {
            return true;
        }
        return false;
    }
}
