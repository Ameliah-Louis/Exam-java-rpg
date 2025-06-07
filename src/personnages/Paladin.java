package personnages;

import interfaces.PouvoirSpecial;

// La classe Paladin hérite de Personnage et implémente PouvoirSpecial
public class Paladin extends Personnage implements PouvoirSpecial {

    // Cooldown restant avant de pouvoir réutiliser le pouvoir spécial
    private int cooldownRestant;

    // Mana maximal et actuel (le paladin a du mana)
    private int manaMax;
    private int mana;

    // Nombre de potions de soin disponibles
    private int potionsSoin;
    private int potionsMana;

    // Constructeur du Paladin
    public Paladin(String nom) {
        // Appel au constructeur parent avec PV, attaque et défense
        super(nom, 130, 20, 20);

        this.manaMax = 100;
        this.mana = manaMax;

        this.cooldownRestant = 0;

        // On commence avec 1 potion de soin et de mana
        this.potionsSoin = 1;
        this.potionsMana = 2;
    }

    // Getters et setters pour mana et potions
    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public int getCooldownRestant() {
        return cooldownRestant;
    }

    public int getPotionsSoin() {
        return potionsSoin;
    }
    public int getPotionsMana() {
        return potionsMana;
    }

    public void setPotionsSoin(int potionsSoin) {
        this.potionsSoin = potionsSoin;
    }
    public void setPotionsMana(int potionsMana) {
        this.potionsMana = potionsMana;
    }

    // Implémentation du pouvoir spécial "Châtiment divin"
    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutMana = 30;     // Coût en mana du pouvoir
        int degatsPouvoir = 35; // Dégâts moyens, supérieurs à l'attaque normale

        if (cooldownRestant > 0) {
            System.out.println(getNom() + " ne peut pas utiliser Châtiment divin, cooldown : " + cooldownRestant + " tours restants.");
            return;
        }

        if (mana < coutMana) {
            System.out.println(getNom() + " n'a pas assez de mana pour utiliser Châtiment divin !");
            return;
        }

        // Dépense le mana
        mana -= coutMana;

        // Inflige les dégâts au cible
        System.out.println(getNom() + " utilise Châtiment divin sur " + cible.getNom() + " et inflige " + degatsPouvoir + " dégâts.");
        cible.prendreDegats(degatsPouvoir);

        // Soigne le paladin de 15% des dégâts infligés
        int soin = (int)(degatsPouvoir * 0.15);
        this.soigner(soin);
        System.out.println(getNom() + " se soigne de " + soin + " PV grâce au Châtiment divin.");

        // Active le cooldown (4 tours)
        cooldownRestant = 4;
    }

    // Restriction de soigner: ne peux pas dépasser pvMax
    public void soigner(int montant) {
        this.pv += montant;
        if (this.pv > this.pvMax) {
            this.pv = this.pvMax;
        }
    }

    // Méthode update à appeler à chaque tour pour gérer cooldown et régénération mana
    public void updatePouvoirs() {
        if (cooldownRestant > 0) {
            cooldownRestant--;
        }
        // Regénère un peu de mana à chaque tour (ex: +5)
        mana = Math.min(mana + 5, manaMax);
    }

    // Utiliser potion de soin, similaire à Guerrier
    public void utiliserPotionSoin() {
        if (potionsSoin > 0) {
            int soins = (int)(pvMax * 0.3);
            pv += soins;
            if (pv > pvMax) {
                pv = pvMax;
            }
            potionsSoin--;
            System.out.println(getNom() + " utilise une potion de soin et récupère " + soins + " PV. PV actuels: " + pv);
        } else {
            System.out.println(getNom() + " n'a plus de potion de soin !");
        }
    }

    // Potion de mana : restaure 30% mana max
    public void utiliserPotionMana() {
        if (potionsMana > 0) {
            int regenMana = (int)(manaMax * 0.3);
            mana += regenMana;
            if (mana > manaMax) mana = manaMax;
            potionsMana--;
            System.out.println(getNom() + " utilise une potion de mana et récupère " + regenMana + " mana. Mana actuel : " + mana);
        } else {
            System.out.println(getNom() + " n'a plus de potions de mana !");
        }
    }

    public void restaurerMana(int montant) {
        this.mana = Math.min(this.mana + montant, this.manaMax);
    }
    public void rechargerPotions() {
        this.potionsSoin = 1; // Ou la quantité que tu souhaites
        this.potionsMana = 1; // Pour les classes qui ont du mana (Mage, Paladin)
    }


    // Redéfinir la méthode attaquer pour afficher le mana et cooldown
    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " attaque " + cible.getNom() + " et inflige " + attaque + " dégâts.");
        cible.prendreDegats(attaque);
    }

    // Redéfinir prendreDegats pour afficher info
    @Override
    public void prendreDegats(int degats) {
        super.prendreDegats(degats);
    }
}
