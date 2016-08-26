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
import java.util.HashMap;

/**
 *
 * @author Kovács Andor
 */
public class Otszaz {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws IOException {

		// 1. Olvassa be és tárolja el a penztar.txt fájl tartalmát!
		BufferedReader bufferedReader = new BufferedReader(new FileReader("penztar.txt"));
		ArrayList<String> desk = new ArrayList<String>();
		String line;

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

		// 3. Írja a képernyőre, hogy az első vásárlónak hány darab árucikk volt
		// a kosarában!
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

		// 4. Kérje be a felhasználótól egy vásárlás sorszámát, egy árucikk
		// nevét és egy darabszámot! A következő három feladat megoldásánál
		// ezeket használja fel! Feltételezheti, hogy a program futtasásakor
		// csak a bemeneti állományban rögzített adatoknak megfelelő vásárlási
		// sorszámot és árucikknevet ad meg a felhasználó.
		System.out.println("4. feladat");
		int orderNumber = Integer.parseInt(getInput("Adja meg egy vásárlás sorszámát! "));
		String productName = getInput("Adja meg egy árucikk nevét! ");
		int quantity = Integer.parseInt(getInput("Adja meg a vásárolt darabszámot! "));

		// 5. Határozza meg, hogy a bekért árucikkből
		// a. melyik vásárláskor vettek először, és melyiknél utoljára!
		// b. összesen hány alkalommal vásároltak!
		ArrayList<Vasarlas> vasarlasok = new ArrayList<Vasarlas>();
		int vasarlo = 1;
		ArrayList<String> kosar = new ArrayList<String>();

		for (int i = 0; i < desk.size(); i++) {

			if (desk.get(i).equals("F")) {
				vasarlasok.add(new Vasarlas(vasarlo, kosar));
				vasarlo++;
				kosar.clear();
				continue;
			} else {
				kosar.add(desk.get(i));
			}
		}

		ArrayList<Vasarlas> productNameVasarlasok = new ArrayList<Vasarlas>();
		for (int i = 0; i < vasarlasok.size(); i++) {
			if (vasarlasok.get(i).kosar.contains(productName)) {
				productNameVasarlasok.add(vasarlasok.get(i));
			}
		}

		System.out.println("5. feladat");
		System.out.println(
				"Első " + productName + " vásárlás: " + productNameVasarlasok.get(0).vasarlo + " sz. vásárlás.");
		System.out.println("Utolsó " + productName + " vásárlás: "
				+ productNameVasarlasok.get(productNameVasarlasok.size() - 1).vasarlo + " sz. vásárlás.");
		System.out.println(
				productName + " nevű terméket összesen " + productNameVasarlasok.size() + " alkalommal vásároltak.");

		// 6. Határozza meg, hogy a bekért darabszámot vásárolva egy termékből
		// mennyi a fizetendő összeg! A feladat megoldásához készítsen függvényt
		// ertek néven, amely a darabszámhoz a fizetendő összeget rendeli!
		System.out.println("6. feladat");
		System.out.println("A bekért darabszámot vásárolva egy termékből " + ertek(quantity) + " Ft-ot kell fizetni.");

		// 7. Határozza meg, hogy a bekért sorszámú vásárláskor mely
		// árucikkekből és milyen mennyiségben
		// vásároltak! Az árucikkek nevét tetszőleges sorrendben megjelenítheti.
		System.out.println("7. feladat");
		System.out.println(
				orderNumber + " sz. vásárláskor " + vasarlasok.get(orderNumber - 1).kosar + " volt a kosár tartalma.");

		ArrayList<Kosar> aktualisKosar = new ArrayList<>();
		for (int i = 0; i < vasarlasok.get(orderNumber - 1).kosar.size(); i++) {
			int db = 1;
			aktualisKosar.add(new Kosar(db, vasarlasok.get(orderNumber - 1).kosar.get(i)));
			System.out.println(vasarlasok.get(orderNumber - 1).kosar.get(i));
		}
		for (int i = 0; i < aktualisKosar.size(); i++) {
			System.out.println("Kosár: " + aktualisKosar.get(i).db + " " + aktualisKosar.get(i).termek);
		}

		ArrayList<Vasarlas2> vasarlas2 = new ArrayList<>();
		int vasarlo2 = 1;
		for (int i = 0; i < desk.size(); i++) {
			if (desk.get(i).equals("F")) {
				vasarlo2 = vasarlo2 + 1;
			} else {
				String termek = desk.get(i);
				int darab = 1;
				boolean b = false;
				for (int j = 0; j < vasarlas2.size(); j++) {
					if (vasarlas2.get(j).termek.equals(termek) && vasarlas2.get(j).vasarlo == vasarlo2) {
						vasarlas2.get(j).darab++;
						b = true;
						break; // Nem lenne hibás nélküle, csak felesleges.
					}
				}
				if (b == false ) {
					vasarlas2.add(new Vasarlas2(vasarlo2, termek, darab));
				}
			}
		}
		for (int i = 0; i < vasarlas2.size(); i++) {
			System.out.println(vasarlas2.get(i).vasarlo + ". vásárló: " + vasarlas2.get(i).termek + " - "
					+ vasarlas2.get(i).darab + " db");
		}

		// 8. Készítse el az osszeg.txt fájlt, amelybe soronként az egy-egy
		// vásárlás alkalmával fizetendő összeg kerüljön a kimeneti mintának
		// megfelelően!
		PrintWriter output = new PrintWriter(new FileWriter("osszeg.txt"));
		for (int i = 0; i < payments; i++) {
			output.println((i + 1) + " sz. vásárláskor " + ertek(vasarlasok.get(i).kosar.size()) + " Ft");
		}
		output.close();

	}

	private static int ertek(int quantity) {

		int price = 0;
		if (quantity == 0) {
			price = 0;
		} else if (quantity == 1) {
			price = 500;
		} else if (quantity == 2) {
			price = 950;
		} else if (quantity > 2) {
			price = 950 + ((quantity - 2) * 400);
		}

		return price;
	}

	public static class Vasarlas {

		private final int vasarlo;
		private final ArrayList<String> kosar;

		public Vasarlas(int vasarlo, ArrayList<String> kosar) {
			this.vasarlo = vasarlo;
			this.kosar = new ArrayList<>(kosar);
		}
	}

	public static class Kosar {

		private final int db;
		private final String termek;

		public Kosar(int db, String termek) {
			this.db = db;
			this.termek = termek;
		}
	}

	public static class Vasarlas2 {

		private int vasarlo;
		private String termek;
		private int darab;

		public Vasarlas2(int vasarlo, String termek, int darab) {
			this.vasarlo = vasarlo;
			this.termek = termek;
			this.darab = darab;
		}
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
}
