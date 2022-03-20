package com.company.m03uf5pr01.models;

import com.company.m03uf5pr01.interfaces.JSONable;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa un Propietari al joc
 */
public class Propietari implements JSONable {
    /** Nom d'un Propietari */
    private String nom;
    /** Contrasenya d'un propietari */
    private final String password;
    /** Diners d'un Propietari */
    private float diners;
    /** Mascotes d'un Propietari */
    private final ArrayList<Animal> mascotes;

    /**
     * Mètode constructor d'un Propietari
     *
     * @param nom Nom d'un Propietari
     */
    public Propietari(String nom, String password) {
        this.nom = nom;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    @Override
    public String toJSON() {
        return new Gson().toJson(this);
    }

    public static ArrayList<Propietari> fromJSON(Reader json) {
        return new Gson().fromJson(json, new TypeToken<ArrayList<Propietari>>() {}.getType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Propietari) {
            return ((Propietari) obj).nom.equals(this.nom);
        } else {
            return false;
        }
    }
}
