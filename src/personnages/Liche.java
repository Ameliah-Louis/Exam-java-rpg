package personnages;

public class Liche extends Ennemi {

    private boolean phylactereActif;
    private int bouclierPhylactere;

    public Liche() {
        super("Liche", 120, 30, 10, true); // PV ajustables
        this.phylactereActif = false;
        this.bouclierPhylactere = 0;
    }

    @Override
    public void attaquer(Personnage cible) {
        double chance = Math.random();

        if (chance < 0.15) {
            utiliserPouvoir(cible);
        } else if (chance < 0.6) {
            attaqueDrainante(cible);
        } else {
            attaqueNecrotique(cible);
        }
    }

    @Override
    public void miseAJourTour() {

    }

    // Définir l'attaque 1 comme une attaque Nécrotique
    @Override
    public void attaque1(Personnage cible) {
        attaqueNecrotique(cible);
    }

    private void attaqueNecrotique(Personnage cible) {
        int degats = 25;
        System.out.println(nom + " lance une attaque nécrotique sur " + cible.getNom() + " pour " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }


    // Définir l'attaque 2 comme une attaque Drainante
    @Override
    public void attaque2(Personnage cible) {
        attaqueDrainante(cible);
    }

    private void attaqueDrainante(Personnage cible) {
        int degats = 10;
        int soin = degats * 3;

        System.out.println(nom + " absorbe la vie de " + cible.getNom() + " infligeant " + degats + " dégâts et se soigne de " + soin + " PV !");
        cible.prendreDegats(degats);
        this.pv += soin;
        if (this.pv > this.pvMax) this.pv = this.pvMax;
    }

    //Gestion du pouvoir spécial Phylactère : un bouclier d'absorption des dégâts
    @Override
    public void utiliserPouvoir(Personnage cible) {
        if (!phylactereActif) {
            phylactereActif = true;
            bouclierPhylactere = 30;
            System.out.println(nom + " crée un Phylactère magique qui absorbera les prochains dégâts (" + bouclierPhylactere + " PV bouclier) !");
        } else {
            System.out.println(nom + " tente de créer un Phylactère... mais il est déjà actif !");
        }
    }

    @Override
    public void prendreDegats(int degats) {
        if (phylactereActif) {
            if (degats <= bouclierPhylactere) {
                bouclierPhylactere -= degats;
                System.out.println(nom + " absorbe les dégâts avec son phylactère. Bouclier restant : " + bouclierPhylactere);
                if (bouclierPhylactere == 0) {
                    phylactereActif = false;
                    System.out.println("Le Phylactère de " + nom + " est détruit !");
                }
                return;
            } else {
                int restants = degats - bouclierPhylactere;
                System.out.println(nom + " absorbe " + bouclierPhylactere + " dégâts avec son phylactère. Le reste passe !");
                phylactereActif = false;
                bouclierPhylactere = 0;
                super.prendreDegats(restants);
                return;
            }
        }

        super.prendreDegats(degats);
    }



}
