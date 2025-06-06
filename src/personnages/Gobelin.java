package personnages;

public class Gobelin extends Ennemi {

    public Gobelin() {
        // Appel du constructeur parent avec nom et stats spécifiques au Gobelin
        super("Gobelin", 50, 10, 5, false);  // PV=50, Attaque=10, Défense=5 (exemple)
    }

    // Override Attaque 1 : attaque simple, inflige dégâts basiques
    @Override
    protected void attaque1(Personnage cible) {
        int degats = this.getAttaque() - cible.getDefense();
        if (degats <= 0) degats = 1;
        System.out.println(getNom() + " frappe " + cible.getNom() + " pour " + degats + " dégâts " + " !");
        cible.prendreDegats(degats);
    }

    // Attaque 2 : attaque spéciale un peu plus forte
    @Override
    protected void attaque2(Personnage cible) {
        int degats = this.getAttaque() + 5 - cible.getDefense();  // +5 dégâts bonus
        if (degats <= 0) degats = 1;
        System.out.println(getNom() + " lance un coup puissant en hurlant sur " + cible.getNom() + " pour " + degats + " dégâts " + " !");
        cible.prendreDegats(degats);
    }

    @Override
    public void utiliserPouvoir(Personnage cible) {

    }
}
