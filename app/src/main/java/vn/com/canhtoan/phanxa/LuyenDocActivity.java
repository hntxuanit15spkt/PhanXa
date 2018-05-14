package vn.com.canhtoan.phanxa;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class LuyenDocActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    ImageButton btnLuyenDocBack, btnLuyenDocSound, btnLuyenDocNext, btnLuyenDocSpeak;
    TextView txtCauNoi, txtIndex;
    Intent intent1, intent2;
    Random rand;
    ArrayList<String> myList = new ArrayList<>();

    BufferedReader bufferedReader;
    //ArrayList<CauDoc> listLuyenDoc;
    String line;

    int position = 1;

    TextToSpeech mTts;
    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_doc);

        //myList = (ArrayList<String>) getIntent().getSerializableExtra("lines");
        myList = getIntent().getExtras().getStringArrayList("lines");
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
        btnLuyenDocSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput(line);
            }
        });
        btnLuyenDocSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTts.speak(line, TextToSpeech.QUEUE_FLUSH, null);
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
    //Version cua Toan
    /*private void evNext() {
        position++;
        if (position <= 10) {
            try {
                line = bufferedReader.readLine();
                txtCauNoi.setText(line);
                txtIndex.setText(position + "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(intent2);
        }
    }*/

    //Version cua Xuan
    private void evNext() {
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
    }


    private void addControls() {
        btnLuyenDocBack = findViewById(R.id.btnLuyenDocBack);
        btnLuyenDocNext = findViewById(R.id.btnLuyenDocNext);
        btnLuyenDocSound = findViewById(R.id.btnLuyenDocSound);
        btnLuyenDocSpeak = findViewById(R.id.btnLuyenDocSpeak);
        txtCauNoi = findViewById(R.id.txtCauNoi);
        txtIndex = findViewById(R.id.txtIndex);

        //txtIndex.setText(position+1+"");

        //listLuyenDoc = new ArrayList<CauDoc>();
        //addlistLuyenDoc();
        addCauDau();// Gán giá trị đầu tiên trong file txt vào textview để luyện đọc câu đầu
        generateTextToSpeech();

        // Intent 1 - Come back MainActivity
        intent1 = new Intent(LuyenDocActivity.this, MainActivity.class);
        // Intent 2 - Go to LuyenPhanXaActivity or come back MainActivity
        intent2 = new Intent(LuyenDocActivity.this, ChuyenTiepActivity.class);
    }

    /*Set first value in textview*/
    /*private void addCauDau() {
        InputStream inputStream = getResources().openRawResource(R.raw.danhsachcau);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        try {
            line = bufferedReader.readLine();
            txtCauNoi.setText(line);
            inputStream.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void addCauDau() {
        line = myList.get(0);
        txtCauNoi.setText(line);
    }

    // Create a function Speech to text
    private void getSpeechInput(String sentence) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
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
                }
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

    @Override
    public void onInit(int status) {

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
