/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otszaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Kovács Andor
 */
public class Otszaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        // 1. Olvassa be és tárolja el a penztar.txt fájl tartalmát!
        BufferedReader bufferedReader = new BufferedReader(new FileReader("penztar.txt"));
        ArrayList<String> penztar = new ArrayList<String>();
        String sor; // Miért kell beletenni változóba?

        while ((sor = bufferedReader.readLine()) != null) {
            penztar.add(sor);
        }

        // 1. Tételek összegyűjtése
        int vasarlas = 1;
        ArrayList<Tetel> tetelek = new ArrayList<>();

        for (int i = 0; i < penztar.size(); i++) {
            if (penztar.get(i).equals("F")) {
                vasarlas++;
            } else {
                boolean tetel = true;
                for (int j = 0; j < tetelek.size(); j++) {
                    if (tetelek.get(j).termek.equals(penztar.get(i)) && tetelek.get(j).vasarlas == vasarlas) {
                        tetelek.get(j).darab++;
                        tetel = false;
                    }
                }
                if (tetel) {
                    tetelek.add(new Tetel(vasarlas, penztar.get(i), 1));
                }
            }
        }

        // 2. Határozza meg, hogy hányszor fizettek a pénztárnál!
        System.out.println("2. feladat");
        System.out.println("A fizetések száma: " + tetelek.get(tetelek.size() - 1).vasarlas);

        // 3. Írja a képernyőre, hogy az első vásárlónak hány darab árucikk volt a kosarában! 
        int hanyadikVasarlo = 2;
        int hanyDarabTermek = 0;
        for (int i = 0; i < tetelek.size(); i++) {
            if (tetelek.get(i).vasarlas == hanyadikVasarlo) {
                hanyDarabTermek = hanyDarabTermek + tetelek.get(i).darab;
            }
        }

        System.out.println("3. feladat");
        System.out.println("A(z) " + hanyadikVasarlo + ". vásárló " + hanyDarabTermek + " darab terméket vásárolt.");

        // 4. Kérje be a felhasználótól egy vásárlás sorszámát, egy árucikk nevét és egy darabszámot! A következő három feladat megoldásánál ezeket használja fel!  
        System.out.println("4. feladat");
        int vasarlasSorszam = Integer.parseInt(getInput("Adja meg egy vásárlás sorszámát! "));
        String arucikkNeve = getInput("Adja meg egy árucikk nevét! ");
        int darabSzam = Integer.parseInt(getInput("Adja meg egy vásárlás sorszámát! "));

        // 5. Határozza meg, hogy a bekért árucikkből a. melyik vásárláskor vettek először, és melyiknél utoljára! b. összesen hány alkalommal vásároltak! 
        int elsoVasarlas = 0;
        int utolsoVasarlas = 0;
        int hanyszorVasaroltak = 0;

        for (int i = 0; i < tetelek.size(); i++) {
            if (tetelek.get(i).termek.equals(arucikkNeve)) {
                elsoVasarlas = tetelek.get(i).vasarlas;
                break;
            }
        }

        for (int i = tetelek.size() - 1; i > 0; i--) {
            if (tetelek.get(i).termek.equals(arucikkNeve)) {
                utolsoVasarlas = tetelek.get(i).vasarlas;
                break;
            }
        }

        for (int i = 0; i < tetelek.size(); i++) {
            if (tetelek.get(i).termek.equals(arucikkNeve)) {
                hanyszorVasaroltak++;
            }
        }

        System.out.println("5. feladat");
        System.out.println("Első '" + arucikkNeve + "' vásárlás: " + elsoVasarlas);
        System.out.println("Utolsó '" + arucikkNeve + "' vásárlás: " + utolsoVasarlas);
        System.out.println("Ennyi '" + arucikkNeve + "' vásárlás történt: " + hanyszorVasaroltak);

        // 6. Határozza meg, hogy a bekért darabszámot vásárolva egy termékből mennyi a fizetendő összeg! A feladat megoldásához készítsen függvényt ertek néven, amely a darabszámhoz a fizetendő összeget rendeli! 
        System.out.println("6. feladat");
        System.out.println(darabSzam + " darab terméket vásárolva " + ertek(darabSzam) + " Ft-ot kell fizetni.");

        // 7. Határozza meg, hogy a bekért sorszámú vásárláskor mely árucikkekből és milyen mennyiségben vásároltak! Az árucikkek nevét tetszőleges sorrendben megjelenítheti. 
        System.out.println("7. feladat");
        System.out.println("A(z) " + vasarlasSorszam + " számú vásárlás tételei:");
        for (int i = 0; i < tetelek.size(); i++) {
            if (tetelek.get(i).vasarlas == vasarlasSorszam) {
                System.out.println(tetelek.get(i).vasarlas + ". vásárlás: " + tetelek.get(i).termek + ", " + tetelek.get(i).darab + " db");
            }
        }

        // 8. Készítse el az osszeg.txt fájlt, amelybe soronként az egy-egy vásárlás alkalmával fizetendő összeg kerüljön a kimeneti mintának  megfelelően!
        PrintWriter output = new PrintWriter(new FileWriter("osszeg.txt"));

        hanyadikVasarlo = 1;
        hanyDarabTermek = 0;
        for (int i = 0; i < tetelek.size(); i++) {
            if (tetelek.get(i).vasarlas == hanyadikVasarlo) {
                hanyDarabTermek = hanyDarabTermek + tetelek.get(i).darab;
            } else {
                output.println(hanyadikVasarlo + ": " + ertek(hanyDarabTermek));
                hanyadikVasarlo++;
                hanyDarabTermek = tetelek.get(i).darab;
            }
        }
        output.close();

    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static int ertek(int darabSzam) {
        int osszeg = 0;
        if (darabSzam <= 0) {
            osszeg = 0;
        }
        if (darabSzam == 1) {
            return 500;
        }
        if (darabSzam == 2) {
            return 950;
        }
        if (darabSzam > 2) {
            return 400 * (darabSzam - 2) + 950;
        }
        return osszeg;
    }

    public static class Tetel {

        private int vasarlas;
        private String termek;
        private int darab;

        Tetel(int vasarlas, String termek, int darab) {
            this.vasarlas = vasarlas;
            this.termek = termek;
            this.darab = darab;
        }
    }

}
