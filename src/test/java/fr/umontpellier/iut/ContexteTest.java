package fr.umontpellier.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContexteTest {

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {}
        }));
    }

    @Test
    public void test_no_exception() {
        int[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Contexte c = new Contexte(new Taquin(data));
        assertDoesNotThrow(c::resoudre);
    }

    @Test
    public void test_resoudre_3_X_3() {
        assertTimeoutPreemptively(Duration.of(3, ChronoUnit.SECONDS), () -> {
            int[][] data_contexte = {{5, 0, 2}, {1, 4, 3}, {7, 8, 6}};
            Taquin taquin_contexte = new Taquin(data_contexte);
            Contexte contexte = new Contexte(taquin_contexte);
            contexte.resoudre();
            int[][] data_complet = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            Taquin taquin_complet = new Taquin(data_complet);
            ArrayList<JeuPuzzle> taquins = contexte.getSolution();
            assertEquals(taquins.get(taquins.size()-1), taquin_complet);
        });
    }

    @Test
    public void test_resoudre_3_X_4() {
        assertTimeoutPreemptively(Duration.of(3, ChronoUnit.SECONDS), () -> {
            int[][] data_contexte = {{4, 1, 3}, {0, 2, 6}, {7, 5, 9}, {10, 8, 11}};
            Taquin taquin_contexte = new Taquin(data_contexte);
            Contexte contexte = new Contexte(taquin_contexte);
            contexte.resoudre();
            int[][] data_complet = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 0}};
            Taquin taquin_complet = new Taquin(data_complet);
            ArrayList<JeuPuzzle> taquins = contexte.getSolution();
            assertEquals(taquins.get(taquins.size()-1), taquin_complet);
        });
    }
}