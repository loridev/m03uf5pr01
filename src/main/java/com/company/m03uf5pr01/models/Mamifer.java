package com.company.m03uf5pr01.models;

import com.company.m03uf5pr01.interfaces.Terrestre;

/**
 * Classe que representa un Mamifer al joc
 */
public class Mamifer extends Animal implements Terrestre {
    /** Multiplicador de danys d'un cop de puny */
    private float multiplicadorPuny;

/**
 * Mètode constructor d'un Mamifer
 *
 * @param nom Nom d'un Animal
 * @param atac Atac d'un Animal
 * @param defensa Defensa d'un Animal
 * @param precisio Precissió d'un Animal
 * @param tipus Tipus d'un Animal
 * @param multiplicadorPuny Multiplicador de danys d'un cop de puny
 */
    public Mamifer(String nom, float atac, float defensa, float precisio, TipusAnimal tipus, float multiplicadorPuny) {
        super(nom, atac, defensa, precisio, tipus);
        this.multiplicadorPuny = multiplicadorPuny;
    }

    public Mamifer(String nom, int nivell, float atac, float defensa, float precisio, int vida, boolean enverinat, Propietari propietari, TipusAnimal tipus, float multiplicadorPuny) {
        super(nom, nivell, atac, defensa, precisio, vida, enverinat, propietari, tipus);
        this.multiplicadorPuny = multiplicadorPuny;
    }

    public void rugir(Animal objectiu) {
        int divisor = 8;

        if (this.tipus == TipusAnimal.PORC) {
            divisor = 4;
        }
        objectiu.defensa -= this.atac / divisor;
    }

    public void pujarNivell() {
        ++this.nivell;
        this.atac *= 1.05;
        this.defensa *= 1.2;
        this.precisio *= 1.10;
        this.multiplicadorPuny *= 1.05;
    }

    public void copDePuny(Animal objectiu) {
        float divisorPuny = 1;
        if (this.tipus != TipusAnimal.CANGUR) {
            System.out.println("L'animal s'ha ferit al fer un cop de puny.");
            this.vida -= 5;
        }
        if (this.tipus == TipusAnimal.RATOLI) {
            divisorPuny = 2;
        } else if (this.tipus == TipusAnimal.PORC) {
            divisorPuny = 1.5f;
        }
        this.atacar((this.atac * this.multiplicadorPuny) / divisorPuny, objectiu);
    }

    public void patada(Animal objectiu) {
        float multiplicador = 1;

        if (this.tipus == TipusAnimal.XIMPANZE) {
            multiplicador = 1.5f;
        }
        this.atacar(this.atac * multiplicador, objectiu);
    }

    public float getMultiplicadorPuny() {
        return multiplicadorPuny;
    }
}
