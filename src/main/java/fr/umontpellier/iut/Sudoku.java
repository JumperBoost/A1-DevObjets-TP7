package fr.umontpellier.iut;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Sudoku implements JeuPuzzle {
    private final int[][] grille;
    private final int sousCarreTaille;

    // pré-requis : la grille est carrée
    public Sudoku(int[][] g) {
        this.grille = g;
        this.sousCarreTaille = (int) Math.sqrt(g.length);
    }

    public boolean estGagnant() {
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++) {
                if(!verifierSousCarre(i, j) || !verifierLigne(i) || !verifierColonne(j))
                    return false;
            }
        }
        return true;
    }

    public boolean verifierSousCarre(int i, int j) {
        ArrayList<Integer> valeurs = new ArrayList<>();
        int[] debCarre = getDebCarre(i, j);
        for(int x = debCarre[0]; x < debCarre[0] + sousCarreTaille; x++) {
            for(int y = debCarre[1]; y < debCarre[1] + sousCarreTaille; y++) {
                if(valeurs.contains(grille[x][y]) || grille[x][y] == 0)
                    return false;
                valeurs.add(grille[x][y]);
            }
        }
        return true;
    }

    public boolean verifierLigne(int i) {
        ArrayList<Integer> valeurs = new ArrayList<>();
        for(int j = 0; j < grille[i].length; j++) {
            if(valeurs.contains(grille[i][j]) || grille[i][j] == 0)
                return false;
            valeurs.add(grille[i][j]);
        }
        return true;
    }

    public boolean verifierColonne(int j) {
        ArrayList<Integer> valeurs = new ArrayList<>();
        for(int i = 0; i < grille.length; i++) {
            if(valeurs.contains(grille[i][j]) || grille[i][j] == 0)
                return false;
            valeurs.add(grille[i][j]);
        }
        return true;
    }

    @Override
    public ArrayList<Sudoku> genererFils() {
        ArrayList<Sudoku> fils = new ArrayList<>();
        int[] trou = getTrou();
        if(trou != null) {
            ArrayList<Integer> valPoss = getValPoss(trou[0], trou[1]);
            int[][] grilleCopie;
            for(int val : valPoss) {
                grilleCopie = copieMatrice(grille);
                grilleCopie[trou[0]][trou[1]] = val;
                fils.add(new Sudoku(grilleCopie));
            }
        }
        return fils;
    }

    public int[] getDebCarre(int i, int j) {
        return new int[] {(i / sousCarreTaille) * sousCarreTaille, (j / sousCarreTaille) * sousCarreTaille};
    }

    private int[][] copieMatrice(int[][] matrice){
        int[][] matriceCopie = new int[matrice.length][matrice.length];
        for(int i = 0; i < matrice.length; i++)
            for(int j = 0; j < matrice[i].length; j++)
                matriceCopie[i][j] = matrice[i][j];
        return matriceCopie;
    }

    public ArrayList<Integer> getValPoss(int i, int j) {
        if(grille[i][j] != 0)
            return new ArrayList<>();

        int[] debCarre = getDebCarre(i, j);
        ArrayList<Integer> valPoss = new ArrayList<>();
        // Ajout des valeurs possibles
        for(int k = 1; k <= sousCarreTaille*sousCarreTaille; k++)
            valPoss.add(k);

        // Sous-carre
        for(int x = debCarre[0]; x < debCarre[0] + sousCarreTaille; x++)
            for (int y = debCarre[1]; y < debCarre[1] + sousCarreTaille; y++)
                if(x != i && y != j && grille[x][y] != 0)
                    valPoss.remove((Integer) grille[x][y]);

        // Ligne
        int[] debCarreY;
        for(int y = 0; y < grille.length; y++) {
            debCarreY = getDebCarre(i, j);
            if (debCarreY != debCarre && grille[i][y] != 0)
                valPoss.remove((Integer) grille[i][y]);
        }

        // Colonne
        for(int x = 0; x < grille.length; x++) {
            int[] debCarreX = getDebCarre(x, j);
            if (debCarreX != debCarre && grille[x][j] != 0)
                valPoss.remove((Integer) grille[x][j]);
        }

        return valPoss;
    }

    public int[] getTrou() {
        for(int i = 0; i < grille.length; i++)
            for(int j = 0; j < grille[i].length; j++)
                if(grille[i][j] == 0)
                    return new int[] {i, j};
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sudoku sudoku = (Sudoku) o;
        return sousCarreTaille == sudoku.sousCarreTaille && Arrays.deepEquals(grille, sudoku.grille);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sousCarreTaille);
        result = 31 * result + Arrays.deepHashCode(grille);
        return result;
    }
}
