package com.company.m03uf5pr01.interfaces;

import com.company.m03uf5pr01.models.Animal;

/**
 * Interfície que implementa les funcions d'un animal Aeri
 */
public interface Aeri {
    /**
     * Funció que controla quan es fa una picotada
     *
     * @param objectiu Animal al que se li fa la picotada
     */
    void picotada(Animal objectiu);

    /**
     * Funció que controla quan es fa una esgarrapada
     *
     * @param objectiu Animal al que se li fa la esgarrapada
     */
    void esgarrapada(Animal objectiu);

    /**
     * Funció que controla quan es repeteix un atac d'un Animal volador
     *
     * @return Si es repeteix l'atac o no
     */
    boolean repeticio();
}
