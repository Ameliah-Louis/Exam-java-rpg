package personnages;

import java.util.Random;

public class Elementaire extends Ennemi {
    private boolean formeRenforcee = false;
    private int toursRenforcement = 0;
    private Random random = new Random();

    public Elementaire() {
        super("Élémentaire", 160, 35, 10, true); // Moins tanky mais plus offensif
    }

    @Override
    public void attaque1(Personnage cible) {
        // Attaque de feu : dégâts purs
        int degats = calculerDegats(this.getAttaque(), cible.getDefense());
        System.out.println(getNom() + " lance une boule de feu et inflige " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    @Override
    public void attaque2(Personnage cible) {
        // Attaque de glace : dégâts + possible malus
        int degats = calculerDegats(this.getAttaque() - 5, cible.getDefense());
        System.out.println(getNom() + " invoque une tempête de glace ! Il inflige " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        // Se transforme en forme élémentaire pure : bonus d’attaque pendant 3 tours
        if (!formeRenforcee) {
            formeRenforcee = true;
            toursRenforcement = 3;
            this.setAttaque(this.getAttaque() + 15);
            System.out.println(getNom() + " fusionne avec les éléments ! Attaque +15 pour 3 tours.");
        }
    }

    @Override
    public void miseAJourTour() {
        // Gestion de la fin de la forme élémentaire
        if (formeRenforcee) {
            toursRenforcement--;
            if (toursRenforcement <= 0) {
                formeRenforcee = false;
                this.setAttaque(this.getAttaque() - 15);
                System.out.println(getNom() + " revient à sa forme normale.");
            }
        }
    }
}
