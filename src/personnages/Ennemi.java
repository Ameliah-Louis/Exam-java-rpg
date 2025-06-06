package personnages;

import java.util.Random;

// Ennemi hérite de Personnage, donc possède nom, pv, attaque, défense, etc.
public abstract class Ennemi extends Personnage {
    private boolean estBoss;
    protected Random random = new Random(); // pour choisir une attaque aléatoirement


    /**
     * Constructeur de base pour un Ennemi, on passe ses stats au parent
     * @param nom
     * @param pvInitial
     * @param attaque
     * @param defense
     * @param estBoss
     */
    public Ennemi(String nom, int pvInitial, int attaque, int defense, boolean estBoss) {
        super(nom, pvInitial, attaque, defense);
        this.estBoss = estBoss;
    }

    public boolean estBoss() {
        return estBoss;
    }

    // Méthode abstraite : chaque ennemi doit définir ses 2 attaques spécifiques
    protected abstract void attaque1(Personnage cible);
    protected abstract void attaque2(Personnage cible);

    // Méthode publique pour attaquer le héros au tour par tour
    public void attaquer(Personnage cible) {
        // Choix aléatoire entre attaque1 et attaque2
        int choix = random.nextInt(2); // 0 ou 1 mais pas booléen pour se laisser la possibilité d'ajouter facilement des attaques

        if (choix == 0) {
            attaque1(cible);
        } else {
            attaque2(cible);
        }
    }
}
