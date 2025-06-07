package utils;

import personnages.Personnage;
import java.io.*;
import java.util.*;

// Classe utilitaire pour gérer les scores des joueurs
public class ScoreManager {

    // Chemin du fichier où les scores sont enregistrés
    private static final String FICHIER_SCORE = "scores.txt";

    // Méthode pour sauvegarder le score d’un joueur à la fin d’une partie
    public static void sauvegarderScore(Personnage heros, int nbreEnnemis, int nbreBoss) {
        // Calcul du score selon une formule simple
        // Enregistrer le nom, la classe, le score, ennemis vaincus, boss vaincus. (comment?)
        // Écrire ces infos dans le fichier
        try (FileWriter writer = new FileWriter(FICHIER_SCORE, true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            String score = heros.getNom() + " - " + heros.getClass().getSimpleName()
                    + " : " + nbreEnnemis + " ennemis vaincus, " + nbreBoss + " boss";
            bw.write(score);
            bw.newLine();

            System.out.println("Score sauvegardé !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du score : " + e.getMessage());
        }
    }

    // Méthode pour lire et afficher les scores (optionnel)
    public static void afficherScores() {
        // Lire le fichier ligne par ligne
        //&
        // Afficher les meilleurs scores

        System.out.println("===== Classement des Héros =====");

        File fichier = new File(FICHIER_SCORE);
        if (!fichier.exists()) {
            System.out.println("Aucun score enregistré pour le moment.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des scores : " + e.getMessage());
        }
    }
}
