package utils;

import personnages.*;
import java.util.Random;

public class UsineEnnemi {

    private static Random random = new Random();

    // Méthode pour créer un ennemi selon le nombre d'ennemis déjà vaincus
    public static Ennemi creer(int compteurEnnemis, Personnage heros) {
        // Tous les 10 ennemis, c’est un boss
        if ((compteurEnnemis + 1) % 10 == 0) {
            return creerBoss(heros);
        } else {
            return creerEnnemiStandard();
        }
    }

    // Génère un ennemi standard aléatoire (Gobelin, Troll, Dragon)
    private static Ennemi creerEnnemiStandard() {
        int choix = random.nextInt(3); // 0, 1 ou 2
        switch (choix) {
            case 0:
                return new Gobelin(); //Ennemi(String nom, int pvInitial, int attaque, int defense, boolean estBoss)
            case 1:
                return new Troll(); //TODO Troll();
            case 2:
                return new Dragon(); //TODO Dragon();
            default:
                return new Gobelin(); // Choix par défaut
        }
    }

    // Génèrer un boss spécifique à la classe du héros
    private static Ennemi creerBoss(Personnage heros) {
        String classHeros = heros.getClass().getSimpleName();

        switch (classHeros) {
            case "Paladin":
                return new Liche();
            case "Guerrier":
                return new Tarasque(); //TODO Tarasque();
            case "Mage":
                return new Elementaire(); //TODO Elementaire();
            default:
                return new Dragon(); // Par défaut si classe inconnue
        }
    }
}
