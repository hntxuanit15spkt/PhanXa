package vn.com.canhtoan.phanxa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vn.com.canhtoan.Database.AppDatabase;
import vn.com.canhtoan.Database.Entity.CauDocEntity;

public class FileChooserActivity extends Activity {

    private AppDatabase mDb;

    BufferedReader bufferedReader;
    String line;
    TextView txtCauNoi, txtIndex;
    int position = 1;

    Button buttonOpenDialog;
    Button buttonUp;
    TextView textFolder;

    String KEY_TEXTPSS = "TEXTPSS";
    static final int CUSTOM_DIALOG_ID = 0;
    ListView dialog_ListView;

    File root;
    File curFolder;

    private List<String> fileList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_layout);
        buttonOpenDialog = (Button) findViewById(R.id.opendialog);
        buttonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CUSTOM_DIALOG_ID);
            }
        });

        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;
    }

    private void addControls() {
        txtCauNoi = findViewById(R.id.txtCauNoi);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch (id) {
            case CUSTOM_DIALOG_ID:
                dialog = new Dialog(FileChooserActivity.this);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.setTitle("Custom Dialog");
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                textFolder = (TextView) dialog.findViewById(R.id.folder);
                buttonUp = (Button) dialog.findViewById(R.id.up);
                buttonUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ListDir(curFolder.getParentFile());
                    }
                });

                dialog_ListView = (ListView) dialog.findViewById(R.id.dialoglist);
                dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        File selected = new File(fileList.get(position));
                        if (selected.isDirectory()) {
                            ListDir(selected);
                        } else {
                            Toast.makeText(FileChooserActivity.this, selected.toString() + " selected",
                                    Toast.LENGTH_LONG).show();
                            dismissDialog(CUSTOM_DIALOG_ID);
                            try {
                                InputStream inputStream = new FileInputStream(selected);
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                                bufferedReader = new BufferedReader(inputStreamReader);
                                ArrayList<String> lines = new ArrayList<String>();
                                while ((line = bufferedReader.readLine()) != null) {
                                    lines.add(line);
                                }
                                Intent intentToLuyenDoc = new Intent(FileChooserActivity.this, LuyenDocActivity.class);
                                intentToLuyenDoc.putStringArrayListExtra("lines", lines);
                                startActivity(intentToLuyenDoc);
                                /*InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                                bufferedReader = new BufferedReader(inputStreamReader);
                                line = bufferedReader.readLine();
                                txtCauNoi.setText(line);
                                inputStream.close();
                                inputStreamReader.close();*/
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                break;
        }
        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case CUSTOM_DIALOG_ID:
                ListDir(curFolder);
                break;
        }
    }

    void ListDir(File f) {
        if (f.equals(root)) {
            buttonUp.setEnabled(false);
        } else {
            buttonUp.setEnabled(true);
        }

        curFolder = f;
        textFolder.setText(f.getPath());

        File[] files = f.listFiles();
        if (files != null) {
            fileList.clear();

            for (File file : files) {
                fileList.add(file.getPath());
            }

            ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, fileList);
            dialog_ListView.setAdapter(directoryList);
        } else {
            Toast.makeText(this, "Khong co file", Toast.LENGTH_SHORT).show();
        }

    }
}
