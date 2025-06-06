package personnages;

// Classe abstraite de base pour tous les personnages
public abstract class Personnage {
    protected String nom;
    protected int pv;
    protected int pvMax;
    protected int attaque;
    protected int defense;

    // Constructeur
    public Personnage(String nom, int pvInitial, int attaque, int defense) {
        this.nom = nom;
        this.pvMax = pvInitial;  // pvMax fixé à la vie de départ
        this.pv = pvInitial;     // pv courant = pv max au départ
        this.attaque = attaque;
        this.defense = defense;
    }
    // getters & setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    // Méthode attaquer: calcule dégâts et appelle cible.prendreDegats()
    public void attaquer(Personnage cible) {
        // Calcul des dégâts bruts
        int degats = this.attaque - cible.getDefense();

        // Dégâts minimum pour éviter le personnage invincible
        if (degats < 1) {
            degats = 1;
        }

        // Appliquer les dégâts à la cible
        cible.prendreDegats(degats);

        // Afficher un message au joueur
        System.out.println(this.nom + " attaque " + cible.getNom() + " et inflige " + degats + " points de dégâts !");
    }

public void prendreDegats(int degats) {
    // On soustrait les dégâts reçus aux points de vie actuels
    this.pv -= degats;

    // Si les points de vie descendent en dessous de zéro, on les met à zéro
    if (this.pv < 0) {
        this.pv = 0;
    }

    // Affiche le nouvel état des points de vie pour feedback
    System.out.println(this.nom + " a maintenant " + this.pv + " PV.");
}

    // Retourne vrai si pv > 0
    public boolean estVivant() {
// Si les points de vie sont supérieurs à 0, alors il est vivant
        return this.pv > 0;
    }

    // (Optionnel) afficher fiche de stats
    public void afficherStats() {
        // TODO
    }
}
