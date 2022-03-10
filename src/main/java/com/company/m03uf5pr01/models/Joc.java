package com.company.m03uf5pr01.models;

import com.company.utils.Colors;
import com.company.utils.ControladorsErrors;
import com.company.utils.Eines;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe estàtica que controla la lògica del Joc
 */
public class Joc {
    /** Protectora que guardarà tots els animals disponibles per adoptar*/
    private static final ArrayList<Animal> PROTECTORA = new ArrayList<>();
    /** Propietaris registrats al joc */
    private static final ArrayList<Propietari> PROPIETARIS = new ArrayList<>();
    /** Per llegir inputs de teclat */
    private static final Scanner LLEGIR = new Scanner(System.in);
    /** Pool de noms */
    private static final String[] POOL_NOMS = {"Drako", "Lucy", "Lua", "Firulais", "Calcetines", "Zarpas", "Rocky",
            "Bruc", "Milhouse", "Espetec", "Fuet", "Macarrones", "Llonganissa", "Elisa", "Sara", "Raimon", "Piruleta",
            "Gala", "Lennon", "Kinder", "Odin", "Israel", "Luke", "Leia", "Dali", "Niebla", "Casper", "Jagger",
            "Maya", "Tequila", "Joy", "Tor", "Tanka", "Rex", "Timon", "Pumba", "Potaje", "Cocido", "Croquetas",
            "Bimbo", "Colacao", "Nesquik", "Margarida", "Petúnia", "Coral", "Camila", "Eustàquia", "Coixí"};

    /**
     * Mètode que contè tot el codi i crides de funcions en temps d'execució
     */
    public static void init() {
        int dia = 1;
        int opcioMenu;
        String menuPrincipal = """
                    Que vols fer?
                        1. Afegir Propietari
                        2. Adoptar Animal
                        3. Veure diners
                        4. Afegir diners
                        5. Iniciar baralla
                        6. Dia següent
                        7. Sortir
                        Opció:\040""";


        System.out.println(Colors.VERD + "Benvingut/da a Money rules Animals!" + Colors.RESET);
        System.out.println("Recomanem ampliar la finestra de la consola per a una experiència millor.");
        System.out.println("Si no hi han animals disponibles, passa de dia i apareixeran de nous.");
        System.out.println("* Recorda que això es un joc i en cap cas s'ha de portar a la realitat.");
        afegirAnimals();
        do {
            System.out.println("DIA " + dia);
            opcioMenu = ControladorsErrors.llegirEnterMissatge(menuPrincipal, 1, 7);
            switch (opcioMenu) {
                case 1 -> {
                    String nomIntroduit;
                    System.out.print("Introdueix un nom per al Propietari: ");
                    nomIntroduit = LLEGIR.nextLine();

                    if (nomIntroduit.length() > 1 && nomIntroduit.matches("^[A-Za-z]+$")) {
                        PROPIETARIS.add(new Propietari(nomIntroduit));
                        System.out.println("S'ha afegit el propietari amb nom: " +
                                PROPIETARIS.get(PROPIETARIS.size() - 1).getNom());
                    } else {
                        System.out.println("Nom no vàlid");
                    }

                }
                case 2 -> {
                    Propietari propietariEscollit = escollirPropietari();
                    Animal animalEscollit;
                    if (propietariEscollit != null) {
                        animalEscollit = escollirAnimal();
                        if (animalEscollit != null) {
                            propietariEscollit.adoptarAnimal(animalEscollit);
                            System.out.println("El propietari " + propietariEscollit.getNom() + " ha adoptat l'animal "
                                    + animalEscollit.getNom());
                            PROTECTORA.remove(animalEscollit);
                        }
                    }
                }
                case 3 -> {
                    Propietari propietariEscollit = escollirPropietari();
                    if (propietariEscollit != null) {
                        System.out.println("El " + propietariEscollit.getNom() + " té " +
                                String.format("%.2f", propietariEscollit.getDiners()) + "€");
                    }
                }
                case 4 -> {
                    Propietari propietariEscollit = escollirPropietari();
                    if (propietariEscollit != null) {
                        int dinersAfegits = ControladorsErrors.llegirEnterMissatge("Quants diners vols " +
                                        "afegir? (Màxim 5000€): ",
                                0, 5000);
                        propietariEscollit.setDiners(propietariEscollit.getDiners() + dinersAfegits);
                    }
                }
                case 5 -> prepararBaralla();
                case 6 -> {
                    dia++;
                    afegirAnimals();
                }
                case 7 -> System.out.println("Sortiràs del joc. Fins aviat!");
            }
        } while (opcioMenu != 7);
    }

