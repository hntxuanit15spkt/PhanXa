package vn.com.canhtoan.phanxa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LuyenPhanXaActivity extends AppCompatActivity {

    ImageButton btnLuyenPhanXaBack, btnLuyenPhanXaRecorder;
    TextView txtCoutdown, txtCauHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_phan_xa);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuyenPhanXaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        txtCauHoi               = findViewById(R.id.txtCauHoi);
        txtCoutdown             = findViewById(R.id.txtCountDown);
        btnLuyenPhanXaBack      = findViewById(R.id.btnLuyenPhanXaBack);
        btnLuyenPhanXaRecorder  = findViewById(R.id.btnLuyenPhanXaRecorder);
    }
}
