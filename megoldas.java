package emeltinfo2016maj;

import java.io.*;
import java.util.*;

/**
 * Ötszáz
 * 
* @author Klemand
 */
public class EmeltInfo2016maj {

    public static void main(String[] args) throws IOException {
        //1. feladat
        System.out.println("A 1. feladat megoldása");
        System.out.println("A penztar.txt fájl beolvasása \n");
        BufferedReader behozatal;
        behozatal = new BufferedReader(new FileReader("penztar.txt"));
        String elsoSor, sor;
        elsoSor = behozatal.readLine();
        //Ha van UTF-8 azonosító a fájl elején, elhagyjuk a sor elejéről:
        if (elsoSor.charAt(0) == (char) 0xFEFF) {
            elsoSor = elsoSor.substring(1);
        }
        int vasarlo = 1;
        penztar.add(new PenztarTipus(vasarlo, elsoSor));
        while ((sor = behozatal.readLine()) != null) {
            if (sor.equals("F")) {
                vasarlo++;
            } else {
                penztar.add(new PenztarTipus(vasarlo, sor));
            }
        }
        behozatal.close();
        vasarlo--;
        System.out.println("A beolvasás ellenőrzése");
        int i;
        int ssz = 0;
        int hossz = penztar.size();
        for (i = 1; i <= hossz; i++) {
            if (penztar.get(i - 1).vasarlo > ssz) {
                System.out.println("");
                System.out.print(penztar.get(i - 1).vasarlo + ". vásárlás: " + penztar.get(i - 1).arucikk);
                ssz++;
            } else {
                System.out.print(", " + penztar.get(i - 1).arucikk);
            }
        }
        System.out.println("\n");
        //2. feladat
        System.out.println("A 2. feladat megoldása");
        System.out.print("A fizetések száma: " + penztar.get(hossz - 1).vasarlo);
        System.out.println("\n");
        //3. feladat
        System.out.println("A 3. feladat megoldása");
        System.out.print("Az első vásárló: ");
        int db = 0;
        i = 1;
        while (i <= hossz && penztar.get(i - 1).vasarlo == 1) {
            db++;
            i++;
        }
        System.out.println(db + " darab árucikket vásárolt. \n");
        System.out.println("A további feladatok előkészítése: a vasarlas tömblista elkészítése \n");
        int j;
        for (i = 1; i <= hossz; i++) {
            j = 1;
            while ((j <= vasarlas.size())
                    && ((penztar.get(i - 1).vasarlo != vasarlas.get(j - 1).vasarlo)
                    || !(penztar.get(i - 1).arucikk.equals(vasarlas.get(j - 1).arucikk)))) {
                j++;
            }
            if (j <= vasarlas.size()) {
                vasarlas.get(j - 1).darab++;
            } else {
                vasarlas.add(new VasarlasTipus(penztar.get(i - 1).vasarlo, penztar.get(i - 1).arucikk, 1));
            }
        }
        System.out.println("A vasarlas tömblista ellenőrzése");
        ssz = 0;
        for (i = 1; i <= vasarlas.size(); i++) {
            if (vasarlas.get(i - 1).vasarlo > ssz) {
                System.out.println("");
                System.out.print(vasarlas.get(i - 1).vasarlo + ". vásárlás: " + vasarlas.get(i - 1).darab
                        + " db " + vasarlas.get(i - 1).arucikk);
                ssz++;
            } else {
                System.out.print(", " + vasarlas.get(i - 1).darab + " " + vasarlas.get(i - 1).arucikk);
            }
        }
        System.out.println("\n");
        //4. feladat
        System.out.println("A 4. feladat megoldása");
        System.out.println("Paraméterek bekérése");
        System.out.print("Adja meg a vásárlás sorszámát! ");
        int vasarlasDb = penztar.get(hossz - 1).vasarlo;
        int sorszam = egeszBevitel(1, vasarlasDb);
        System.out.print("Adja meg az árucikk nevét! ");
        String arucikk = szovegBevitel();
        System.out.print("Adja meg a vásárolt darabszámot! ");
        int darabszam = egeszBevitel(1, 20);
        System.out.println("");
        //5. feladat
        System.out.println("Az 5. feladat megoldása");
        System.out.print("A bekért árucikkből az első vásárlás sorszáma: ");
        i = 1;//Kiválasztás, tudjuk, hogy létezik ilyen árucikk
        while (i <= vasarlas.size() && !(vasarlas.get(i - 1).arucikk.equals(arucikk))) {
            i++;
        }
        System.out.println(vasarlas.get(i - 1).vasarlo);
        System.out.print("Az utolsó vásárlás sorszáma: ");
        i = vasarlas.size();//Kiválasztás, tudjuk, hogy létezik ilyen árucikk
        while (i >= 1 && !(vasarlas.get(i - 1).arucikk.equals(arucikk))) {
            i--;
        }
        System.out.println(vasarlas.get(i - 1).vasarlo);
        db = 0;
        for (i = 1; i <= vasarlas.size(); i++) {
            if (vasarlas.get(i - 1).arucikk.equals(arucikk)) {
                db++;
            }
        }
        System.out.println(db + " vásárlás során vettek belőle.");
        System.out.println("");
        //6. feladat
        System.out.println("A 6. feladat megoldása");
        System.out.println(darabszam + " darab vételekor fizetendő: " + ertek(darabszam));
        System.out.println("");
        //7. feladat
        System.out.println("A 7. feladat megoldása");
        System.out.println("A bekért vásárlás adatai: ");
        for (i = 1; i <= vasarlas.size(); i++) {
            if (vasarlas.get(i - 1).vasarlo == sorszam) {
                System.out.println(vasarlas.get(i - 1).darab + " " + vasarlas.get(i - 1).arucikk);
            }
        }
        System.out.println("");
        //8. feladat
        System.out.println("A 8. feladat megoldása");
        System.out.println("A vásárlások alkalmával fizetendő összegek kiírása az osszeg.txt fájlba. ");
        PrintWriter kivitel;
        kivitel = new PrintWriter(new FileWriter("osszeg.txt"));
        int osszeg = ertek(vasarlas.get(0).darab);
        for (i = 2; i <= vasarlas.size(); i++) {
            if (vasarlas.get(i - 1).vasarlo == vasarlas.get(i - 2).vasarlo) {
                osszeg += ertek(vasarlas.get(i - 1).darab);
            } else {
                kivitel.println(vasarlas.get(i - 2).vasarlo + ": " + osszeg);
                osszeg = ertek(vasarlas.get(i - 1).darab);
            }
        }
        kivitel.println(vasarlas.get(vasarlas.size() - 1).vasarlo + ": " + osszeg);
        kivitel.close();
        System.out.println("");
        System.out.println("A fájlkiírás befejeződött. \n");
    }
    //************************************************************

