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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_CTKM;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_NXB;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_Sach;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TacGia;
import admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa.Data_TheLoai;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._Sach;
import admin1.ufm.quanlybanhangfahasa.R;


public class Dialog_ThemSach extends AppCompatDialogFragment {
    private EditText txtTenSach,txtNamXB,txtGiaBanGoc,txtSLTon,txtNoiDungSach,txtURLHinhAnh;
    private Spinner spTheLoai,spNXB,spTacGia,spCTKM;
    private View view;

    private Data_TheLoai theLoaiData;
    private Data_NXB nxbData;
    private Data_TacGia tacGiaData;
    private Data_CTKM ctkmData;
    private Data_Sach sachData;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_themsach,null);
        anhXa();
        setSpinner();
        builder.setView(view).setTitle("Thêm Sách").setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (themSach()){
                    Toast.makeText(getActivity(),"Đã thêm sách ",Toast.LENGTH_SHORT).show();
                    listener.CapNhatListView();
                }
                else
                    Toast.makeText(getActivity(),"Thêm Voucher thất bại ",Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
//    ---------------------------------- Implement
    private ExampleDialogListener listener;

    public interface ExampleDialogListener{
        void CapNhatListView(); // QUAN TRONG
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"");
        }
    }
//    ---------------------------------- Implement
    private boolean themSach(){
        String tenSach = txtTenSach.getText().toString().trim();
        String duongDanURL = txtURLHinhAnh.getText().toString().trim();
        String namXB = txtNamXB.getText().toString().trim();
        String string_GiaBanGoc = txtGiaBanGoc.getText().toString().trim();
        String string_sLTon = txtSLTon.getText().toString().trim();
        String noiDung = txtNoiDungSach.getText().toString().trim();
        int giaBanGoc = Integer.parseInt(string_GiaBanGoc);
        int sLTon = Integer.parseInt(string_sLTon);

        if (tenSach.equals("")||duongDanURL.equals("")||namXB.equals("")||string_GiaBanGoc.equals("")||string_sLTon.equals("")||noiDung.equals("")){
            Toast.makeText(getActivity()," Không được để ô trống !",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (giaBanGoc<1000||giaBanGoc>10000000){
            Toast.makeText(getActivity()," Sai giá trị giá bán (lớn hơn 1000 và nhỏ hơn 10.000.000đ) !",Toast.LENGTH_SHORT).show();
            return false;
        }if (sLTon<0||sLTon>200){
            Toast.makeText(getActivity()," Sai giá trị số lượng tồn (lớn hơn 0 và nhỏ hơn 200) !",Toast.LENGTH_SHORT).show();
            return false;
        }

        sachData = new Data_Sach(getActivity());
        if (sachData.themSach(new _Sach(-1,tenSach,noiDung,duongDanURL,namXB,giaBanGoc,sLTon,
                getPosTheLoai(),getPosNXB(),getPosTacGia(),getPosCTKM()))){
            return true;
        }
        return false;
    }
    private void anhXa() {
        txtTenSach =view.findViewById(R.id.txtTenSach);
        txtNamXB =view.findViewById(R.id.txtNamXB);
        txtGiaBanGoc =view.findViewById(R.id.txtGiaBanGoc);
        txtSLTon =view.findViewById(R.id.txtSLTon);
        txtNoiDungSach =view.findViewById(R.id.txtNoiDungSach);
        txtURLHinhAnh=view.findViewById(R.id.txtURLHinhAnh);
        spTheLoai =view.findViewById(R.id.spTheLoai);
        spNXB =view.findViewById(R.id.spNXB);
        spTacGia =view.findViewById(R.id.spTacGia);
        spCTKM =view.findViewById(R.id.spCTKM);
    }

    private void setSpinner(){
        setSpTheLoai();
        setSpNXB();
        setSpTacGia();
        setSpCTKM();
    }

    private int posTheLoai = 1,posNXB = 1,posTacGia = 1,posCTKM = 1;



    public void setSpTheLoai() {
        theLoaiData = new Data_TheLoai(getActivity());
        int dodai = theLoaiData.layDLTheLoai().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTenTheLoai = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlTenTheLoai.add(theLoaiData.layDLTheLoai().get(i).getTenTheLoai());
        }
        arr = dlTenTheLoai.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTheLoai.setAdapter(adapter);
        spTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosTheLoai(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpNXB() {
        nxbData = new Data_NXB(getActivity());
        int dodai = nxbData.layDLNhaXuatBan().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlNhaXuatBan = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlNhaXuatBan.add(nxbData.layDLNhaXuatBan().get(i).getTenNXB());
        }
        arr = dlNhaXuatBan.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNXB.setAdapter(adapter);
        spNXB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosNXB(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpTacGia() {
        tacGiaData = new Data_TacGia(getActivity());
        int dodai = tacGiaData.layDLTacGia().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlTacGia = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlTacGia.add(tacGiaData.layDLTacGia().get(i).getTenTacGia());
        }
        arr = dlTacGia.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTacGia.setAdapter(adapter);
        spTacGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosTacGia(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setSpCTKM() {
        ctkmData = new Data_CTKM(getActivity());
        int dodai = ctkmData.layDlCTKM().size();
        String[] arr = new String[dodai];
        ArrayList<String> dlCTKM = new ArrayList<>();
        for (int i = 0;i<dodai;i++){
            dlCTKM.add(ctkmData.layDlCTKM().get(i).getTenCTKM());
        }
        arr = dlCTKM.toArray(arr);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCTKM.setAdapter(adapter);
        spCTKM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPosCTKM(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public int getPosTheLoai() {
        return posTheLoai;
    }

    public void setPosTheLoai(int posTheLoai) {
        this.posTheLoai = posTheLoai;
    }

    public int getPosNXB() {
        return posNXB;
    }

    public void setPosNXB(int posNXB) {
        this.posNXB = posNXB;
    }

    public int getPosTacGia() {
        return posTacGia;
    }

    public void setPosTacGia(int posTacGia) {
        this.posTacGia = posTacGia;
    }



    public int getPosCTKM() {
        return posCTKM;
    }

    public void setPosCTKM(int posCTKM) {
        this.posCTKM = posCTKM;
    }
}
