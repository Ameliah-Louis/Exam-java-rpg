package personnages;

// Le Dragon est un ennemi classique mais plus costaud
public class Dragon extends Ennemi {

    private boolean defenseRenforcee = false;

    public Dragon() {
        super("Dragon", 200, 30, 15, false); // nom, pv, attaque
    }

    @Override
    public void attaque1(Personnage cible) {
        System.out.println(nom + " crache un énorme souffle de feu !");
        cible.prendreDegats(attaque + 20);
    }

    @Override
    public void attaque2(Personnage cible) {
        System.out.println(nom + " donne un coup de queue !");
        cible.prendreDegats(attaque);
    }

    @Override
    public void attaquer(Personnage cible) {
        double chance = Math.random();

        if (chance <= 0.10) {
            // 10% de chance d'utiliser le pouvoir spécial
            utiliserPouvoir(cible);
        } else {
            // 90% de chance d'utiliser une des deux attaques normales (chacune a 50% de chance)
            if (Math.random() < 0.5) {
                attaque1(cible);
            } else {
                attaque2(cible);
            }
        }
    }

    @Override
    public void miseAJourTour() {

    }

    @Override
    public void utiliserPouvoir(Personnage cible) {
        // Le dragon active sa défense renforcée pour réduire les dégâts subis
        System.out.println(nom + " frémit et renforce ses écailles !");
        defenseRenforcee = true;
    }

    @Override
    public void prendreDegats(int degats) {
        if (defenseRenforcee) {
            degats /= 2;
            System.out.println(nom + " subit des dégâts réduits grâce à sa défense renforcée !");
            defenseRenforcee = false; // Effet temporaire
        }
        super.prendreDegats(degats);
    }
}
