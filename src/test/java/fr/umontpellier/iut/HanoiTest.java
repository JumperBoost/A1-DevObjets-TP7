package fr.umontpellier.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HanoiTest {

    private Hanoi hanoi;

    @BeforeEach
    public void init() {
        hanoi = new Hanoi(3);
    }
    
    @Test
    public void test_hanoi_init_taille() {
        ArrayList<Integer> tour1 = new ArrayList<>(List.of(3, 2, 1));
        assertEquals(hanoi.getTour1(), tour1);
    }
    
    @Test
    void test_constructeur1() {
        ArrayList<Integer> resTour1 = new ArrayList<>();
        resTour1.add(3);
        resTour1.add(2);
        resTour1.add(1);
        assertTrue(resTour1.equals(hanoi.getTour1()) && hanoi.getTour2().isEmpty() && hanoi.getTour3().isEmpty());
    }

    @Test
    void test_constructeur2() {
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        ArrayList<Integer> tour3 = new ArrayList<>();
        tour1.add(4); tour1.add(3);
        tour2.add(2);
        tour3.add(1);
        hanoi = new Hanoi(tour1, tour2, tour3, 4);
        assertTrue(hanoi.getTour1().equals(tour1) && hanoi.getTour2().equals(tour2) && hanoi.getTour3().equals(tour3));
    }

    @Test
    void test_generer_fils_config_initiale() {
        ArrayList<Integer> tour1CommunAuxFils = new ArrayList<>();
        ArrayList<Integer> tour2Fils1 = new ArrayList<>();
        ArrayList<Integer> tour3Fils2 = new ArrayList<>();
        tour1CommunAuxFils.add(3);tour1CommunAuxFils.add(2);
        tour2Fils1.add(1);
        tour3Fils2.add(1);

        Hanoi hanoiFils1 = new Hanoi(tour1CommunAuxFils, tour2Fils1, new ArrayList<>(), 3);
        Hanoi hanoiFils2 = new Hanoi(tour1CommunAuxFils, new ArrayList<>(), tour3Fils2, 3);

        ArrayList<Hanoi> res = hanoi.genererFils();
        assertTrue(res.size()==2 && res.contains(hanoiFils1) && res.contains(hanoiFils2));
    }

    @Test
    void test_generer_fils_config_3_deplacements_possibles() {
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        ArrayList<Integer> tour3 = new ArrayList<>();
        tour1.add(3);
        tour2.add(2);
        tour3.add(1);
        hanoi = new Hanoi(tour1, tour2, tour3, 3);

        ArrayList<Integer> tour1Fils1 = new ArrayList<>();
        ArrayList<Integer> tour1Fils2 = new ArrayList<>();
        ArrayList<Integer> tour2Fils3 = new ArrayList<>();
        tour1Fils1.add(3);tour1Fils1.add(2);
        tour1Fils2.add(3);tour1Fils2.add(1);
        tour2Fils3.add(2);tour2Fils3.add(1);

        Hanoi fils1 = new Hanoi(tour1Fils1, new ArrayList<>(), hanoi.getTour3(), 3);
        Hanoi fils2 = new Hanoi(tour1Fils2, hanoi.getTour2(), new ArrayList<>(), 3);
        Hanoi fils3 = new Hanoi(hanoi.getTour1(), tour2Fils3, new ArrayList<>(), 3);
        ArrayList<Hanoi> res = hanoi.genererFils();
        assertTrue(res.size()==3 && res.contains(fils1) && res.contains(fils2) && res.contains(fils3));
    }

    
    @Test
    void test_est_un_deplacement_valide_renvoie_true_pour_source_non_vide_et_destination_vide() {
        assertTrue(hanoi.estUnDeplacementValide(hanoi.getTour1(), hanoi.getTour2()));
    }

    
    @Test
    void test_est_un_deplacement_valide_renvoie_false_pour_source_vide() {
        assertFalse(hanoi.estUnDeplacementValide(hanoi.getTour2(), hanoi.getTour1()));
    }

    
    @Test
    void test_est_un_deplacement_valide_renvoie_true_pour_source_non_vide_et_destination_avec_disque_superieur() {
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        tour1.add(2); tour1.add(1);
        tour2.add(3);
        hanoi = new Hanoi(tour1, tour2, new ArrayList<>(), 3);
        assertTrue(hanoi.estUnDeplacementValide(hanoi.getTour1(), hanoi.getTour2()));
    }

    
    @Test
    void test_est_un_deplacement_valide_renvoie_false_pour_source_non_vide_et_destination_avec_disque_inferieur() {
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        tour1.add(3); tour1.add(2);
        tour2.add(1);
        hanoi = new Hanoi(tour1, tour2, new ArrayList<>(), 3);
        assertFalse(hanoi.estUnDeplacementValide(hanoi.getTour1(), hanoi.getTour2()));
    }

    @Test
    void test_deplacer_disques_une_fois_source_non_vide() {
        hanoi.deplacerDisque(hanoi.getTour1(), hanoi.getTour2());
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        tour1.add(3); tour1.add(2);
        tour2.add(1);
        assertTrue(hanoi.getTour1().equals(tour1) && hanoi.getTour2().equals(tour2) && hanoi.getTour3().isEmpty());
    }

    
    @Test
    void test_deplacer_disques_deux_fois_source_non_vide() {
        hanoi.deplacerDisque(hanoi.getTour1(), hanoi.getTour2());
        hanoi.deplacerDisque(hanoi.getTour1(), hanoi.getTour3());
        ArrayList<Integer> tour1 = new ArrayList<>();
        ArrayList<Integer> tour2 = new ArrayList<>();
        ArrayList<Integer> tour3 = new ArrayList<>();
        tour1.add(3);
        tour2.add(1);
        tour3.add(2);
        assertTrue(hanoi.getTour1().equals(tour1) && hanoi.getTour2().equals(tour2) && hanoi.getTour3().equals(tour3));
    }

    
    @Test
    void test_deplacer_disques_source_vide() {
        hanoi.deplacerDisque(hanoi.getTour2(), hanoi.getTour1());
        ArrayList<Integer> tour1 = new ArrayList<>();
        tour1.add(3); tour1.add(2); tour1.add(1);

        Hanoi res = new Hanoi(tour1, new ArrayList<>(), new ArrayList<>(), 3);
        assertEquals(res, hanoi);
    }

    
    @Test
    void test_est_gagnant_renvoie_true_pour_config_gagnante_taille_3() {
        hanoi = new Hanoi(hanoi.getTour2(), hanoi.getTour2(), hanoi.getTour1(), 3);
        assertTrue(hanoi.estGagnant());
    }

    
    @Test
    void test_est_gagnant_renvoie_true_pour_config_gagnante_taille_5() {
        hanoi = new Hanoi(5);
        hanoi = new Hanoi(hanoi.getTour2(), hanoi.getTour2(), hanoi.getTour1(), 5);
        assertTrue(hanoi.estGagnant());
    }

    
    @Test
    void test_est_gagnant_renvoie_false_pour_config_non_gagnante_taille_3() {
        hanoi = new Hanoi(hanoi.getTour2(), hanoi.getTour1(), hanoi.getTour2(), 3);
        assertFalse(hanoi.estGagnant());
    }

    
    @Test
    void test_est_gagnant_renvoie_false_pour_config_non_gagnante_taille_5() {
        hanoi = new Hanoi(5);
        assertFalse(hanoi.estGagnant());
    }
}