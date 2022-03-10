package com.company.m03uf5pr01.interfaces;

import com.company.m03uf5pr01.models.Animal;

/**
 * Interfície que implementa les funcions d'un animal Terrestre
 */
public interface Terrestre {
    /**
     * Funció que controla quan es fa un cop de puny
     *
     * @param objectiu Animal al que se li fa el cop de puny
     */
    void copDePuny(Animal objectiu);

    /**
     * Funció que controla quan es fa una patada
     *
     * @param objectiu Animal al que se li fa la patada
     */
    void patada(Animal objectiu);
}
