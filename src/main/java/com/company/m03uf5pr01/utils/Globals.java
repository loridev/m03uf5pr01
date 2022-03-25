package com.company.m03uf5pr01.utils;

import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Propietari;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Globals {
    private static ArrayList<Propietari> propietaris;
    private static Propietari propietariActual;
    private static LinkedList<Animal> protectora;
    private static int dia = 1;
    private static Animal animalDesafiant;
    private static Animal animalDesafiat;
    private static int dinersApostats;
    public final static String RESOURCES_PATH = "src/main/resources/com/company/m03uf5pr01/";

    public static ArrayList<Propietari> getPropietaris() {
        return propietaris;
    }

    public static void setPropietaris(ArrayList<Propietari> propietaris) {
        Globals.propietaris = propietaris;
    }

    public static Propietari getPropietariActual() {
        return propietariActual;
    }

    public static void setPropietariActual(Propietari propietariActual) {
        Globals.propietariActual = propietariActual;
    }

    public static LinkedList<Animal> getProtectora() {
        return protectora;
    }

    public static void setProtectora(LinkedList<Animal> protectora) {
        Globals.protectora = protectora;
    }

    public static int getDia() {
        return dia;
    }

    public static void setDia(int dia) {
        Globals.dia = dia;
    }

    public static Animal getAnimalDesafiant() {
        return animalDesafiant;
    }

    public static void setAnimalDesafiant(Animal animalDesafiant) {
        Globals.animalDesafiant = animalDesafiant;
    }

    public static Animal getAnimalDesafiat() {
        return animalDesafiat;
    }

    public static void setAnimalDesafiat(Animal animalDesafiat) {
        Globals.animalDesafiat = animalDesafiat;
    }

    public static int getDinersApostats() {
        return dinersApostats;
    }

    public static void setDinersApostats(int dinersApostats) {
        Globals.dinersApostats = dinersApostats;
    }
}


