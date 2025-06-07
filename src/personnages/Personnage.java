package personnages;

import interfaces.Potion;

// Classe abstraite de base pour tous les personnages
public abstract class Personnage {
    protected String nom;
    protected int pv;
    protected int pvMax;
    protected int Mana;
    protected int ManaMax;
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

    public int getMana() {
        // Conseillé par GPT pour gérer les classes sans Mana. On a un défaut à moins 1 qui n'affichera pas le mana mais
        // on l'override dans les classes qui possèdent du mana avec un return Mana
        return -1;
    }

    public void setMana(int mana) {
        Mana = mana;
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

// Méthode abstraite du pouvoir qui sera implémentée dans chaque classe avec sa logique propre
public abstract void utiliserPouvoir(Personnage cible);


// Calcule les dégâts nets infligés à une cible en fonction de sa défense
    public int calculerDegats(int attaque, int defenseCible) {
        int degats = attaque - defenseCible;
        return Math.max(1, degats); // Toujours infliger au moins 1 point de dégât
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

    public class PotionMana implements Potion {

        private int manaAmount;

        public PotionMana(int manaAmount) {
            this.manaAmount = manaAmount;
        }

        @Override
        public void utiliser(Personnage cible) {
            // Si le personnage a un mana, on le restaure
            if (cible instanceof Paladin || cible instanceof Mage) {
                int manaAvant = 0;
                int manaApres = 0;

                if (cible instanceof Paladin) {
                    Paladin p = (Paladin) cible;
                    manaAvant = p.getMana();
                    p.restaurerMana(manaAmount);
                    manaApres = p.getMana();
                } else if (cible instanceof Mage) {
                    Mage m = (Mage) cible;
                    manaAvant = m.getMana();
                    m.restaurerMana(manaAmount);
                    manaApres = m.getMana();
                }
                System.out.println(cible.getNom() + " utilise une potion de mana et récupère " + (manaApres - manaAvant) + " mana.");
            } else {
                System.out.println(cible.getNom() + " ne peut pas utiliser cette potion de mana !");
            }
        }

        @Override
        public String getNom() {
            return "Potion de Mana";
        }
    }

    public void soigner(int montant) {
        this.pv += montant;
        if (this.pv > this.pvMax) {
            this.pv = this.pvMax;
        }
    }



    // (Optionnel) afficher fiche de stats
    public void afficherStats() {
        // TODO
    }
}
