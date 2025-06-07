package utils;

import java.util.Scanner;

import personnages.*;

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
        // Recharge des potions au début du combat
        if (heros instanceof Guerrier) {
            ((Guerrier) heros).rechargerPotions();
        } else if (heros instanceof Mage) {
            ((Mage) heros).rechargerPotions();
        } else if (heros instanceof Paladin) {
            ((Paladin) heros).rechargerPotions();
        }
        // Mise à jour des effets spéciaux du héros à chaque tour (bouclier, cooldown, mana)
        if (heros instanceof Mage) {
            ((Mage) heros).updatePouvoirs();
        } else if (heros instanceof Paladin) {
            ((Paladin) heros).updatePouvoirs(); // À faire aussi pour lui si pouvoir bouclier à voir
        }

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

    private static void gererUtilisationPotion(Personnage heros) {
        System.out.println("Quel type de potion souhaitez-vous utiliser ?");
        System.out.println("1 - Potion de soin");
        System.out.println("2 - Potion de mana");

        int choixPotion;
        try {
            choixPotion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Choix invalide.");
            return;
        }

        switch (choixPotion) {
            case 1: // Potion de soin
                if (heros instanceof Guerrier) {
                    Guerrier g = (Guerrier) heros;
                    if (g.getPotionsSoin() > 0) {
                        g.utiliserPotionSoin();
                    } else {
                        System.out.println("Vous n'avez plus de potion de soin !");
                    }
                } else if (heros instanceof Mage) {
                    Mage m = (Mage) heros;
                    if (m.getPotionsSoin() > 0) {
                        m.utiliserPotionSoin();
                    } else {
                        System.out.println("Vous n'avez plus de potion de soin !");
                    }
                } else if (heros instanceof Paladin) {
                    Paladin p = (Paladin) heros;
                    if (p.getPotionsSoin() > 0) {
                        p.utiliserPotionSoin();
                    } else {
                        System.out.println("Vous n'avez plus de potion de soin !");
                    }
                }
                break;

            case 2: // Potion de mana
                if (heros instanceof Mage) {
                    Mage m = (Mage) heros;
                    if (m.getPotionsMana() > 0) {
                        m.utiliserPotionMana();
                    } else {
                        System.out.println("Vous n'avez plus de potion de mana !");
                    }
                } else if (heros instanceof Paladin) {
                    Paladin p = (Paladin) heros;
                    if (p.getPotionsMana() > 0) {
                        p.utiliserPotionMana();
                    } else {
                        System.out.println("Vous n'avez plus de potion de mana !");
                    }
                } else {
                    System.out.println("Cette classe ne peut pas utiliser de mana.");
                }
                break;

            default:
                System.out.println("Choix de potion invalide.");
        }
    }

}
