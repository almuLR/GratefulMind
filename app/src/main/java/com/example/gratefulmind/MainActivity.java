package com.example.gratefulmind;

import android.content.Intent;
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

        // Inicialización de la animación Lottie
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setAnimation(R.raw.gratitude_animation);
        lottieAnimationView.playAnimation();

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