    static ArrayList<PenztarTipus> penztar = new ArrayList<>();

    public static class PenztarTipus {

        private final int vasarlo;
        private final String arucikk;

        public PenztarTipus(int vasarloBe, String sor) {
            vasarlo = vasarloBe;
            arucikk = sor;
        }
    }
    static ArrayList<VasarlasTipus> vasarlas = new ArrayList<>();

    public static class VasarlasTipus {

        private final int vasarlo;
        private final String arucikk;
        private int darab;

        public VasarlasTipus(int vasarloBe, String arucikkBe, int darabBe) {
            vasarlo = vasarloBe;
            arucikk = arucikkBe;
            darab = darabBe;
        }
    }

    public static int egeszBevitel(int MIN, int MAX) {
        int x = -1000000000;
        do {
            System.out.print("Kérem a számot! (" + MIN + " <= beírt szám <= " + MAX + ") :");
            try {
                Scanner szamBill = new Scanner(System.in);
                x = szamBill.nextInt();
            } catch (Exception e) {
                System.out.println("Egész számot kérek!");
            }
        } while ((x < MIN) || (x > MAX));
        return x;
    }

    public static String szovegBevitel() throws IOException {
        BufferedReader bill;
        bill = new BufferedReader(new InputStreamReader(System.in));
        return bill.readLine().trim();
        //Eltávolítjuk a szöveg széleiről az esetleges szóközöket
    }

    public static int ertek(int db) {
        switch (db) {
            case 1:
                return 500;
            case 2:
                return 950;
            case 3:
                return 1350;
            default:
                return 1350 + (db - 3) * 400;
        }
    }
}
