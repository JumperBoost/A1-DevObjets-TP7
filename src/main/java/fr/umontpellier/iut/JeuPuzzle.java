package fr.umontpellier.iut;

import java.util.Set;

public interface JeuPuzzle {
    boolean estGagnant();
    Set<? extends JeuPuzzle> genererFils();
}
