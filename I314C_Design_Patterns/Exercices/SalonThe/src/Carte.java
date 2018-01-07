import java.util.HashMap;
import java.util.Map;

//Flyweight Factory
class Carte {

    private Map<String, Parfum> parfums = new HashMap<>(); //Un Parfum = un Flyweight
    //Un UnsharedFlyweight serait un parfum / une table non connus de nos systèmes

    void ajouterParfum(Parfum parfum) {
        parfums.put(parfum.getNom(), parfum);
    }

    void supprimerParfum(Parfum parfum) {
        parfums.remove(parfum.getNom());
    }

    Parfum getParfum(String parfum) {
        return parfums.get(parfum);
    }
}
