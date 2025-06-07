package personnages;

import java.util.Random;

public class Tarasque extends Ennemi {
    private boolean armureRenforceeActive = false;
    private int toursRestantsArmure = 0;
    private Random random = new Random();

    public Tarasque() {
        super("Tarasque", 200, 30, 25, true); // Boss: bcp de PV, bonne attaque/défense
    }

    @Override
    public void attaque1(Personnage cible) {
        // Attaque de base
        int degats = calculerDegats(this.getAttaque(), cible.getDefense());
        System.out.println(getNom() + " donne un coup de queue ! Il inflige " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    @Override
    public void attaque2(Personnage cible) {
        // Attaque plus puissante mais plus aléatoire
        int degats = calculerDegats(this.getAttaque() + 10, cible.getDefense());
        System.out.println(getNom() + " charge brutalement ! Il inflige " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        // Pouvoir spécial : renforce temporairement sa défense
        if (!armureRenforceeActive) {
            armureRenforceeActive = true;
            toursRestantsArmure = 3;
            this.setDefense(this.getDefense() + 20);
            System.out.println(getNom() + " active son armure d’écailles renforcées ! Défense +20 pour 3 tours.");
        }
    }

    @Override
    public void miseAJourTour() {
        // Gestion de la fin de l'effet de l’armure renforcée
        if (armureRenforceeActive) {
            toursRestantsArmure--;
            if (toursRestantsArmure <= 0) {
                armureRenforceeActive = false;
                this.setDefense(this.getDefense() - 20);
                System.out.println(getNom() + " perd les effets de son armure renforcée !");
            }
        }
    }

    /**
     * Redéfinir prise de dégâts pour réduire dégâts quand bouclier actif
     */
    @Override
    public void prendreDegats(int degats) {
        if (armureRenforceeActive) {
            degats = degats / 2; // Réduction de 50% des dégâts si bouclier actif
            System.out.println(this.nom + " réduit les dégâts grâce à son bouclier !");
        }
        super.prendreDegats(degats);
    }
}
