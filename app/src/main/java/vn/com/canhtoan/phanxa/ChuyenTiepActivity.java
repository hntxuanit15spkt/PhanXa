package vn.com.canhtoan.phanxa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChuyenTiepActivity extends AppCompatActivity {

    Button btnTiepTuc, btnKhongTiepTuc;
    Intent intent1, intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_tiep);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnKhongTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
    }

    private void addControls() {
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnKhongTiepTuc = findViewById(R.id.btnKhongTiepTuc);

        intent1 = new Intent(ChuyenTiepActivity.this, MainActivity.class);
        intent2 = new Intent(ChuyenTiepActivity.this, LuyenPhanXaActivity.class);
    }
}
