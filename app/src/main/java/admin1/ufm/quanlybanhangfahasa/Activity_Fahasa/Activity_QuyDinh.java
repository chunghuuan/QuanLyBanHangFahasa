package admin1.ufm.quanlybanhangfahasa.Activity_Fahasa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import admin1.ufm.quanlybanhangfahasa.R;


public class Activity_QuyDinh extends AppCompatActivity {

    private ImageButton imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_dinh);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide(); // ẨN ACTION BAR
        anhXa();
        setImgBtnBack();
    }

    private void anhXa() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
    }
    private void setImgBtnBack(){
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}