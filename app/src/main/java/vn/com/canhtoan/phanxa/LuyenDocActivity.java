package vn.com.canhtoan.phanxa;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import vn.com.canhtoan.model.CauDoc;

public class LuyenDocActivity extends AppCompatActivity {

    ImageButton btnLuyenDocBack, btnLuyenDocSound, btnLuyenDocNext;
    TextView txtCauNoi, txtIndex;
    Intent intent1, intent2;

    BufferedReader bufferedReader;
    //ArrayList<CauDoc> listLuyenDoc;
    int position=1;

    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_doc);
        addControls();
        addEvents();
    }

    private void addEvents() {
        //createMedia();
        btnLuyenDocBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLuyenDocNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evNext();
            }
        });
        /*btnLuyenDocSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });*/
    }

    // Ever next that is set next value from txt file into textview to display for speak practice
    private void evNext() {
        position++;
        if (position <= 10){
            try {
                String line = bufferedReader.readLine();
                txtCauNoi.setText(line);
                txtIndex.setText(position+"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(intent2);
        }
    }

    /*private void evNext() {
        position++;
        if (position > listLuyenDoc.size() - 1){
            startActivity(intent2);
        } else {
            txtIndex.setText(position+1+"");
            mediaPlayer.release();
            createMedia();
        }
    }*/

    private void addControls() {
        btnLuyenDocBack     = findViewById(R.id.btnLuyenDocBack);
        btnLuyenDocNext     = findViewById(R.id.btnLuyenDocNext);
        btnLuyenDocSound    = findViewById(R.id.btnLuyenDocSound);
        txtCauNoi           = findViewById(R.id.txtCauNoi);
        txtIndex            = findViewById(R.id.txtIndex);

        //txtIndex.setText(position+1+"");

        //listLuyenDoc = new ArrayList<CauDoc>();
        //addlistLuyenDoc();
        addCauDau();// Gán giá trị đầu tiên trong file txt vào textview để luyện đọc câu đầu

        // Intent 1 - Come back MainActivity
        intent1 = new Intent(LuyenDocActivity.this, MainActivity.class);
        // Intent 2 - Go to LuyenPhanXaActivity or come back MainActivity
        intent2 = new Intent(LuyenDocActivity.this, ChuyenTiepActivity.class);
    }

    /*Set first value in textview*/
    private void addCauDau() {
        InputStream inputStream             = getResources().openRawResource(R.raw.danhsachcau);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader                      = new BufferedReader(inputStreamReader);
        try {
            String line = bufferedReader.readLine();
            txtCauNoi.setText(line);
            inputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void createMedia(){
        mediaPlayer = MediaPlayer.create(LuyenDocActivity.this, listLuyenDoc.get(position).getSound());
        txtCauNoi.setText(listLuyenDoc.get(position).getSentence());
    }*/

   /* private void addlistLuyenDoc() {
        listLuyenDoc.add(new CauDoc("Beautiful In White", R.raw.beautifulinwhite_shanefilan, 10));
        listLuyenDoc.add(new CauDoc("Cô Gái 1m52?", R.raw.cogai1m52));
        listLuyenDoc.add(new CauDoc("It's My life", R.raw.itsmylife_bonjovi));
        listLuyenDoc.add(new CauDoc("Nothing Gonna Change My Love", R.raw.nothinggonnachangemylove_westlife));
        listLuyenDoc.add(new CauDoc("Sweet Dream", R.raw.sweetdream_jangnara));
    }*/
}
