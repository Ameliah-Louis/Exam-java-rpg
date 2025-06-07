package personnages;

import interfaces.PouvoirSpecial;

// La classe Guerrier hérite de Personnage et implémente PouvoirSpecial
public class Guerrier extends Personnage implements PouvoirSpecial {

    // Vrai si le Guerrier est en Rage
    private boolean enRage;

    // Nombre de tours restants pour la Rage
    private int dureeRageRestante;

    // Cooldown (nombre de tours à attendre avant de réutiliser la Rage)
    private int cooldownRestant;

    // Nombre de potion disponible
    private int potionsSoin;

    // Constructeur du Guerrier (appel au constructeur de Personnage via super)
    public Guerrier(String nom) {
        super(nom, 150, 25, 15);

        // Par défaut, pas en Rage
        this.enRage = false;

        // Donc pas de durée restante de rage au depart
        this.dureeRageRestante = 0;

        // Et pas de cooldown non plus
        this.cooldownRestant = 0;

        // Une seule potion au début de chaque combat
        this.potionsSoin = 1;
    }

    // Getters & Setters


    public boolean isEnRage() {
        return enRage;
    }

    public void setEnRage(boolean enRage) {
        this.enRage = enRage;
    }

    public int getDureeRageRestante() {
        return dureeRageRestante;
    }

    public void setDureeRageRestante(int dureeRageRestante) {
        this.dureeRageRestante = dureeRageRestante;
    }

    public int getCooldownRestant() {
        return cooldownRestant;
    }

    public void setCooldownRestant(int cooldownRestant) {
        this.cooldownRestant = cooldownRestant;
    }

    public int getPotionsSoin() {
        return potionsSoin;
    }

    public void setPotionsSoin(int potionsSoin) {
        this.potionsSoin = potionsSoin;
    }

    // Méthode utiliserPouvoir() : écraser le défaut pour le pouvoir spécifique du Guerrier : active la Rage si le cooldown est fini
    @Override
    public void utiliserPouvoir(Personnage cible) {
        // On vérifie si le pouvoir peut être activé (pas en cooldown et pas déjà en Rage)
        if (cooldownRestant == 0 && !enRage) {
            // Activation de la Rage
            enRage = true;

            // La Rage dure 3 tours
            dureeRageRestante = 3;

            // Le cooldown commence à 5 tours dès l'activation
            cooldownRestant = 5;

            // Message d'information pour le joueur
            System.out.println(this.getNom() + " entre en Rage ! Ses attaques sont renforcées pour 3 tours.");
        } else {
            // Si on est en cooldown ou déjà en Rage, on prévient que le pouvoir n'est pas dispo
            System.out.println("La Rage n'est pas encore disponible. Patientez " + cooldownRestant + " tours.");
        }
    }

    // Méthode attaquer() : fait + de dégâts si en Rage
    @Override
    public void attaquer(Personnage cible) {
        int degats = this.attaque; // dégâts de base

        if (enRage) {
            // Bonus de dégâts de Rage
            degats *= 1.5; //  dégats +50%
        }

        // Message d’info dans la console
        System.out.println(getNom() + (enRage ? " en Rage " : " ") + "attaque " + cible.getNom() + " et inflige " + degats + " dégâts.");

        // Appliquer les dégâts à la cible
        cible.prendreDegats(degats);
    }

    // Méthode prendreDegats() : subit + de dégâts si en Rage
    @Override
    public void prendreDegats(int degats) {
        int degatsSubis = degats;

        if (enRage) {
            // Augmente les dégâts reçus de 50%
            degatsSubis = (int)(degats * 1.5);
        }

        // Appelle la méthode prendreDegats de la classe parent Personnage
        super.prendreDegats(degatsSubis);
    }


    // Méthode pour réduire les durées de Rage et cooldown chaque tour
    public void updatePouvoirs() {
        // Si la Rage est active
        if (enRage) {
            // On réduit la durée restante d'un tour
            dureeRageRestante--;

            // Si la Rage vient de se terminer
            if (dureeRageRestante <= 0) {
                enRage = false;
                System.out.println(getNom() + " n'est plus en Rage.");
            }
        }

        // On réduit le cooldown s'il est en cours
        if (cooldownRestant > 0) {
            cooldownRestant--;
        }
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
    public void rechargerPotions() {
        this.potionsSoin = 1; // Ou la quantité que tu souhaites
    }



}
