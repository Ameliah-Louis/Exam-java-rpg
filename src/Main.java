import personnages.Guerrier;
import personnages.Gobelin;
import personnages.Personnage;
import utils.CombatManager;
import utils.GestionnairePersonnage;
import utils.UsineEnnemi;

import java.util.Scanner;

import static utils.GestionnairePersonnage.choisirClasse;

// - Affiche écran d'accueil
// - Lit le nom du joueur
// - Propose choix de classe (Paladin, Guerrier, Mage)
// - Instancie le héros choisi
// - Initialise compteur, score, fichiers
// - Boucle principale de jeu : générer ennemi, lancer combat, gérer victoires/défaites
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Afficher un message de bienvenue et demander le nom du joueur
        System.out.println("Bienvenue dans Slay the Tower !");
        System.out.print("Entrez le nom de votre héros : ");
        String nom = scanner.nextLine();

        // 2. Afficher menu pour choisir la classe et retourner une instance de Personnage correspondante
        Personnage heros = GestionnairePersonnage.choisirClasse(scanner, nom);

        // 3. Initialiser les compteurs
        int compteurEnnemis = 0;
        int compteurBoss = 0;

        // 4. Boucle principale du jeu : tant que le héros est vivant
        while (heros.estVivant()) {
            // Générer un ennemi (normal ou boss selon le compteur)
            Personnage ennemi = UsineEnnemi.creer(compteurEnnemis, heros);

            // Lancer le combat via CombatManager
            boolean victoire = CombatManager.lancerCombat(heros, ennemi);

            if (victoire) {
                compteurEnnemis++;
                if (CombatManager.estBoss(ennemi)) {
                    compteurBoss++;
                    System.out.println("Vous avez vaincu un boss !");
                }
                System.out.println("Ennemis vaincus : " + compteurEnnemis);
            } else {
                // Le héros est mort, fin de boucle
                System.out.println("Vous êtes mort...");
            }
        }

        // 5. Afficher Game Over et sauvegarder le score
        System.out.println("Game Over ! " + heros.getNom() + " a vaincu " + compteurEnnemis + " ennemis dont " + compteurBoss + " boss.");
        // Ajputer méthode pour sauvegarder le score dans un fichier
//        ScoreManager.sauvegarderScore(heros, compteurEnnemis, compteurBoss);

        scanner.close();
    }



}
