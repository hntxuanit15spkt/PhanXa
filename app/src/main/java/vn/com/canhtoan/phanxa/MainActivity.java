package vn.com.canhtoan.phanxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.com.canhtoan.Database.AppDatabase;
import vn.com.canhtoan.Database.utils.DatabaseInitializer;
import vn.com.canhtoan.ViewModel.CauPhanXaViewModel;


public class MainActivity extends AppCompatActivity {

    Button btnLuyenDoc, btnLuyenPhanXa;
    Intent intent1, intent2, intent3;
    private AppDatabase mDb;
    private CauPhanXaViewModel cauPhanXaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        mDb = AppDatabase.getDatabase(getApplicationContext());
        populateDb();
        /*cauPhanXaViewModel = ViewModelProviders.of(this).get(CauPhanXaViewModel.class);
        cauPhanXaViewModel.getmAllCauPhanXa().observe(this, new Observer<List<CauPhanXaEntity>>() {
            @Override
            public void onChanged(@Nullable List<CauPhanXaEntity> cauPhanXaEntities) {

            }
        });
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCauPhanXaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void populateDb() {
        DatabaseInitializer.populateAsync(mDb);
    }

    //Hàm này sẽ nhận kết quả từ startActivityForResult trả về từ activity NewWordActivity
    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            CauPhanXaEntity cauPhanXaEntity = new CauPhanXaEntity(data.getStringExtra("cauphanxa_sql"));
            CauPhanXaViewModel cauPhanXaViewModel = new CauPhanXaViewModel(this.getApplication());
            cauPhanXaViewModel.insert(cauPhanXaEntity);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "chua luu",
                    Toast.LENGTH_LONG).show();
        }
    }*/

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
        alert.setTitle("Hướng dẫn");
        alert.setMessage("Bạn hãy đọc theo câu được hiển thị trên màn hình thật nhanh nhé vì thời gian là có hạn");
        alert.setNegativeButton("OK, Đã hiểu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Bạn chuẩn bị luyện đọc!");
                alert.setMessage("Bạn có muốn thêm dữ liệu");
                alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(intent1);
                    }
                });
                alert.setPositiveButton("Có, tôi muốn", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent2);
                    }
                });
                alert.show();
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