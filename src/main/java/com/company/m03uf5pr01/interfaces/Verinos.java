package com.company.m03uf5pr01.interfaces;

import com.company.m03uf5pr01.models.Animal;

/**
 * Interfície que implementa les funcions d'un animal Verinos
 */
public interface Verinos {
    /**
     * Mètode que controla si s'enverina a un Animal
     *
     * @return Si s'ha enverinat o no
     */
    boolean enverinar();

    /**
     * Funció que controla quan es fa una mossegada
     *
     * @param objectiu Animal al que se li fa la mossegada
     */
    boolean mossegar(Animal objectiu);
}
