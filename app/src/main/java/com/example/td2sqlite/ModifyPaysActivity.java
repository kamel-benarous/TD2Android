package com.example.td2sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyPaysActivity extends Activity implements View.OnClickListener {

    private EditText titleText;
    private EditText descText;
    private Button updateBtn, deleteBtn;

    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modifier Enregistrement");
        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();
        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        //getting the intent extras
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this::onClick);
        deleteBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_update){
            String name = titleText.getText().toString();
            String desc = descText.getText().toString();

            dbManager.update(_id, name, desc);
            this.returnHome();
        }
        if(v.getId() == R.id.btn_delete){
            dbManager.delete(_id);
            this.returnHome();
        }
    }

    public void returnHome(){
        Intent intent = new Intent(ModifyPaysActivity.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}