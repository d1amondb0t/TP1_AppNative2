package com.example.tp1_appnative2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView score, mot;
    private Jeu jeu;

    /**
     * Initialisation d'un nouveau jeu et des éléments qui vont être
     * manipulés sur le visuel.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.jeu = new Jeu();
        intialialize();
    }

    /**
     * Initialisation des éléments manipulables du visuel
     */
    public void intialialize() {
        img = findViewById(R.id.img);
        score = findViewById(R.id.score);
        mot = findViewById(R.id.mot);
    }

    /**
     * La logique après le click de chaque boutton du visuel.
     *
     * @param view
     */
    public void onclick(View view) {
        //Get button click Value ID
        Button btn = (Button) view;
        char lettre = btn.getText().charAt(0);
        // Transformer en charactere et le transformer en minuscule
        lettre = Character.toLowerCase(lettre);
        // motCache est la chaîne de charactère dans le visuel avec les lettres cachées
        String motCache = mot.getText().toString();
        // On récupére les indexes pour lesquels la lettre soumise est le même.
        HashMap<Integer, Integer> indexes = this.jeu.lettreDansMot(lettre);
        // On ne permet pas au client de rejouer la même lettre en disablant le button
        btn.setEnabled(false);

        System.out.println(this.jeu.getMotDevine());

        /**
         * Si le HashMap d'index est vide, ca veut dire que c'était une mauvaise devinette
         * Donc on incrementre le nombre d'essai échoué à partir de la classe jeu
         * Donc on incrementre le nombre d'essai échoué à partir de la classe jeu
         * Si le nombre d'erreur dépasse 6, on dirige l'utilisateur vers une autre page pour
         * qu'il puisse rejouer et on recommece cette activité
         * */
        if (indexes.isEmpty()) {
            this.jeu.incrementeEssai();
            int ess = this.jeu.getEssai();
            switch (ess) {
                case 1:
                    img.setImageResource(R.drawable.err01);
                    break;
                case 2:
                    img.setImageResource(R.drawable.err02);
                    break;
                case 3:
                    img.setImageResource(R.drawable.err03);
                    break;
                case 4:
                    img.setImageResource(R.drawable.err04);
                    break;
                case 5:
                    img.setImageResource(R.drawable.err05);
                    break;
                case 6:
                    img.setImageResource(R.drawable.err06);
                    Intent i = new Intent(this, Status.class);
                    Bundle b = new Bundle();
                    b.putString("STATUS", "Vous avez perdu!");
                    i.putExtras(b);
                    startActivity(i);
                    this.recreate();
//                    break;
//                default:
//                    Intent i = new Intent(this, Status.class);
//                    Bundle b = new Bundle();
//                    b.putString("STATUS", "Vous avez perdu!");
//                    i.putExtras(b);
//                    startActivity(i);
//                    this.recreate();
            }
        }
        /**
         * Si le HashMap d'index n'est pas vide, l'utilisateur a bien deviné
         * alors on ajouter la lettre sur les indexes pour lesquels le lettre bien deviné est
         * est le même et on le restant est le même que les charactères sur le visuel
         * On incrémente le pointage.
         * On change le pointage, le mot sur le visuel
         * Si l'utilsateur gagne, on lui dirige vers une autre activité qui lui indique
         * qu'il a gagné avec un boutton pour que le joueur puisse rejouer et on recommece cette activité
         * */
        else {
            String motRemplace = "";
            for (int i = 0; i < motCache.length(); i++) {
                if (indexes.containsValue(i)) {
                    this.jeu.incrementScore();
                    motRemplace += lettre;
                } else {
                    motRemplace += motCache.charAt(i);
                }
            }
            String scr = this.jeu.getScore() + "";
            score.setText(scr);
            mot.setText(motRemplace + "");

            if (this.jeu.motEqualsMotDevine(mot.getText().toString())) {
                Intent i = new Intent(this, Status.class);
                Bundle b = new Bundle();
                b.putString("STATUS", "Vous avez gagné!");
                i.putExtras(b);
                startActivity(i);
                this.recreate();
            }
        }
    }

    /***
     * Recommence le jeu
     */
    public void click_restart(View view) {
        this.recreate();
    }
}