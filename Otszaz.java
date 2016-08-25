/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otszaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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
        ArrayList<String> desk = new ArrayList<String>();
        String line; // Ameddig nem tettem bele lokális változóba, addig futás során hibákat dobált és nem is számolta jól össze. Miért

        while ((line = bufferedReader.readLine()) != null) {
            desk.add(line);
        }

        System.out.println("1. feladat");
        System.out.println("Megvásárolt árucikkek: " + desk.size());

        // 2. Határozza meg, hogy hányszor fizettek a pénztárnál! 
        int payments = 0;

        for (int i = 0; i < desk.size(); i++) {
            if (desk.get(i).equals("F")) {
                payments++;
            }
        }

        System.out.println("2. feladat");
        System.out.println("A fizetések száma: " + payments);

        // 3. Írja a képernyőre, hogy az első vásárlónak hány darab árucikk volt a kosarában!
        int firstCustomer = 0;

        for (int i = 0; i < desk.size(); i++) {
            if (desk.get(i).equals("F")) {
                break;
            } else {
                firstCustomer++;
            }
        }

        System.out.println("3. feladat");
        System.out.println("Az első vásárló " + firstCustomer + " darab árucikket vásárolt");

        // 4. Kérje be a felhasználótól egy vásárlás sorszámát, egy árucikk nevét és egy darabszámot! A következő három feladat megoldásánál ezeket használja fel! Feltételezheti, hogy a program futtasásakor csak a bemeneti állományban rögzített adatoknak megfelelő vásárlási sorszámot és árucikknevet ad meg a felhasználó. 
        System.out.println("4. feladat");
        String orderNumber = getInput("Adja meg egy vásárlás sorszámát! ");
        String productName = getInput("Adja meg egy árucikk nevét! ");
        String quantity = getInput("Adja meg a vásárolt darabszámot! ");

        // 5. Határozza meg, hogy a bekért árucikkből 
        // a. melyik vásárláskor vettek először, és melyiknél utoljára!  
        // b. összesen hány alkalommal vásároltak! 
        System.out.println("5. feladat");
        // ITT TARTOTTAM!
    }

    public static class Vásárlás {

        private final int vasarlo;
        private final ArrayList<String> kosar;

        public Vásárlás(int vasarlo, ArrayList<String> kosar) {
            this.vasarlo = vasarlo;
            this.kosar = kosar;
        }
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
