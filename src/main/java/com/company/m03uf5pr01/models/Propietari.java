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
public class Propietari {
    /** Nom d'un Propietari */
    private final String nom;
    /** Contrasenya d'un propietari */
    private String password;
    /** Diners d'un Propietari */
    private int diners = 50;
    /** Mascotes d'un Propietari */
    private List<Animal> mascotes;

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

    public Propietari(String nom, String password, int diners) {
        this.nom = nom;
        this.password = password;
        this.diners = diners;
        this.mascotes = new ArrayList<>();
    }

    public Propietari(String nom) {
        this.nom = nom;
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
    public int getDiners() {
        return diners;
    }


    /**
     * Mètode setter de l'atribut diners
     *
     * @param diners Nous diners
     */
    public void setDiners(int diners) {
        this.diners = diners;
    }

    /**
     * Mètode getter de l'atribut mascotes
     *
     * @return Mascotes del propietari
     */
    public List<Animal> getMascotes() {
        return mascotes;
    }

    public void setMascotes(List<Animal> mascotes) {
        this.mascotes = mascotes;
    }
}
