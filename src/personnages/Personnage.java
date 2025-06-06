package personnages;

// Classe abstraite de base pour tous les personnages
public abstract class Personnage {
    protected String nom;
    protected int pv;
    protected int attaque;
    protected int defense;

    // Constructeur
    // getters & setters

    // Méthode attaquer: calcule dégâts et appelle cible.prendreDegats()
    public void attaquer(Personnage cible) {
        // TODO
    }

    // Réduit les PV selon dégats reçus
    public void prendreDegats(int degats) {
        // TODO
    }

    // Retourne vrai si pv > 0
    public boolean estVivant() {
        // TODO
        return false;
    }

    // (Optionnel) afficher fiche de stats
    public void afficherStats() {
        // TODO
    }
}
