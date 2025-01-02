package com.example.gratefulmind;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        MediaPlayer startSound = MediaPlayer.create(this, R.raw.app_start_sound);
        startSound.start();

        // Inicialización de la animación Lottie
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setAnimation(R.raw.gratitude_animation);
        lottieAnimationView.playAnimation();

        // Implementación del botón que nos lleve a la pantalla de ayuda
        Button helpButton = findViewById(R.id.helpbutton);
        helpButton.setOnClickListener(v -> {
            Intent next = new Intent(MainActivity.this, HelpActivity1.class);
            startActivity(next);
        });

        // Implementación del botón que nos lleve a escribir el agradecimiento
        Button gratitudeButton = findViewById(R.id.gratitudebutton);
        gratitudeButton.setOnClickListener(v -> {
            Intent next = new Intent(MainActivity.this, GratitudeActivity.class);
            startActivity(next);
        });

        // Implementación del botón que nos lleve a ver todos nuestros agradecimientos
        Button thanksButton = findViewById(R.id.thanksbutton);
        thanksButton.setOnClickListener(v -> {
            Intent next = new Intent(MainActivity.this, ThanksActivity.class);
            startActivity(next);
        });

        // Configuración para ajustar las vistas al sistema de bordes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
