package com.example.gratefulmind;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ThanksActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thanks);

        ImageButton backbutton = findViewById(R.id.volverbutton);
        backbutton.setOnClickListener(v ->{
            Intent next = new Intent(ThanksActivity.this, MainActivity.class);
            startActivity(next);
        });

        dbHelper = new DataBaseHelper(this);

        ListView listView = findViewById(R.id.lv_thanks);

        ArrayList<String> dates = new ArrayList<>();
        Cursor cursor = dbHelper.getGratitudesByDate();

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                dates.add(date);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No hay agradecimientos guardados.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dates);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedDate = dates.get(position);
                Cursor cursor = dbHelper.getGratitudeByDate(selectedDate);
                if (cursor != null && cursor.moveToFirst()) {
                    Intent intent = new Intent(ThanksActivity.this, GratitudeDetailActivity.class);
                    intent.putExtra("selectedDate", selectedDate);
                    startActivity(intent);
                    cursor.close();
                } else {
                    Toast.makeText(ThanksActivity.this, "No se encontraron detalles para esta fecha.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}