package personnages;

import java.util.Random;

public class Troll extends Ennemi {

    private Random random = new Random();

    // Constructeur Troll avec stats solides
    public Troll() {
        super("Troll", 120, 25, 10, false);
    }

    /**
     * Attaque basique lourde mais lente
     */
    @Override
    public void attaque1(Personnage cible) {
        int degats = 5 + random.nextInt(10); // Dégâts entre 20 et 29
        System.out.println(this.nom + " frappe " + cible.getNom() + " pour " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    @Override
    protected void attaque2(Personnage cible) {
        int degats = 20 + random.nextInt(10); // Dégâts entre 20 et 29
        System.out.println(this.nom + " écrase sa masse sur " + cible.getNom() + " pour " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    /**
     * Pouvoir spécial : régénération qui soigne un peu le Troll
     */
    @Override
    public void utiliserPouvoir(Personnage cible) {
        int soin = 15;
        this.pv += soin;
        if (this.pv > this.pvMax) this.pv = this.pvMax;
        System.out.println(this.nom + " se régénère et récupère " + soin + " PV. PV actuels : " + this.pv);
    }

    @Override
    public void miseAJourTour() {
    }
}
