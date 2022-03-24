package com.company.m03uf5pr01.models;

import com.company.m03uf5pr01.interfaces.JSONable;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * Classe que representa un Animal al joc
 */
public abstract class Animal implements JSONable, Comparable {
    private static int contadorId = 0;
    protected final int id;
    /** Nom d'un Animal */
    protected String nom;
    /** Nivell d'un Animal */
    protected int nivell = 1;
    /** Atac d'un Animal */
    protected float atac;
    /** Defensa d'un Animal */
    protected float defensa;
    /** Precissió d'un Animal */
    protected float precisio;
    /** Vida d'un Animal */
    protected int vida = 100;
    /** Si està enverinat */
    protected boolean enverinat;
    /** Propietari d'un Animal */
    protected Propietari propietari;
    /** Tipus d'un Animal */
    protected TipusAnimal tipus;

    /**
     * Mètode constructor d'un Animal
     *
     * @param nom Nom d'un Animal
     * @param atac Atac d'un Animal
     * @param defensa Defensa d'un Animal
     * @param precisio Precissió d'un Animal
     * @param tipus Tipus d'un Animal
     */
    public Animal(String nom, float atac, float defensa, float precisio, TipusAnimal tipus) {
        this.id = ++contadorId;
        this.nom = nom;
        this.atac = atac;
        this.defensa = defensa;
        this.precisio = precisio;
        this.tipus = tipus;
    }

    public Animal(String nom, int nivell, float atac, float defensa, float precisio, int vida, boolean enverinat, Propietari propietari, TipusAnimal tipus) {
        this.id = ++contadorId;
        this.nom = nom;
        this.nivell = nivell;
        this.atac = atac;
        this.defensa = defensa;
        this.precisio = precisio;
        this.vida = vida;
        this.enverinat = enverinat;
        this.propietari = propietari;
        this.tipus = tipus;
    }

    public Animal(int id) {
        this.id = id;
    }

    /**
     * Funció genèrica per atacar un Animal a un altre
     *
     * @param danys Valor d'atac total del atacant amb multiplicadors o divisors
     * @param objectiu Animal defensor
     */
    protected void atacar(float danys, Animal objectiu) {
        if (this.atacExitos()) {
            if (danys > objectiu.defensa) {
                System.out.println(this.nom + " ha fet un atac exitòs a " + objectiu.nom);
                objectiu.vida -= (danys - objectiu.defensa);
            } else {
                System.out.println("L'animal " + objectiu.nom + " s'ha pogut defensar de l'atac i ha minimitzat el cop");
                objectiu.vida -= 1;
            }
        } else {
            System.out.println("L'atac ha fallat!");
        }
    }

    /**
     * Controla si un atac ha impactat o ha fallat
     *
     * @return Si ha sigut exitòs o no
     */
    protected boolean atacExitos() {
        int random = (int) (Math.random() * 100 + 1);
        return random < this.precisio;
    }

    /**
     * Controla el primer moviment del combat, amb el seu valor en atac, baixarà la defensa inicial del contrari
     *
     * @param objectiu Animal defensor
     */
    protected abstract void rugir(Animal objectiu);

    /**
     * Mètode que controla els atributs que pugen quan un Animal guanya un combat
     */
    protected abstract void pujarNivell();

    @Override
    public String toJSON() {
        return new Gson().toJson(this);
    }

    public void fromJSON(String json) {
        new Gson().fromJson(json, this.getClass());
    }

    /**
     * Mètode setter de l'atribut Propietari
     *
     * @param propietari Nou propietari
     */
    public void setPropietari(Propietari propietari) {
        this.propietari = propietari;
    }

    /**
     * Mètode getter de l'atribut nom
     *
     * @return Nom de l'Animal
     */
    public String getNom() {
        return nom;
    }

    public int getNivell() {
        return nivell;
    }

    public float getAtac() {
        return atac;
    }

    public float getDefensa() {
        return defensa;
    }

    public float getPrecisio() {
        return precisio;
    }

    public int getVida() {
        return vida;
    }

    public boolean isEnverinat() {
        return enverinat;
    }

    public Propietari getPropietari() {
        return propietari;
    }

    public TipusAnimal getTipus() {
        return tipus;
    }

    public int getId() {
        return id;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setEnverinat(boolean enverinat) {
        this.enverinat = enverinat;
    }

    public static int toStringToId(String tostring) {
        return Integer.parseInt(tostring.split("(\\ )(\\|)(\\ )")[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return ((Animal) obj).id == this.id;
        } return false;
    }

    @Override
    public int compareTo(Object o) {
        return this.id - ((Animal) o).id;
    }

    @Override
    public String toString() {
        return this.id + " | " + this.nom + " | " + this.getClass().getSimpleName() + " | " + this.getTipus() +
                " | Atac: " + String.format("%.02f", this.atac) +
                " | Defensa: " + String.format("%.02f", this.defensa) +
                " | Precisió: " + String.format("%.02f", this.precisio) +
                " | Vida: " + this.vida;
    }
}
