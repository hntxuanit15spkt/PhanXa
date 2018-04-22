package vn.com.canhtoan.phanxa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnLuyenDoc, btnLuyenPhanXa;
    Intent intent1, intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuyenDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
        btnLuyenPhanXa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
    }

    private void addControls() {
        btnLuyenDoc     = findViewById(R.id.btnLuyenDoc);
        btnLuyenPhanXa  = findViewById(R.id.btnPhanXa);

        intent1 = new Intent(MainActivity.this, LuyenDocActivity.class);
        intent2 = new Intent(MainActivity.this, LuyenPhanXaActivity.class);
    }
}
