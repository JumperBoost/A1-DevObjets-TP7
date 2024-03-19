package fr.umontpellier.iut;

import java.util.*;

@SuppressWarnings("Duplicates")
public class Contexte {

    private final JeuPuzzle puzzle;
    private ArrayList<JeuPuzzle> solution;

    public Contexte(JeuPuzzle puzzle) {
        this.puzzle = puzzle;
        solution = new ArrayList<>();
    }

    public void resoudre() {
        Set<JeuPuzzle> dejaVus = new HashSet<>(List.of(puzzle));
        Queue<Couple> frontieres = new LinkedList<>(List.of(new Couple(puzzle, null)));
        Couple couple;
        while (!frontieres.isEmpty()) {
            couple = frontieres.poll();
            if (couple.getPuzzle().estGagnant()) {
                solution = couple.getListeDeMouvements();
                return;
            }
            frontieres.remove(couple);
            couple.mettreAJour(frontieres, dejaVus);
        }
    }  // Résolution en file : exploration de l'arbre en largeur

    public ArrayList<JeuPuzzle> getSolution() {
        return solution;
    }

    public int getDistance() {
        return solution.size() - 1;
    }

    @Override
    public String toString() {
        String res = "";
        for(JeuPuzzle puzzle : solution) {
            if(!res.isEmpty())
                res += "\n";
            res += puzzle.toString();
        }
        return res;
    }
}
