package personnages;

import interfaces.PouvoirSpecial;

import java.util.Random;

// La classe Mage hérite de Personnage et implémente PouvoirSpecial
public class Mage extends Personnage implements PouvoirSpecial {

    private int mana;            // Points de mana actuels
    private int manaMax;         // Mana maximum
    private int potionsSoin;     // Nombre de potions de soin disponibles
    private int potionsMana;     // Nombre de potions de mana disponibles
    private int cooldownSort;    // Cooldown du pouvoir spécial (en tours)
    private int toursBouclierRestants = 0;

    private Random random = new Random();

    // Constructeur Mage avec stats spécifiques
    public Mage(String nom) {
        super(nom, 100, 20, 8);  // moins de PV, attaque moyenne, défense faible
        this.manaMax = 100;
        this.mana = manaMax;
        this.potionsSoin = 2; // le Mage commence avec 2 potions de soin
        this.potionsMana = 2; // le Mage commence avec 2 potions de mana
        this.cooldownSort = 0;   // pas de cooldown au départ
    }

    // Getters
    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public int getPotionsSoin() {
        return potionsSoin;
    }

    public int getPotionsMana() {
        return potionsMana;
    }

    public int getCooldownSort() {
        return cooldownSort;
    }

    // Attaque classique du Mage : sort de feu, coûte un peu de mana
    @Override
    public void attaquer(Personnage cible) {
        int coutMana = 0;

        if (mana < coutMana) {
            System.out.println(getNom() + " n'a pas assez de mana pour lancer une attaque magique !");
            return;
        }

        mana -= coutMana;

        int degats = 15 + random.nextInt(11);  // dégâts entre 15 et 25
        System.out.println(getNom() + " lance un sort de feu sur " + cible.getNom() + " et inflige " + degats + " dégâts.");
        cible.prendreDegats(degats);
    }

    // Pouvoir spécial : Bouclier magique (coût 30 mana, cooldown 5 tours)
    @Override
    public void utiliserPouvoir(Personnage cible) {
        int coutManaPouvoir = 30;
        if (cooldownSort > 0) {
            System.out.println("Pouvoir spécial en cooldown, disponible dans " + cooldownSort + " tours.");
            return;
        }
        if (mana < coutManaPouvoir) {
            System.out.println(getNom() + " n'a pas assez de mana pour activer le bouclier magique !");
            return;
        }
        mana -= coutManaPouvoir;
        cooldownSort = 5;
        toursBouclierRestants = 2;
        System.out.println(getNom() + " active un bouclier magique protecteur pour 2 tours !");
    }

    public void updatePouvoirs() {
        if (cooldownSort > 0) cooldownSort--;

        if (toursBouclierRestants > 0) {
            toursBouclierRestants--;
            if (toursBouclierRestants <= 0) {
                System.out.println(getNom() + " : le bouclier magique disparaît !");
            }
        }

        mana = Math.min(mana + 5, manaMax);
    }


    // Potion de soin : restaure 30% PV max
    public void utiliserPotionSoin() {
        if (potionsSoin > 0) {
            int soinsPV = (int)(pvMax * 0.3);
            pv += soinsPV;
            if (pv > pvMax) pv = pvMax;
            potionsSoin--;
            System.out.println(getNom() + " utilise une potion de soin et récupère " + soinsPV + " PV. PV actuels : " + pv);
        } else {
            System.out.println(getNom() + " n'a plus de potions de soin !");
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


    // Prise de dégâts classique (sans bouclier)
    @Override
    public void prendreDegats(int degats) {
        if (toursBouclierRestants > 0) {
            System.out.println(getNom() + " est protégé par le bouclier et ne subit aucun dégât !");
            // Bouclier absorbe tout => pas de dégâts pris
        } else {
            super.prendreDegats(degats);
        }
    }

}
