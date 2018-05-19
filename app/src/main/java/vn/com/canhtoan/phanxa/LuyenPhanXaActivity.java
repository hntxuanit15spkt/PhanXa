package vn.com.canhtoan.phanxa;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import vn.com.canhtoan.model.CauPhanXa;

public class LuyenPhanXaActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    ArrayList<CauPhanXa> dsCauPhanXa;

    ImageButton btnLuyenPhanXaBack, btnLuyenPhanXaNext, btnLuyenPhanXaSound;
    TextView txtCauHoi;
    int position = 0, soCauTraLoiDung = 0;
    Intent intent;
    TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_phan_xa);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtCauHoi = findViewById(R.id.txtCauHoi);
        btnLuyenPhanXaBack = findViewById(R.id.btnLuyenPhanXaBack);
        btnLuyenPhanXaNext = findViewById(R.id.btnLuyenPhanXaNext);
        btnLuyenPhanXaSound = findViewById(R.id.btnLuyenPhanXaSound);
        intent = new Intent(LuyenPhanXaActivity.this, MainActivity.class);
        addData(dsCauPhanXa);

        startLuyenDoc();
    }

    private void addEvents() {
        btnLuyenPhanXaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLuyenPhanXaNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evNext();
            }
        });
    }

    private void startLuyenDoc() {
        final AlertDialog.Builder alertLPX = new AlertDialog.Builder(LuyenPhanXaActivity.this);
        alertLPX.setTitle("Bắt đầu!");
        alertLPX.setNegativeButton("QUAY VỀ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
            }
        });
        alertLPX.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                txtCauHoi.setText(dsCauPhanXa.get(0).getCauHoi());
                disable();
                speakText();
                startAutoSpeech();
            }
        });
        alertLPX.show();
    }

    private void evNext() {
        position++;
        if (position < dsCauPhanXa.size()){
            txtCauHoi.setText(dsCauPhanXa.get(position).getCauHoi());
            disable();
            speakText();
            startAutoSpeech();
        }
        else {
            finishLuyenPhanXa();
        }
    }

    private void disable(){
        btnLuyenPhanXaSound.setClickable(false);
        btnLuyenPhanXaNext.setVisibility(View.INVISIBLE);
    }

    private void speakText() {
        mTts.speak(dsCauPhanXa.get(position).getCauHoi(), TextToSpeech.QUEUE_FLUSH, null);
    }

    private void startAutoSpeech() {
        CountDownTimer timer = new CountDownTimer(2500, 2500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getSpeechInput();
            }
        };
        timer.start();
    }

    private void getSpeechInput() {
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Trả lời");
        try {
            startActivityForResult(intent, 10);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Device not suppost", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    testResult(result);
                }
            }
            case 88: {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    mTts = new TextToSpeech(LuyenPhanXaActivity.this, this);
                } else {
                    Intent installIntent = new Intent();
                    installIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                    startActivity(installIntent);
                }
            }
        }
    }

    private void testResult(ArrayList result) {
        String input = (String) result.get(0);
        String output1 = dsCauPhanXa.get(position).getCauTraLoi1();
        String output2 = dsCauPhanXa.get(position).getCauTraLoi2();
        if ((input.toLowerCase()).equals(output1.toLowerCase()) || (input.toLowerCase()).equals(output2.toLowerCase())){
            Toast.makeText(LuyenPhanXaActivity.this, "Bạn đã trả lời chính xác!", Toast.LENGTH_SHORT).show();
            soCauTraLoiDung++;
        }
        else {
            Toast.makeText(LuyenPhanXaActivity.this, "Bạn trả lời chưa đúng!", Toast.LENGTH_SHORT).show();
        }
        btnLuyenPhanXaSound.setClickable(true);
        btnLuyenPhanXaNext.setVisibility(View.VISIBLE);
    }

    private void finishLuyenPhanXa() {
        AlertDialog.Builder alert = new AlertDialog.Builder(LuyenPhanXaActivity.this);
        alert.setTitle("Kết thúc luyện phản xạ");
        alert.setMessage("Bạn đã hoàn thành luyện phản xạ với " + soCauTraLoiDung + " câu trả lời đúng");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(LuyenPhanXaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alert.show();
    }

    private void addData(ArrayList<CauPhanXa> dsCauPhanXa) {
        dsCauPhanXa.add(new CauPhanXa("What is your name?","John", "My name is John"));
        dsCauPhanXa.add(new CauPhanXa("What is your job?","Student", "I am a student"));
        dsCauPhanXa.add(new CauPhanXa("Do you have a girl friend?","No", "No, i do not"));
        dsCauPhanXa.add(new CauPhanXa("How are you?","I am fine", "Fine"));
        dsCauPhanXa.add(new CauPhanXa("How old are you?","eighteen", "I am eighteen years old"));
        dsCauPhanXa.add(new CauPhanXa("Where are you from?","Viet Nam", "I from Viet Nam"));
        dsCauPhanXa.add(new CauPhanXa("What is your hobby?","Reading book", "My hobby is reading book"));
        dsCauPhanXa.add(new CauPhanXa("1 + 1 = ?","2", "two"));
        dsCauPhanXa.add(new CauPhanXa("Are you like American?","Yes", "Yes I am"));
        dsCauPhanXa.add(new CauPhanXa("Are you tired?","Yes", "Yes I am"));
    }

    @Override
    public void onInit(int status) {

    }
}




















