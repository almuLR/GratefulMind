package com.example.gratefulmind;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GratitudeDetailActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude_detail);

        ImageButton backbutton = findViewById(R.id.volverbutton3);
        backbutton.setOnClickListener(v ->{
            Intent next = new Intent(GratitudeDetailActivity.this, MainActivity.class);
            startActivity(next);
        });

        // Inicializar la base de datos
        dbHelper = new DataBaseHelper(this);

        // Referencias a las vistas
        TextView dateText = findViewById(R.id.textViewDate);
        TextView gratitude1Text = findViewById(R.id.textViewGratitude1);
        TextView gratitude2Text = findViewById(R.id.textViewGratitude2);
        TextView gratitude3Text = findViewById(R.id.textViewGratitude3);
        TextView lessonText = findViewById(R.id.textViewLesson);
        TextView feelingText = findViewById(R.id.textViewFeeling);
        TextView reasonText = findViewById(R.id.textViewReason);

        // Obtener la fecha seleccionada
        String selectedDate = getIntent().getStringExtra("selectedDate");
        if (selectedDate != null) {
            // Mostrar la fecha en el campo correspondiente
            dateText.setText(selectedDate);

            // Recuperar detalles del agradecimiento
            Cursor cursor = dbHelper.getGratitudeByDate(selectedDate);
            if (cursor != null && cursor.moveToFirst()) {
                gratitude1Text.setText(cursor.getString(cursor.getColumnIndexOrThrow("gratitude1")));
                gratitude2Text.setText(cursor.getString(cursor.getColumnIndexOrThrow("gratitude2")));
                gratitude3Text.setText(cursor.getString(cursor.getColumnIndexOrThrow("gratitude3")));
                lessonText.setText(cursor.getString(cursor.getColumnIndexOrThrow("lesson")));
                feelingText.setText(cursor.getString(cursor.getColumnIndexOrThrow("feeling")));
                reasonText.setText(cursor.getString(cursor.getColumnIndexOrThrow("reason")));
            } else {
                Toast.makeText(this, "No se encontraron detalles para esta fecha.", Toast.LENGTH_SHORT).show();
            }
            if (cursor != null) cursor.close();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.toolbar4), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
    }


}
