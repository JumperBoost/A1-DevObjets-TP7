package fr.umontpellier.iut;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class Taquin implements JeuPuzzle {
    private final int[][] tableau;

    public Taquin(int[][] tableau) {
        this.tableau = tableau;
    }

    /**
     * @return true si le Taquin courant est dans une configuration gagnante
     */
    public boolean estGagnant() {
        for(int i = 0; i < tableau.length; i++)
            for(int j = 0; j < tableau[i].length; j++)
                if(tableau[i][j] != i*tableau[i].length + j+1 && !(i == tableau.length-1 && j == tableau[i].length-1))
                    return false;
        return true;
    }

    /**
     * @return la liste des configurations obtenues avec un seul mouvement
     * à partir du Taquin courant
     */
    public Set<Taquin> genererFils() {
        int[] trou = trouverTrou();
        Set<Taquin> valides = new HashSet<>();
        if(trou[1]-1 >= 0 && tableau[trou[0]][trou[1]-1] != 0)
            valides.add(genererTaquin(trou, trou[0], trou[1]-1));
        if(trou[0]-1 >= 0 && tableau[trou[0]-1][trou[1]]!= 0)
            valides.add(genererTaquin(trou, trou[0]-1, trou[1]));
        if(trou[1]+1 < tableau[0].length && tableau[trou[0]][trou[1]+1] != 0)
            valides.add(genererTaquin(trou, trou[0], trou[1]+1));
        if(trou[0]+1 < tableau.length && tableau[trou[0]+1][trou[1]] != 0)
            valides.add(genererTaquin(trou, trou[0]+1, trou[1]));
        return valides;
    }

    private Taquin genererTaquin(int[] trou, int i, int j) {
        int[][] taquin_tableau = new int[tableau.length][tableau[0].length];
        for(int a = 0; a < tableau.length; a++)
            for(int b = 0; b < tableau[a].length; b++)
                taquin_tableau[a][b] = tableau[a][b];
        taquin_tableau[trou[0]][trou[1]] = tableau[i][j];
        taquin_tableau[i][j] = 0;
        return new Taquin(taquin_tableau);
    }

    /**
     * @return un tableau [i,j] si tableau[i][j]==0
     */
    public int[] trouverTrou() {
        for(int i = 0; i < tableau.length; i++)
            for(int j = 0; j < tableau[i].length; j++)
                if(tableau[i][j] == 0)
                    return new int[] {i, j};
        return null;
    }

    @Override
    public String toString() {
        String res = "+" + "-".repeat(4*tableau[0].length - 1) + "+\n";
        for(int i = 0; i < tableau.length; i++) {
            res += "|";
            for(int j = 0; j < tableau[i].length; j++) {
                res += (j != 0 ? "  " : "") + " ".repeat(2 - String.valueOf(tableau[i][j]).length()) + tableau[i][j];
            }
            res += " |\n";
        }
        res += "+" + "-".repeat(4*tableau[0].length - 1) + "+";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taquin taquin = (Taquin) o;
        return Arrays.deepEquals(tableau, taquin.tableau);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tableau);
    }
}
