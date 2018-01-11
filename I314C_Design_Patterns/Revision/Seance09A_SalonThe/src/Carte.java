import java.util.HashMap;
import java.util.Map;

public class Carte {

    Map<String, Parfum> parfums = new HashMap<>();

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
