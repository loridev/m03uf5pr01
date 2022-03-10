package com.company.m03uf5pr01.models;

import com.company.utils.Colors;

import java.util.ArrayList;

/**
 * Classe que representa un Propietari al joc
 */
public class Propietari {
    /** Nom d'un Propietari */
    private final String nom;
    /** Diners d'un Propietari */
    private float diners;
    /** Mascotes d'un Propietari */
    private final ArrayList<Animal> mascotes;

    /**
     * Mètode constructor d'un Propietari
     *
     * @param nom Nom d'un Propietari
     */
    public Propietari(String nom) {
        this.nom = nom;
        this.mascotes = new ArrayList<>();
    }

    /**
     * Controla l'adopció d'un Animal a la protectora
     *
     * @param mascota Animal a adoptar
     */
    public void adoptarAnimal(Animal mascota){
        this.mascotes.add(mascota);
        mascota.setPropietari(this);
    }

    /**
     * Mètode getter de l'atribut nom
     *
     * @return Nom del propietari
     */
    public String getNom() {
        return nom;
    }

    /**
     * Mètode getter de l'atribut diners
     *
     * @return Diners del propietari
     */
    public float getDiners() {
        return diners;
    }

    /**
     * Mètode setter de l'atribut diners
     *
     * @param diners Nous diners
     */
    public void setDiners(float diners) {
        this.diners = diners;
    }

    /**
     * Mètode getter de l'atribut mascotes
     *
     * @return Mascotes del propietari
     */
    public ArrayList<Animal> getMascotes() {
        return mascotes;
    }

    /**
     * Mètode toString d'un Propietari
     *
     * @return Representació d'un Propietari
     */
    public String toString() {
        return "nom = " + Colors.BLAU + this.nom + Colors.RESET +
                ", diners = " + Colors.BLAU + this.diners  + Colors.RESET;
    }
}
