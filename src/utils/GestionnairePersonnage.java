package utils;

import personnages.Guerrier;
import personnages.Personnage;

import java.util.Scanner;

public class GestionnairePersonnage {

    /**
     * Affiche un menu pour que le joueur choisisse sa classe.
     * Crée et retourne une instance du personnage choisi avec le nom donné.
     */
    public static Personnage choisirClasse(Scanner scanner, String nom) {
        while (true) {
            System.out.println("Choisissez votre classe :");
            System.out.println("1 - Guerrier");
            System.out.println("2 - Mage");
            System.out.println("3 - Paladin");
            System.out.print("Entrez le numéro de votre choix : ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    return new Guerrier(nom);
                case "2":
                    return new Guerrier(nom); // a remplacer par le Mage
                case "3":
                    return new Guerrier(nom);// a remplacer par le Paladin
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
}
