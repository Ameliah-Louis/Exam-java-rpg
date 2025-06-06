package utils;

import java.util.Scanner;

import personnages.Ennemi;
import personnages.Personnage;

public class CombatManager {
    private static Scanner scanner = new Scanner(System.in);

    // Méthode pour vérfier si l'ennemi est un boss ou non
    public static boolean estBoss(Personnage ennemi) {
        if (ennemi instanceof Ennemi) {
            return ((Ennemi) ennemi).estBoss();
        }
        return false;
    }

    // Méthode pour lancer un combat tour par tour entre un héros et un ennemi
    public static boolean lancerCombat(Personnage heros, Personnage ennemi) {
        System.out.println("Début du combat entre " + heros.getNom() + " et " + ennemi.getNom() + " !");

        // 1. Situation initiales
        afficherStats(heros, ennemi);

        // Boucle tant que les deux sont vivants
        while (heros.estVivant() && ennemi.estVivant()) {
            // 2. Afficher HUD (PV, mana, potions, cooldowns...)
            afficherHUD(heros, ennemi);

            // 3. Demander action joueur
            int choix = demanderActionJoueur(heros);

            // 4. Exécuter action
            switch (choix) {
                case 1:
                    // Attaque classique
                    System.out.println(heros.getNom() + " attaque !");
                    heros.attaquer(ennemi);
                    break;
                case 2:
                    // Pouvoir spécial
                    System.out.println(heros.getNom() + " utilise son pouvoir spécial !");
                    heros.utiliserPouvoir(ennemi);
                    break;
                case 3:
                    // Potion
                    gererUtilisationPotion(heros);
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    continue; // Reboucle directement sans passer au tour ennemi
            }

            // 5. Vérifier si ennemi est mort
            if (!ennemi.estVivant()) {
                System.out.println(ennemi.getNom() + " est vaincu !");
                return true; // victoire
            }

            // 6. Tour ennemi si vivant
            System.out.println(ennemi.getNom() + " attaque !");
            ennemi.attaquer(heros);

            // 7. Vérifier si héros est mort
            if (!heros.estVivant()) {
                System.out.println(heros.getNom() + " est mort !");
                return false; // défaite
            }
        }
        return false;
    }

    // Affiche les PV/Mana initiaux
    private static void afficherStats(Personnage heros, Personnage ennemi) {
        System.out.println(heros.getNom() + " PV: " + heros.getPv() + " Mana: " + heros.getMana());
        System.out.println(ennemi.getNom() + " PV: " + ennemi.getPv());
    }

    // Affiche HUD détaillé à chaque tour (à compléter au besoin)
    private static void afficherHUD(Personnage heros, Personnage ennemi) {
        System.out.println("-------------------------");

        // On test si la classe possède du mana ou pas. Si elle n'en a pas getMana() est à -1
        int mana = heros.getMana();
        if (mana >= 0) {
            System.out.println(heros.getNom() + " - PV: " + heros.getPv() + " Mana: " + heros.getMana());
        } else {
            System.out.println(heros.getNom() + " - PV: " + heros.getPv());
        }
        System.out.println(heros.getNom() + " - PV: " + heros.getPv() + " Mana: " + heros.getMana());
        System.out.println(ennemi.getNom() + " - PV: " + ennemi.getPv());
        // TODO: Afficher potions, cooldowns, etc.
        System.out.println("-------------------------");
    }

    // Demande au joueur de choisir une action (attaque, pouvoir, potion)
    private static int demanderActionJoueur(Personnage heros) {
        System.out.println("Choisissez une action :");
        System.out.println("1 - Attaquer");
        System.out.println("2 - Utiliser pouvoir spécial");
        System.out.println("3 - Utiliser potion");
        System.out.print("Votre choix : ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // invalide
        }
    }

    // Gère l'utilisation d'une potion selon les disponibilités du héros
    private static void gererUtilisationPotion(Personnage heros) {
        // TODO: demander type potion (soin ou mana selon classe)
        // TODO: vérifier si potion dispo, appliquer effet et décrémenter stock
        System.out.println("Fonction de gestion de potions à implémenter.");
    }
}
