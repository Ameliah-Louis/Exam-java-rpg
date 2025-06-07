package interfaces;

import personnages.Personnage;

public interface Potion {
    // Méthode à appeler lors de l'utilisation de la potion
    void utiliser(Personnage cible);

    // Retourne le nom de la potion
    String getNom();
}
