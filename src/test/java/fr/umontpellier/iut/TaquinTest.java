package fr.umontpellier.iut;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaquinTest {
    @Test
    public void test_est_gagnant_faux_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        assertFalse(t.estGagnant());
    }

    @Test
    public void test_est_gagnant_vrai_3_X_3() {
        int[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Taquin t = new Taquin(data);
        assertTrue(t.estGagnant());
    }

    @Test
    public void test_est_gagnant_faux_3_X_4() {
        int[][] data = {{1, 2, 0, 4}, {5, 6, 3, 9}, {7, 8, 11, 10}};
        Taquin t = new Taquin(data);
        assertFalse(t.estGagnant());
    }

    @Test
    public void test_est_gagnant_vrai_3_X_4() {
        int[][] data = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 0}};
        Taquin t = new Taquin(data);
        assertTrue(t.estGagnant());
    }

    @Test
    public void test_trouver_trou_coin_hd_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[] res = t.trouverTrou();
        int[] res2 = {0, 2};
        assertArrayEquals(res2, res);
    }

    @Test
    public void test_trouver_trou_coin_milieu_3_X_3() {
        int[][] data = {{1, 5, 2}, {4, 0, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[] res = t.trouverTrou();
        int[] res2 = {1, 1};
        assertArrayEquals(res, res2);
    }

    @Test
    public void test_trouver_trou_coin_hd_3_X_4() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}, {10, 11, 9}};
        Taquin t = new Taquin(data);
        int[] res = t.trouverTrou();
        int[] res2 = {0, 2};
        assertArrayEquals(res, res2);
    }

    @Test
    public void test_equals_vrai_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t2 = new Taquin(data2);
        assertEquals(t, t2);
    }

    @Test
    public void test_equals_faux_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
        Taquin t2 = new Taquin(data2);
        assertNotEquals(t, t2);
    }

    @Test
    public void test_equals_vrai_3_X_4() {
        int[][] data = {{1, 2, 0, 4}, {5, 6, 3, 9}, {7, 8, 11, 10}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 2, 0, 4}, {5, 6, 3, 9}, {7, 8, 11, 10}};
        Taquin t2 = new Taquin(data2);
        assertEquals(t, t2);
    }

    @Test
    public void test_equals_faux_3_X_4() {
        int[][] data = {{1, 2, 0, 4}, {5, 6, 3, 9}, {7, 8, 11, 10}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 2, 9, 4}, {5, 6, 3, 0}, {7, 8, 11, 10}};
        Taquin t2 = new Taquin(data2);
        assertNotEquals(t, t2);
    }

    @Test
    public void test_hashcode_vrai_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t2 = new Taquin(data2);
        assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void test_generer_Fils_coin_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        ArrayList<Taquin> res = t.genererFils();

        ArrayList<Taquin> res2 = new ArrayList<>();
        int[][] datafils1 = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        Taquin fils1 = new Taquin(datafils1);
        int[][] datafils2 = {{1, 0, 2}, {4, 5, 3}, {7, 8, 6}};
        Taquin fils2 = new Taquin(datafils2);
        res2.add(fils1);
        res2.add(fils2);
        assertTrue(res.containsAll(res2) && res2.containsAll(res));
    }

    @Test
    public void test_hashcode_different_3_X_3() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        int[][] data2 = {{1, 2, 0}, {5, 4, 3}, {7, 8, 6}};
        Taquin t2 = new Taquin(data2);
        assertNotEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void test_hashcode_meme_refTableau_meme_hashcode() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        Taquin t2 = new Taquin(data);
        assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void test_hashcode_meme_tableauRefDifferente_meme_hashcode() {
        int[][] data = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        int[][] data2 = {{1, 2, 0}, {4, 5, 3}, {7, 8, 6}};
        Taquin t = new Taquin(data);
        Taquin t2 = new Taquin(data2);
        assertEquals(t.hashCode(), t2.hashCode());
    }
}