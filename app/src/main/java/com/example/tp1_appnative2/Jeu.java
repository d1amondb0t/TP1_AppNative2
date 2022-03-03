package com.example.tp1_appnative2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Jeu {

    private int score = 0;
    private int essai = 0;
    private HashMap<Integer, String> mots = new HashMap<>();
    private String motDevine;
    private int maxNombre = 11;

    /**
     * Initialisation du constructeur.
     * Choisi un mot a devine par hasard avec le Random.
     * On recupere le mot avec la cle choisi par hasard dans le
     * HashMap.
     */
    public Jeu() {
        init();
        Random rand = new Random();
        int num = rand.nextInt(maxNombre);
        this.motDevine = this.mots.get(num);
    }

    /**
     * Tous les differents mots possibles et les
     * ajouter dans le HashMap avec une cle differentes
     */
    public void init() {
        this.mots.put(0, "abdomen");
        this.mots.put(1, "aborder");
        this.mots.put(2, "bahamas");
        this.mots.put(3, "cabinet");
        this.mots.put(4, "editeur");
        this.mots.put(5, "facteur");
        this.mots.put(6, "induire");
        this.mots.put(7, "lafleur");
        this.mots.put(8, "magenta");
        this.mots.put(9, "ouvrant");
        this.mots.put(10, "pommery");
        this.mots.put(11, "rhombus");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEssai() {
        return essai;
    }

    public void setEssai(int essai) {
        this.essai = essai;
    }

    public String getMotDevine() {
        return motDevine;
    }

    public void setMotDevine(String motDevine) {
        this.motDevine = motDevine;
    }

    public HashMap<Integer, Integer> lettreDansMot(char lettre) {
        HashMap<Integer, Integer> indexes = new HashMap<>();
        for (int i = 0; i < this.motDevine.toCharArray().length; i++) {
            if (lettre == this.motDevine.charAt(i)) {
                indexes.put(i, i);
            }
        }
        return indexes;
    }

    public boolean motEqualsMotDevine(String mot) {
        System.out.println(mot);
        System.out.println(this.getMotDevine());
        if (this.motDevine.equals(mot)) {
            return true;
        }
        return false;
    }

    public void incrementeEssai() {
        this.essai += 1;
    }

    public void incrementScore() {
        this.score += 1;
    }
}
