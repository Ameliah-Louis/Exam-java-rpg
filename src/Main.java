import personnages.Guerrier;
import personnages.Gobelin;

// - Affiche écran d'accueil
// - Lit le nom du joueur
// - Propose choix de classe (Paladin, Guerrier, Mage)
// - Instancie le héros choisi
// - Initialise compteur, score, fichiers
// - Boucle principale de jeu : générer ennemi, lancer combat, gérer victoires/défaites
public class Main {
    public static void main(String[] args) {
        // 1. Afficher bienvenue et lire nom
        // 2. Afficher menu de choix de classe -> retourner instance de Personnage
        // 3. Initialiser compteurEnnemis = 0, compteurBoss = 0
        // 4. Tant que héros.estVivant():
        //      ennemi = UsineEnnemi.creer(compteurEnnemis)
        //      boolean victoire = CombatManager.lancerCombat(heros, ennemi)
        //      si victoire: compteurEnnemis++; si boss: compteurBoss++
        // 5. Afficher Game Over et score
        // 6. Sauvegarder score dans scores.txt
            Guerrier heros = new Guerrier("Thorgal");
            Gobelin gob = new Gobelin();

            System.out.println("Combat entre " + heros.getNom() + " et " + gob.getNom());

            gob.attaquer(heros);
            heros.attaquer(gob);
        }


}
