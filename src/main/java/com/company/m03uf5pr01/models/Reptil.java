package com.company.m03uf5pr01.models;

import com.company.m03uf5pr01.interfaces.Verinos;

/**
 * Classe que representa un Animal al joc
 */
public class Reptil extends Animal implements Verinos {
    /** Precissió del verí */
    private float precissioVeri;

    /**
     * Mètode constructor d'un Animal
     *
     * @param nom Nom d'un Animal
     * @param atac Atac d'un Animal
     * @param defensa Defensa d'un Animal
     * @param precisio Precissió d'un Animal
     * @param tipus Tipus d'un Animal
     * @param precissioVeri Precissió del verí
     */
    public Reptil(String nom, float atac, float defensa, float precisio, TipusAnimal tipus, float precissioVeri) {
        super(nom, atac, defensa, precisio, tipus);
        this.precissioVeri = precissioVeri;
    }

    public Reptil(String nom, int nivell, float atac, float defensa, float precisio, int vida, boolean enverinat, Propietari propietari, TipusAnimal tipus, float precissioVeri) {
        super(nom, nivell, atac, defensa, precisio, vida, enverinat, propietari, tipus);
        this.precissioVeri = precissioVeri;
    }

    public Reptil(int id) {
        super(id);
    }

    public void rugir(Animal objectiu) {
        objectiu.defensa -= this.atac / 10;
    }

    public void pujarNivell() {
        ++this.nivell;
        this.atac *= 1.15;
        this.defensa *= 1.25;
        this.precisio *= 1.05;
        this.precissioVeri *= 1.05;
    }

    public boolean mossegar(Animal objectiu) {
        boolean retorn = this.atacar(this.atac, objectiu);
        if (this.enverinar()) {
            if (!objectiu.enverinat) {
                System.out.println(objectiu.nom + " ha sigut enverinat!");
                objectiu.enverinat = true;
            }
        }

        return retorn;
    }

    public boolean enverinar() {
        int random = (int) (Math.random() * 100 + 1);
        if (this.tipus == TipusAnimal.COBRA) {
            random /= 2;
        }
        return random < this.precissioVeri;
    }

    public float getPrecissioVeri() {
        return precissioVeri;
    }

    @Override
    public String toString() {
        return super.toString() + " | Precissió verí: " + String.format("%.02f", this.precissioVeri);
    }
}
