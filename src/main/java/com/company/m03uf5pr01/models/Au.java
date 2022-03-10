package com.company.m03uf5pr01.models;

import com.company.m03uf5pr01.interfaces.Aeri;

/**
 * Classe que representa una Au al joc
 */
public class Au extends Animal implements Aeri {
    /** Si té o no les urpes trencades */
    private boolean urpesTrencades;
    /** Ratio de repetició d'atac d'una Au */
    private float ratioRepeticioAtac;

    /**
     * Mètode constructor d'una Au
     *
     * @param nom Nom d'un Animal
     * @param atac Atac d'un Animal
     * @param defensa Defensa d'un Animal
     * @param precisio Precissió d'un Animal
     * @param tipus Tipus d'un Animal
     * @param ratioRepeticioAtac Ratio de repetició d'atac d'una Au
     */
    public Au(String nom, float atac, float defensa, float precisio, TipusAnimal tipus, float ratioRepeticioAtac) {
        super(nom, atac, defensa, precisio, tipus);
        this.ratioRepeticioAtac = ratioRepeticioAtac;
    }

    public void rugir(Animal objectiu) {
        objectiu.defensa -= this.atac / 6;
    }

    public void pujarNivell() {
        ++this.nivell;
        this.atac *= 1.10;
        this.defensa *= 1.15;
        this.precisio *= 1.10;
        this.ratioRepeticioAtac *= 1.05;
    }

    public void picotada(Animal objectiu) {
        this.atacar(this.atac, objectiu);

        if (this.repeticio()) {
            this.picotada(objectiu);
        }

    }

    public void esgarrapada(Animal objectiu) {
        if (!this.urpesTrencades) {
            this.atacar(this.atac, objectiu);
            if (this.tipus != TipusAnimal.ALIGA) {
                int random = (int) (Math.random() * 100 + 1);
                if (random <= 10) {
                    System.out.println("S'ha trencat les urpes! No podrà fer més esgarrapades");
                    this.urpesTrencades = true;
                    this.atac /= 2;
                }
            }
        } else {
            System.out.println("Ha intentat fer una esgarrapada però té les ungles trencades!");
        }
    }

    public boolean repeticio() {
        int random = (int) (Math.random() * 100 + 1);

        if (this.tipus == TipusAnimal.COLIBRI) {
            random /= 1.2f;
        }

        return random < this.ratioRepeticioAtac;
    }
}
