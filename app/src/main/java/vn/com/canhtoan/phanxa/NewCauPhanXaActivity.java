package vn.com.canhtoan.phanxa;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCauPhanXaActivity extends AppCompatActivity {

    private EditText mEditCauPhanXa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cauphanxa);
        mEditCauPhanXa = findViewById(R.id.edit_cauphanxa);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(mEditCauPhanXa.getText()))
                {
                    setResult(RESULT_CANCELED, intent);
                }
                else{
                    String question = mEditCauPhanXa.getText().toString();
                    intent.putExtra("cauphanxa_sql", question);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}
