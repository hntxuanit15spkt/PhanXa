package vn.com.canhtoan.phanxa;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class LuyenDocActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    ImageButton btnLuyenDocBack, btnLuyenDocSound, btnLuyenDocNext, btnLuyenDocPrevous, btnLuyenDocSpeak;
    TextView txtCauNoi, txtLoiNoi, txtIndex;
    Intent intent1, intent2;
    Random rand;
    ArrayList<String> myList = new ArrayList<>();

    ArrayList<String> listCau;
    BufferedReader bufferedReader;
    String line;

    int position = 1, soCauDung = 0, perfect = 0, timeout = 2500, cauHienTai = 1;
    boolean docDungLanThuNhat = true, docLanThuNhat = true;

    TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_doc);

        //myList = (ArrayList<String>) getIntent().getSerializableExtra("lines");
        //myList = getIntent().getExtras().getStringArrayList("lines");
        addControls();
        addEvents();
    }

    private void addControls() {
        btnLuyenDocBack = findViewById(R.id.btnLuyenDocBack);
        btnLuyenDocNext = findViewById(R.id.btnLuyenDocNext);
        btnLuyenDocPrevous = findViewById(R.id.btnLuyenDocPrevous);
        btnLuyenDocSound = findViewById(R.id.btnLuyenDocSound);
        btnLuyenDocSpeak = findViewById(R.id.btnLuyenDocSpeak);
        txtCauNoi = findViewById(R.id.txtCauNoi);
        txtLoiNoi = findViewById(R.id.txtLoiNoi);
        txtIndex = findViewById(R.id.txtIndex);

        listCau = new ArrayList<String>();
        addData();
        generateTextToSpeech();

        // Intent 1 - Come back MainActivity
        intent1 = new Intent(LuyenDocActivity.this, MainActivity.class);
        // Intent 2 - Go to LuyenPhanXaActivity
        intent2 = new Intent(LuyenDocActivity.this, LuyenPhanXaActivity.class);

        // Start Alert Luyện Đọc
        startLuyenDoc();
        // Auto start Speech to text
        //startAutoSpeech();
    }

    private void addEvents() {
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
        btnLuyenDocPrevous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evPrevous();
            }
        });
        btnLuyenDocSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput(listCau.get(position - 1));
            }
        });
        btnLuyenDocSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakText();
            }
        });
    }

    // Alert start Luyen Doc
    private void startLuyenDoc() {
        final AlertDialog.Builder alertLD = new AlertDialog.Builder(LuyenDocActivity.this);
        alertLD.setTitle("Bắt đầu!");
        alertLD.setNegativeButton("QUAY VỀ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent1);
            }
        });
        alertLD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                txtIndex.setText(1 + "/" + listCau.size());
                txtCauNoi.setText(listCau.get(0));
                txtLoiNoi.setText("");
                speakText();
                startAutoSpeech();
            }
        });
        alertLD.show();
    }

    // Auto start Speech to text
    private void startAutoSpeech() {
        CountDownTimer timer = new CountDownTimer(timeout, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getSpeechInput(listCau.get(position - 1));
            }
        };
        timer.start();
    }

    // Speak ever sentences
    private void speakText() {
        //mTts.speak(line, TextToSpeech.QUEUE_FLUSH, null);
        mTts.speak(listCau.get(position - 1), TextToSpeech.QUEUE_FLUSH, null);
    }

    // Ever next that is set next value from txt file into textview to display for speak practice
    //Version cua Toan
    // Seeson 2
    private void evNext() {
        position++;
        if (position <= listCau.size()) {
            txtLoiNoi.setText("");
            txtCauNoi.setText(listCau.get(position - 1));
            txtIndex.setText(position + "/" + listCau.size());
            speakText();
            if (position > cauHienTai) {
                cauHienTai++;
                docLanThuNhat = docDungLanThuNhat = true;
                startAutoSpeech();
            }
        } else {
            finishLuyenDoc();
        }
    }

    //Version cua Xuan
    /*private void evNext() {
        int sizeMyList = myList.size();
        int randomIndex;
        rand = new Random();
        if (sizeMyList > 0) {
            if (sizeMyList == 1) {
                line = myList.get(0);
                myList.remove(myList.get(0));
            } else {
                randomIndex = rand.nextInt(sizeMyList - 1) + 1;
                line = myList.get(randomIndex);
                txtCauNoi.setText(line);
                myList.remove(myList.get(randomIndex));
            }

        } else {
            startActivity(intent2);
        }
    }*/

    private void evPrevous() {
        position--;
        if (position > 0){
            txtLoiNoi.setText("");
            txtCauNoi.setText(listCau.get(position-1));
            txtIndex.setText(position+"/" + listCau.size());
            docLanThuNhat = docDungLanThuNhat = false;
            speakText();
        }else {
            position++;
            Toast.makeText(LuyenDocActivity.this, "Đã đến câu đầu tiên!", Toast.LENGTH_SHORT).show();
        }
    }

    // addCauDau Season 2 --> Ứng với evNext2
    private void addData() {
        InputStream inputStream = getResources().openRawResource(R.raw.danhsachcau);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        try {
            line = bufferedReader.readLine();

            while (line != null) {
                listCau.add(line);
                line = bufferedReader.readLine();
            }
            inputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void addCauDau() {
        line = myList.get(0);
        txtCauNoi.setText(line);
    }*/

    // Create a function Speech to text
    private void getSpeechInput(String sentence) {
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, sentence);
        try {
            startActivityForResult(intent, 10);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Device not suppost", Toast.LENGTH_SHORT).show();
        }
    }

    // Create a function Text to speech
    public void generateTextToSpeech() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, 88);
    }

    // Return result that is text from speech
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //txtResult.setText(result.get(0));
                    txtLoiNoi.setText(result.get(0));
                    testResult(result);
                }
                else txtLoiNoi.setText("Không trả lời");
            }
        }
        if (requestCode == 88) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                mTts = new TextToSpeech(LuyenDocActivity.this, this);
                /*mTts.setLanguage(Locale.US);*/
            } else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void testResult(ArrayList result) {
        String input = (String) result.get(0);
        //if ((input.toLowerCase()).equals(line.toLowerCase())){
        if ((input.toLowerCase()).equals(listCau.get(position-1).toLowerCase())){
            Toast.makeText(LuyenDocActivity.this, "Chúc mừng, bạn đã đọc chính xác", Toast.LENGTH_SHORT).show();
            if (docDungLanThuNhat) {
                soCauDung++;
                docDungLanThuNhat = false;
            }
            if (docLanThuNhat){
                perfect++;
                docLanThuNhat = false;
            }
            if (position == 10) {
                finishLuyenDoc();
            }
        }
        else {
            Toast.makeText(LuyenDocActivity.this, "Rất tiếc, bạn nói chưa đúng", Toast.LENGTH_LONG).show();
            docDungLanThuNhat = docLanThuNhat = false;
        }
    }

    private void finishLuyenDoc() {
        AlertDialog.Builder alert = new AlertDialog.Builder(LuyenDocActivity.this);
        alert.setTitle("Luyện đọc");
        if (perfect == 10){
            alert.setMessage("Bạn rất xuất sắc hoàn thành luyện đọc với 10 điểm tuyệt đối! Bạn có muốn tiếp tục Luyên phản xạ?");
        }
        else alert.setMessage("Bạn đã hoàn thành phần luyện đọc với " + soCauDung + " điểm, bạn có muốn luyện phản xạ?");
        alert.setNeutralButton("CÓ, TÔI MUỐN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent2);
            }
        });
        alert.setNegativeButton("LUYỆN ĐỌC LẠI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = cauHienTai = 1;
                txtIndex.setText(1+"/10");
                soCauDung = perfect = 0;
                docLanThuNhat = docDungLanThuNhat = true;
                addData();
                startLuyenDoc();
            }
        });
        alert.setPositiveButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent1);
            }
        });
        alert.show();
    }

    @Override
    public void onInit(int status) {

    }
}
