package vn.com.canhtoan.phanxa;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.canhtoan.models.LuyenDoc;

public class LuyenDocActivity extends AppCompatActivity {

    ImageButton btnLuyenDocBack, btnLuyenDocSound, btnLuyenDocNext;
    TextView txtCauNoi, txtIndex;
    Intent intent1, intent2;

    ArrayList<LuyenDoc> listLuyenDoc;
    int position;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_doc);
        addControls();
        addEvents();
    }

    private void addEvents() {
        createMedia();
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
        btnLuyenDocSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
    }

    private void evNext() {
        position++;
        if (position > listLuyenDoc.size() - 1){
            startActivity(intent2);
        } else {
            txtIndex.setText(position+1+"");
            mediaPlayer.release();
            createMedia();
        }
    }

    private void addControls() {
        btnLuyenDocBack     = findViewById(R.id.btnLuyenDocBack);
        btnLuyenDocNext     = findViewById(R.id.btnLuyenDocNext);
        btnLuyenDocSound    = findViewById(R.id.btnLuyenDocSound);
        txtCauNoi           = findViewById(R.id.txtCauNoi);
        txtIndex            = findViewById(R.id.txtIndex);

        txtIndex.setText(position+1+"");

        listLuyenDoc = new ArrayList<LuyenDoc>();
        addlistLuyenDoc();

        intent1 = new Intent(LuyenDocActivity.this, MainActivity.class);
        intent2 = new Intent(LuyenDocActivity.this, ChuyenTiepActivity.class);
    }

    private void createMedia(){
        mediaPlayer = MediaPlayer.create(LuyenDocActivity.this, listLuyenDoc.get(position).getSound());
        txtCauNoi.setText(listLuyenDoc.get(position).getSentence());
    }

    private void addlistLuyenDoc() {
        listLuyenDoc.add(new LuyenDoc("Beautiful In White", R.raw.beautifulinwhite_shanefilan));
        listLuyenDoc.add(new LuyenDoc("Cô Gái 1m52?", R.raw.cogai1m52));
        listLuyenDoc.add(new LuyenDoc("It's My life", R.raw.itsmylife_bonjovi));
        listLuyenDoc.add(new LuyenDoc("Nothing Gonna Change My Love", R.raw.nothinggonnachangemylove_westlife));
        listLuyenDoc.add(new LuyenDoc("Sweet Dream", R.raw.sweetdream_jangnara));
    }
}
