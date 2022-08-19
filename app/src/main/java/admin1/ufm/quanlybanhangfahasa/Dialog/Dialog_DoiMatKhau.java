package admin1.ufm.quanlybanhangfahasa.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_DoiMatKhau extends AppCompatDialogFragment {
    private EditText txtPassCu,txtPass1,txtPass2;
    private View view;
    Data_TaiKhoan taiKhoanData;
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_doimatkhau,null);
        anhXa();
        nhanGT();
        builder.setView(view).setTitle("Đổi mật khẩu").setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (doiMK()){
                    Toast.makeText(getActivity(),"Đổi mật khẩu thành công !",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(),"Đổi thất bại !",Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
    private String maTK;
    private void nhanGT(){
        String maTK = getArguments().getString("maTK");
        this.maTK = maTK;
    }
    private String passCu,pass1,pass2;

    private void anhXa() {
        txtPassCu = view.findViewById(R.id.txtPassCu);
        txtPass1 = view.findViewById(R.id.txtPass1);
        txtPass2 = view.findViewById(R.id.txtPass2);
    }

    private boolean doiMK(){
        // Kiểm tra rỗng
        passCu = txtPassCu.getText().toString().trim();
        pass1 = txtPass1.getText().toString().trim();
        pass2 = txtPass2.getText().toString().trim();
        if (passCu.equals("")||pass1.equals("")||pass2.equals("")){
            Toast.makeText(getActivity()," Không được để trống !",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pass1.equals(pass2)){
        }
        else {
            Toast.makeText(getActivity(), "Mật khẩu chưa trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }
        taiKhoanData = new Data_TaiKhoan(getActivity());
        String matkhau = taiKhoanData.layMatKhau(this.maTK).trim();
        if (passCu.equals(matkhau)){
        }
        else{
            Toast.makeText(getActivity(), "Nhập sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (taiKhoanData.doiMatKhau(pass2)){
            return true;
        };
        return false;
    }
}
