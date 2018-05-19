package vn.com.canhtoan.phanxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnLuyenDoc, btnLuyenPhanXa;
    Intent intent1, intent2, intent3;

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
                xuLyLuyenDoc();
            }
        });
        btnLuyenPhanXa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
            }
        });
    }

    private void xuLyLuyenDoc() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Bạn chuẩn bị luyện đọc!");
        alert.setMessage("Bạn có muốn thêm dữ liệu");
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent1);
            }
        });
        alert.setPositiveButton("Có, tôi muốn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent2);
            }
        });
        alert.show();
    }

    private void addControls() {
        btnLuyenDoc = findViewById(R.id.btnLuyenDoc);
        btnLuyenPhanXa = findViewById(R.id.btnPhanXa);

        intent1 = new Intent(MainActivity.this, LuyenDocActivity.class);
        intent2 = new Intent(MainActivity.this, FileChooserActivity.class);
        intent3 = new Intent(MainActivity.this, LuyenPhanXaActivity.class);
    }
}