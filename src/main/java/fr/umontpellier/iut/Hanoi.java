package fr.umontpellier.iut;

import java.util.ArrayList;

public class Hanoi implements JeuPuzzle {
    private final ArrayList<Integer> tour1;
    private final ArrayList<Integer> tour2;
    private final ArrayList<Integer> tour3;
    private final int taille;

    public Hanoi(int taille) {
        /* crée un hanoi avec la configuration suivante :
                * sur la tour 1 les disques [taille,taille-1, .., 1]
                * rien sur les tour 2 et 3 (elles sont vides)

         */
        tour1 = new ArrayList<>();
        tour2 = new ArrayList<>();
        tour3 = new ArrayList<>();
        for(int i = taille; i > 0; i--)
            tour1.add(i);
        this.taille = taille;
    }


    public Hanoi(ArrayList<Integer> tour1, ArrayList<Integer> tour2, ArrayList<Integer> tour3, int taille) {
        /*
        Crée un hanoi où la tour 1 (resp. tour 2 et tour 3) contient les entiers de tour1 (resp. tour2 et tour3). Par exemple,
        si tour1 est une ArrayList contenant [3,2,1], et tour2 et tour3 sont des ArrayList vides, alors
        Hanoi(tour1, tour2, tour3) doit créer la même configuration que Hanoi(3).
         */
        this.tour1 = new ArrayList<>(tour1);
        this.tour2 = new ArrayList<>(tour2);
        this.tour3 = new ArrayList<>(tour3);
        this.taille = taille;
    }


    @Override
    public boolean estGagnant() {
        return tour1.isEmpty() && tour2.isEmpty() && tour3.size() == taille;
    }

    @Override
    public ArrayList<Hanoi> genererFils() {
        ArrayList<Hanoi> fils = new ArrayList<>();
        // Tour 1
        if(!tour1.isEmpty()) {
            if(tour2.isEmpty() || estUnDeplacementValide(tour1, tour2))
                fils.add(deplacerDisque(1, 2));
            if(tour3.isEmpty() || estUnDeplacementValide(tour1, tour3))
                fils.add(deplacerDisque(1, 3));
        }
        // Tour 2
        if(!tour2.isEmpty()) {
            if(tour1.isEmpty() || estUnDeplacementValide(tour2, tour1))
                fils.add(deplacerDisque(2, 1));
            if(tour3.isEmpty() || estUnDeplacementValide(tour2, tour3))
                fils.add(deplacerDisque(2, 3));
        }
        // Tour 3
        if(!tour3.isEmpty()) {
            if(tour1.isEmpty() || estUnDeplacementValide(tour3, tour1))
                fils.add(deplacerDisque(3, 1));
            if(tour2.isEmpty() || estUnDeplacementValide(tour3, tour2))
                fils.add(deplacerDisque(3, 2));
        }
        return fils;
    }

    private Hanoi deplacerDisque(int srcNb, int destNb) {
        Hanoi hanoi = new Hanoi(tour1, tour2, tour3, taille);
        ArrayList<Integer> src = hanoi.getTourNb(srcNb);
        ArrayList<Integer> dest = hanoi.getTourNb(destNb);
        deplacerDisque(src, dest);
        return hanoi;
    }

    public void deplacerDisque(ArrayList<Integer> src, ArrayList<Integer> dest) {
        if(estUnDeplacementValide(src, dest))
            dest.add(src.remove(src.size()-1));
    }

    public boolean estUnDeplacementValide(ArrayList<Integer> src, ArrayList<Integer> dest) {
        if(src.isEmpty())
            return false;
        return dest.isEmpty() || dest.get(dest.size()- 1) > src.get(src.size() - 1);
    }

    public ArrayList<Integer> getTour1() {
        return tour1;
    }

    public ArrayList<Integer> getTour2() {
        return tour2;
    }

    public ArrayList<Integer> getTour3() {
        return tour3;
    }

    private ArrayList<Integer> getTourNb(int nb) {
        if(nb == 1)
            return tour1;
        if(nb == 2)
            return tour2;
        if(nb == 3)
            return tour3;
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        String affichageDisque;
        for(int i = taille; i > 0; i--) {
            for(int j = 1; j <= 3; j++) {
                affichageDisque = i <= getTourNb(j).size() ? "-".repeat(getTourNb(j).get(i-1)) : " ".repeat(taille-i + 1);
                res += " ".repeat(taille - affichageDisque.length()) + affichageDisque + "|" + affichageDisque + " ".repeat(taille - affichageDisque.length()) + " ";
            }
            res += "\n";
        }
        return res;
    }

    @Override
    public int hashCode() {
        int x = 7;
        x += tour1.hashCode();
        x += tour2.hashCode();
        x += tour3.hashCode();
        x += taille;
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Hanoi))
            return false;
        Hanoi hanoi = (Hanoi) o;
        return hanoi.taille == taille && hanoi.tour1.equals(tour1)
                && hanoi.tour2.equals(tour2)&& hanoi.tour3.equals(tour3);
    }
}
