package fr.umontpellier.iut;

public class AppJeuxPuzzle {
    public static void main(String[] args) {
        int[][] tableau = {{5, 0, 2}, {1, 4, 3}, {7, 8, 6}};
        JeuPuzzle puzzle_taquin = new Taquin(tableau);
        Contexte contexte_taquin = new Contexte(puzzle_taquin);
        contexte_taquin.resoudre();
        for(JeuPuzzle puzzle : contexte_taquin.getSolution())
            System.out.println(puzzle);

        JeuPuzzle puzzle_hanoi = new Hanoi(3);
        Contexte contexte_hanoi = new Contexte(puzzle_hanoi);
        contexte_hanoi.resoudre();
        for(JeuPuzzle puzzle : contexte_hanoi.getSolution())
            System.out.println(puzzle);
    }
}
