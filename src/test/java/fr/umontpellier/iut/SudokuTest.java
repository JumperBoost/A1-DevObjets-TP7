package fr.umontpellier.iut;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    public static int[][] grille_sudoku22_incomplete2() {
        return new int[][]{
                {4, 1, 2, 3},
                {0, 0, 1, 4},
                {0, 3, 4, 1},
                {1, 4, 3, 2}
        };
    }

    public static int[][] grille_sudoku22_incomplete_fils1() {
        return new int[][]{
                {4, 1, 2, 3},
                {2, 0, 1, 4},
                {0, 3, 4, 1},
                {1, 4, 3, 2}
        };
    }

    public static int[][] grille_sudoku22_incomplete_fils2() {
        return new int[][]{
                {4, 1, 2, 3},
                {3, 0, 1, 4},
                {0, 3, 4, 1},
                {1, 4, 3, 2}
        };
    }

    static int[][] grille_sudoku22_gagnante() {
        return new int[][]{
                {4, 1, 2, 3},
                {3, 2, 1, 4},
                {2, 3, 4, 1},
                {1, 4, 3, 2}};
    }

    static int[][] grille_sudoku_nongagnante_ligne() {
        return new int[][]{
                {4, 1, 2, 3},
                {3, 2, 1, 4},
                {2, 3, 4, 2},
                {1, 4, 3, 1}};
    }

    static int[][] grille_sudoku22_nongagnante_colonne() {
        return new int[][]{
                {4, 1, 2, 3},
                {3, 2, 1, 4},
                {2, 3, 4, 1},
                {4, 1, 3, 2}};
    }

    static int[][] grille_sudoku22_nongagnante_carre() {
        return new int[][]{
                {4, 2, 1, 3},
                {3, 2, 1, 4},
                {2, 3, 4, 1},
                {1, 4, 3, 2}};
    }

    
    @Test
    public void test_sudoku22_est_gagnant_vrai() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_gagnante());
        assertTrue(sudoku.estGagnant());
    }

    
    @Test
    public void test_sudoku22_est_gagnant_faux_pbLignes() {
        Sudoku sudoku = new Sudoku(grille_sudoku_nongagnante_ligne());
        assertFalse(sudoku.estGagnant());
    }

    
    @Test
    public void test_verifier_ligne_renvoie_vrai_ligne_valide() {
        Sudoku sudoku = new Sudoku(grille_sudoku_nongagnante_ligne());
        assertTrue(sudoku.verifierLigne(0));
    }

    
    @Test
    public void test_verifier_ligne_renvoie_faux_ligne_non_valide() {
        Sudoku sudoku = new Sudoku(grille_sudoku_nongagnante_ligne());
        assertFalse(sudoku.verifierLigne(2));
    }

    
    @Test
    public void test_verifier_ligne_renvoie_faux_ligne_valide_mais_avec_zeros() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierLigne(2));
    }

    
    @Test
    public void test_verifier_ligne_renvoie_faux_ligne_valide_mais_avec_un_zero() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierLigne(1));
    }

    
    @Test
    public void test_verifier_colonne_renvoie_faux_colonne_non_valide() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_nongagnante_colonne());
        assertFalse(sudoku.verifierColonne(0));
    }

    
    @Test
    public void test_verifier_colonne_renvoie_vrai_colonne_valide() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_nongagnante_colonne());
        assertTrue(sudoku.verifierColonne(3));
    }

    
    @Test
    public void test_verifier_colonne_renvoie_faux_colonne_valide_mais_avec_zeros() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierColonne(0));
    }

    
    @Test
    public void test_verifier_colonne_renvoie_faux_colonne_valide_mais_avec_un_zero() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierColonne(1));
    }

    
    @Test
    public void test_verifier_sous_carre_renvoie_vrai_car_valide() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_nongagnante_carre());
        assertTrue(sudoku.verifierSousCarre(2, 3));
    }

    
    @Test
    public void test_verifier_sous_carre_renvoie_faux_car_invalide() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_nongagnante_carre());
        assertFalse(sudoku.verifierSousCarre(0, 3));
    }


    
    @Test
    public void test_verifier_sous_carre_renvoie_faux_carre_valide_mais_avec_zeros() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierSousCarre(0, 0));
    }

    
    @Test
    public void test_verifier_sous_carre_renvoie_faux_carre_valide_mais_avec_un_zero() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        assertFalse(sudoku.verifierSousCarre(3, 0));
    }

    
    @Test
    public void test_verifier_debCarre() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_gagnante());
        int[] coordDeb = {2, 0};
        int[] ans = sudoku.getDebCarre(3, 1);
        assertTrue(coordDeb[0]==ans[0] && coordDeb[1]==ans[1]);
    }

    
    @Test
    public void test_trouverTrou() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        int[] coordTrou = {1, 0};
        assertArrayEquals(coordTrou, sudoku.getTrou());
    }

    
    @Test
    public void test_trouverTrou_renvoie_null_si_pas_de_trou() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_gagnante());
        assertNull(sudoku.getTrou());
    }

    
    @Test
    public void test_valeurs_possibles_trou_non_evident() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        ArrayList<Integer> res = new ArrayList<>();
        res.add(2); res.add(3);
        ArrayList<Integer> actual = sudoku.getValPoss(1, 0);
        assertEquals(res, actual);
    }

    
    @Test
    public void test_valeurs_possibles_trou_evident() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        ArrayList<Integer> res = new ArrayList<>();
        res.add(2);
        ArrayList<Integer> actual = sudoku.getValPoss(1, 1);
        assertEquals(res, actual);
    }

    
    @Test
    public void test_valeurs_possibles_case_remplie() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        ArrayList<Integer> actual = sudoku.getValPoss(3, 3);
        assertEquals(new ArrayList<>(), actual);
    }

    
    @Test
    public void test_generer_fils_pas_de_trou() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_gagnante());
        assertEquals(new ArrayList<Sudoku>(), sudoku.genererFils());
    }

    
    @Test
    public void test_generer_fils_trou_2_fils_2x2() {
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete2());
        Sudoku fils1 = new Sudoku(grille_sudoku22_incomplete_fils1());
        Sudoku fils2 = new Sudoku(grille_sudoku22_incomplete_fils2());

        ArrayList<Sudoku> res = new ArrayList<>();
        res.add(fils1); res.add(fils2);

        assertEquals(res, sudoku.genererFils());
    }

    
    @Test
    public void test_generer_fils_jusqua_config_gagnante_2x2() {
        Sudoku configGagnante = new Sudoku(grille_sudoku22_gagnante());
        Sudoku sudoku = new Sudoku(grille_sudoku22_incomplete_fils2());
        ArrayList<Sudoku> fils = sudoku.genererFils();
        ArrayList<Sudoku> dernier = fils.get(0).genererFils();

        assertEquals(configGagnante, dernier.get(0));
    }

    
    @Test
    public void test_generer_fils_max_config_fils_possibles_3x3() {
        int[][] g = new int[9][9];
        Sudoku sudoku = new Sudoku(g);
        ArrayList<Sudoku> fils = sudoku.genererFils();
        assertEquals(9, fils.size());
    }

}
