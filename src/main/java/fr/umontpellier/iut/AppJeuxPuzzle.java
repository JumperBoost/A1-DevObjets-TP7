package fr.umontpellier.iut;

public class AppJeuxPuzzle {
    public static void main(String[] args) {
        int time_start, time_end;

        time_start = (int) System.currentTimeMillis();
        int[][] tableau = {{5, 0, 2}, {1, 4, 3}, {7, 8, 6}};
        JeuPuzzle puzzle_taquin = new Taquin(tableau);
        Contexte contexte_taquin = new Contexte(puzzle_taquin);
        contexte_taquin.resoudre();
        time_end = (int) System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (time_end - time_start) + "ms");
        for(JeuPuzzle puzzle : contexte_taquin.getSolution())
            System.out.println(puzzle);

        time_start = (int) System.currentTimeMillis();
        JeuPuzzle puzzle_hanoi = new Hanoi(3);
        Contexte contexte_hanoi = new Contexte(puzzle_hanoi);
        contexte_hanoi.resoudre();
        time_end = (int) System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (time_end - time_start) + "ms");
        for(JeuPuzzle puzzle : contexte_hanoi.getSolution())
            System.out.println(puzzle);

        time_start = (int) System.currentTimeMillis();
        int[][] taquin_long = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        JeuPuzzle puzzle_taquin_long = new Taquin(taquin_long);
        Contexte contexte_taquin_long = new Contexte(puzzle_taquin_long);
        contexte_taquin_long.resoudre();
        time_end = (int) System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (time_end - time_start) + "ms");
        for(JeuPuzzle puzzle : contexte_taquin_long.getSolution())
            System.out.println(puzzle);
    }
}