    /**
     * Controla la lògica per escollir un propietari, sempre i quan hi hagi disponibles
     *
     * @return Propietari escollit o null si no es pot escollir
     */
    private static Propietari escollirPropietari() {
        int propietariEscollit;

        if (PROPIETARIS.size() != 0) {
            System.out.println("Selecciona un propietari:");
            Eines.imprirArrayList(PROPIETARIS);
            propietariEscollit = (ControladorsErrors.llegirEnter(1, PROPIETARIS.size())) - 1;
            return PROPIETARIS.get(propietariEscollit);
        } else {
            System.out.println(Colors.VERMELL + "No hi ha propietaris!" + Colors.RESET);
            return null;
        }
    }

    /**
     * Controla la lògica per escollir un animal, sempre i quan hi hagi disponibles
     *
     * @return Animal escollit o null si no es pot escollir
     */
    private static Animal escollirAnimal() {
        int animalEscollit;

        if (PROTECTORA.size() != 0) {
            System.out.println("Selecciona un animal:");
            Eines.imprirArrayList(PROTECTORA);
            animalEscollit = (ControladorsErrors.llegirEnter(1, PROTECTORA.size())) - 1;
            return PROTECTORA.get(animalEscollit);
        } else {
            System.out.println(Colors.VERMELL + "No hi ha animals!" + Colors.RESET);
            return null;
        }
    }

    /**
     * Controla la lògica per escollir un animal d'un propietari, sempre i quan hi hagi disponibles
     *
     * @param propietari Propietari del qual es seleccionarà la mascota
     * @return Mascota escollida o null si no té mascotes o no es vol fer la baralla
     */
    private static Animal escollirMascota(Propietari propietari) {
        int animalEscollit;
        if (propietari.getMascotes().size() != 0) {
            System.out.println("Escull una mascota (0 si no es vol combatir):");
            Eines.imprirArrayList(propietari.getMascotes());
            animalEscollit = (ControladorsErrors.llegirEnter(0, propietari.getMascotes().size())) - 1;
            if (animalEscollit == -1) {
                return null;
            }
            return propietari.getMascotes().get(animalEscollit);
        } else {
            System.out.println(Colors.VERMELL + "El propietari no té mascotes!" + Colors.RESET);
            return null;
        }
    }

    /**
     * Tota la lògica per a la preparació d'un combat (abans que inicii, escollir mascotes, etc.)
     */
    private static void prepararBaralla() {
        if (PROPIETARIS.size() > 1) {
            float dinersApostats;
            System.out.println("Propietari desafiant: ");
            Propietari desafiant = escollirPropietari();
            Propietari desafiat;
            Animal mascotaDesafiat;
            if (desafiant != null) {
                dinersApostats = ControladorsErrors.llegirFloatMissatge("Quants diners vols apostar? ", 0,
                        desafiant.getDiners());
                Animal mascotaDesafiant = escollirMascota(desafiant);
                if (mascotaDesafiant != null) {
                    do {
                        System.out.println("Propietari desafiat: ");
                        desafiat = escollirPropietari();
                        if (desafiant == desafiat) {
                            System.out.println(Colors.VERMELL + "Has d'escollir un propietari diferent!" + Colors.RESET);
                        }
                    } while (desafiant == desafiat);
                    if (desafiat != null) {
                        if (desafiat.getDiners() >= dinersApostats) {
                            mascotaDesafiat = escollirMascota(desafiat);
                            if (mascotaDesafiat != null) {
                                System.out.println("El combat iniciarà entre " + mascotaDesafiant.getNom() +
                                        " i " + mascotaDesafiat.getNom());
                                baralla(mascotaDesafiant, mascotaDesafiat, dinersApostats);
                            }
                        } else {
                            System.out.println(Colors.VERMELL + "El desafiat no té tants diners per apostar!" + Colors.RESET);
                        }
                    }

                }
            }
        } else {
            System.out.println(Colors.VERMELL + "No es pot barallar si no hi ha més d'un propietari!" + Colors.RESET);
        }
    }

