package fr.umontpellier.iut;

public class AppJeuxPuzzle {
    public static void main(String[] args) {
        long time_start = System.currentTimeMillis();
        int[][] tableau = {{5, 0, 2}, {1, 4, 3}, {7, 8, 6}};
        JeuPuzzle puzzle_taquin = new Taquin(tableau);
        Contexte contexte_taquin = new Contexte(puzzle_taquin);
        contexte_taquin.resoudre();
        long time_end = System.currentTimeMillis();
        for(JeuPuzzle puzzle : contexte_taquin.getSolution())
            System.out.println(puzzle);
        System.out.println("Taquin résolu en " + contexte_taquin.getDistance() + " coup(s) en " + (time_end - time_start) + "ms.");
    }
}
