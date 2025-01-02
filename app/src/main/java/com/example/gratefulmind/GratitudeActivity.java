package com.example.gratefulmind;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GratitudeActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gratitude);

        dbHelper = new DataBaseHelper(this);

        // Referencias a los campos de texto
        EditText dateField = findViewById(R.id.editTextDate);
        EditText gratitudeField1 = findViewById(R.id.editTextTextMultiLine);
        EditText gratitudeField2 = findViewById(R.id.editTextTextMultiLine2);
        EditText gratitudeField3 = findViewById(R.id.editTextTextMultiLine3);
        EditText lessonField = findViewById(R.id.editTextTextMultiLine4);
        EditText reasonField = findViewById(R.id.editTextTextMultiLine5);
        RadioGroup feelingGroup = findViewById(R.id.radiogorup);

        // Botón para guardar
        ImageButton saveButton = findViewById(R.id.savebutton);
        saveButton.setOnClickListener(v -> {
            String date = dateField.getText().toString().trim();
            String gratitude1 = gratitudeField1.getText().toString().trim();
            String gratitude2 = gratitudeField2.getText().toString().trim();
            String gratitude3 = gratitudeField3.getText().toString().trim();
            String lesson = lessonField.getText().toString().trim();
            String reason = reasonField.getText().toString().trim();

            int selectedFeelingId = feelingGroup.getCheckedRadioButtonId();
            String feeling = null;

            if (selectedFeelingId != -1) {
                RadioButton selectedFeeling = findViewById(selectedFeelingId);
                feeling = selectedFeeling.getText().toString();
            }

            // Validar datos
            if (date.isEmpty() || gratitude1.isEmpty() || gratitude2.isEmpty() || gratitude3.isEmpty() || lesson.isEmpty() || feeling == null || reason.isEmpty()) {
                Toast.makeText(GratitudeActivity.this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Guardar en la base de datos
            dbHelper.addGratitude(date, gratitude1, gratitude2, gratitude3, lesson, feeling, reason);
            Toast.makeText(GratitudeActivity.this, "¡Agradecimiento registrado exitosamente!", Toast.LENGTH_SHORT).show();
            MediaPlayer saveSound = MediaPlayer.create(GratitudeActivity.this, R.raw.gratitude_saved);
            saveSound.start();

            // Limpiar los campos
            dateField.setText("");
            gratitudeField1.setText("");
            gratitudeField2.setText("");
            gratitudeField3.setText("");
            lessonField.setText("");
            reasonField.setText("");
            feelingGroup.clearCheck();

            // Volver al MainActivity
            Intent next = new Intent(GratitudeActivity.this, MainActivity.class);
            startActivity(next);
        });

        // Botón para volver
        ImageButton backButton = findViewById(R.id.volverbutton2);
        backButton.setOnClickListener(v -> {
            Intent next = new Intent(GratitudeActivity.this, MainActivity.class);
            startActivity(next);
        });

        // Ajustar los márgenes para la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