    /**
     * Controla tota la lògica d'una baralla, fins que un Animal se li acaba la vida
     *
     * @param desafiant Animal del propietari desafiant
     * @param desafiat Animal del propietari desafiat
     * @param diners Diners apostats
     */
    private static void baralla(Animal desafiant, Animal desafiat, float diners) {
        String missatge;
        Animal torn = desafiat;
        int atacEscollit;

        desafiant.rugir(desafiat);
        desafiat.rugir(desafiant);

        do {
            if (torn == desafiat) {
                torn = desafiant;
            } else {
                torn = desafiat;
            }

            System.out.println(Colors.BLAU + "Vida de " + desafiant.nom + ": " + desafiant.vida);
            System.out.println("Vida de " + desafiat.nom + ": " + desafiat.vida + Colors.RESET);

            System.out.println("Torn de " + torn.nom);

            if (torn instanceof Mamifer) {
                missatge = """
                            Que vols fer?
                            1. Cop de puny
                            2. Patada
                            Opció:\040""";
                atacEscollit = ControladorsErrors.llegirEnterMissatge(missatge, 1, 2);

                if (atacEscollit == 1) {
                    if (torn == desafiant) {
                        ((Mamifer) torn).copDePuny(desafiat);
                    } else {
                        ((Mamifer) torn).copDePuny(desafiant);
                    }
                    if (torn.vida <= 0) {
                        break;
                    }
                } else {
                    if (torn == desafiant) {
                        ((Mamifer) torn).patada(desafiat);
                    } else {
                        ((Mamifer) torn).patada(desafiant);
                    }
                }
            } else if (torn instanceof Au) {
                missatge = """
                            Que vols fer?
                            1. Picotada
                            2. Esgarrapada
                            Opció:\040""";
                atacEscollit = ControladorsErrors.llegirEnterMissatge(missatge, 1, 2);

                if (atacEscollit == 1) {
                    if (torn == desafiant) {
                        ((Au) torn).picotada(desafiat);
                    } else {
                        ((Au) torn).picotada(desafiant);
                    }
                } else {
                    if (torn == desafiant) {
                        ((Au) torn).esgarrapada(desafiat);
                    } else {
                        ((Au) torn).esgarrapada(desafiant);
                    }
                }

            } else {
                System.out.println(torn.nom + " és un reptil, per tant mossegarà.");

                if (torn == desafiant) {
                    ((Reptil) torn).mossegar(desafiat);
                } else {
                    ((Reptil) torn).mossegar(desafiant);
                }

                System.out.println("(INTRO PER CONTINUAR)");
                LLEGIR.nextLine();
            }
            if (torn.enverinat) {
                torn.vida -= 5;
                System.out.println(torn.nom + " ha perdut vida a causa del verí!");
            }
        } while (desafiant.vida > 0 && desafiat.vida > 0);

        terminarBaralla(desafiant, desafiat, diners);

    }

    /**
     * Controla quan un dels Animals es queda sense vida i li dóna diners i puja el nivell del propietari i animal
     * guanyadors, respectivament.
     *
     * @param desafiant Animal del propietari desafiant
     * @param desafiat Animal del propietari desafiat
     * @param diners Diners apostats
     */
    private static void terminarBaralla(Animal desafiant, Animal desafiat, float diners) {
        Animal guanyador;
        Animal perdedor;
        if (desafiant.vida <= 0) {
            guanyador = desafiat;
            perdedor = desafiant;

        } else {
            guanyador = desafiant;
            perdedor = desafiat;
        }

        System.out.println(Colors.VERD + guanyador.nom + " ha guanyat i " + guanyador.propietari.getNom() +
                " ha rebut " + diners + "€" + Colors.RESET);
        guanyador.pujarNivell();
        guanyador.propietari.setDiners(guanyador.propietari.getDiners() + diners);
        perdedor.propietari.setDiners(perdedor.propietari.getDiners() - diners);
        guanyador.vida = 100;
        System.out.println("La mascota " + Colors.VERMELL + perdedor.nom + Colors.RESET + " ha mort en combat, " +
                "i ara descansarà amb " + Colors.BLAU + "Jesús" + Colors.RESET);
        perdedor.propietari.getMascotes().remove(perdedor);
    }

    /**
     * Afegeix un nombre aleatori d'animals a l'inici d'un dia
     */
    private static void afegirAnimals() {
        int numAnimalsAfegits = (int) (Math.random() * 5);
        System.out.println("S'ha(n) afegit " + numAnimalsAfegits + " animal(s) més per adoptar, hi ha un total de " +
                (PROTECTORA.size() + numAnimalsAfegits) + " a la protectora!");

        for (int i = 0; i < numAnimalsAfegits; i++) {
            Animal animalAfegir;
            int tipusAnimalRandom = (int) (Math.random() * TipusAnimal.values().length);
            int nomRandom = (int) (Math.random() * POOL_NOMS.length);
            float atac = (float) (Math.random() * 60 + 1);
            float defensa = (float) (Math.random() * 25 + 1);
            float precissio = (float) (Math.random() * 75 + 1);

            if (tipusAnimalRandom < 4) {
                float multiplicadorPuny = (float) (Math.random() * 2 + 1);
                animalAfegir = new Mamifer(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], multiplicadorPuny);
            } else if (tipusAnimalRandom > 7) {
                float precissioVeri = (float) (Math.random() * 60 + 1);
                animalAfegir = new Reptil(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], precissioVeri);
            } else {
                float ratioRepeticioAtac = (float) (Math.random() * 35 + 1);
                animalAfegir = new Au(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], ratioRepeticioAtac);
            }
            PROTECTORA.add(animalAfegir);

        }
    }

}
