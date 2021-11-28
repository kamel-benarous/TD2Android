package com.example.td2sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;

    final String[] from = new String[] {
            DataBaseHelper._ID,
            DataBaseHelper.SUBJECT,
            DataBaseHelper.DESC
    };

    final int [] to = new int[] {
        R.id.id,
        R.id.title,
        R.id.desc
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.enregistrement,
                cursor,
                from,
                to,
                0);
        simpleCursorAdapter.notifyDataSetChanged();

        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ModifyPaysActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_record){
            Intent intent = new Intent(getApplicationContext(), AddPaysActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}