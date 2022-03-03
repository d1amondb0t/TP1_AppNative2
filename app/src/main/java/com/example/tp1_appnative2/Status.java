package com.example.tp1_appnative2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Status extends AppCompatActivity {

    TextView status;

    /**
     * On récupéle message de victoire ou de défaite et on l'affiche par le bundle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        status = findViewById(R.id.message);

        Bundle b = getIntent().getExtras();
        status.setText(b.getString("STATUS"));
    }

    /**
     * On fini l'activité et on recommence le jeu
     *
     */
    public void click_rejouer(View view) {
        finish();
    }
}