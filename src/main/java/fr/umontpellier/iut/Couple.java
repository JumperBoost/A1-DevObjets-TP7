package fr.umontpellier.iut;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class Couple {

    private final JeuPuzzle puzzle;
    private final Couple predecesseur;

    public Couple(JeuPuzzle puzzle, Couple predecesseur) {
        this.puzzle = puzzle;
        this.predecesseur = predecesseur;
    }

    /**
     * Vérifie si les fils du taquin sont déjà vus et met à jour la frontière
     * et l'ensemble des configurations déjà vues.
     */
    public void mettreAJour(ArrayList<Couple> frontiere, ArrayList<JeuPuzzle> dejaVus) {
        ArrayList<?> taquinsFils = puzzle.genererFils();
            for(Object object : taquinsFils) {
            Couple couple_object = new Couple((JeuPuzzle) object, this);
            // Ajouter si inexistant
            if(!dejaVus.contains(object)) {
                frontiere.add(couple_object);
                dejaVus.add((JeuPuzzle) object);
            }
        }
    }

    /**
     * @return la liste des taquins intermédiaires à partir du taquin initial
     * et jusqu'au taquin courant
     */
    public ArrayList<JeuPuzzle> getListeDeMouvements() {
        ArrayList<JeuPuzzle> listePuzzle = new ArrayList<>();
        Couple couple = this;
        do {
            listePuzzle.add(0, couple.puzzle);
            couple = couple.predecesseur;
        } while (couple != null);
        return listePuzzle;
    }

    public JeuPuzzle getPuzzle() {
        return puzzle;
    }
}
