package bibliotekaProjekat;

import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotekaTest {

	public static void main(String[] args) throws IOException, ParseException{
		Scanner unos = new Scanner(System.in);
		Biblioteka b = new Biblioteka();
		b.ucitajKorisnike();
		b.ucitajKnjige();
		int izbor = 0;
	
		
		while (izbor != 7) {
			try {
				System.out.println("Dobrodošli u biblioteku. \nIzaberite jednu od opcija: \n1. Kreiraj racun"
						+ "\n2. Kreiraj knjigu\n3. Podigni knjigu"
						+ "\n4. Vrati knjigu\n5. Ispis detalja postojeæih knjiga"
						+ "\n6. Ispis detalja postojeæih racuna" + "\n7. Izlaz");
				izbor = unos.nextInt();
				switch (izbor) {
				case 1:
					System.out.println("Unesite broj racuna: ");
					int brojRacuna = unos.nextInt();
					unos.nextLine();
					System.out.println("Unesite ime korisnika");
					String ime = unos.nextLine();
					b.kreirajRacun(brojRacuna, ime);
					break;
				case 2:
					System.out.println("Unesite broj knjige: ");
					int brojKnjige = unos.nextInt();
					unos.nextLine();
					System.out.println("Unesite ime knjige: ");
					String imeKnjige = unos.nextLine();
					b.kreirajKnjigu(brojKnjige, imeKnjige);
					break;
				case 3:
					System.out.println("Unesite broj racuna: ");
					brojRacuna = unos.nextInt();
					unos.nextLine();
					System.out.println("Unesite broj knjige");
					brojKnjige = unos.nextInt();
					b.podigniKnjigu(brojRacuna, brojKnjige);
					break;
				case 4:
					System.out.println("Unesite broj racuna: ");
					brojRacuna = unos.nextInt();
					unos.nextLine();
					System.out.println("Unesite broj knjige");
					brojKnjige = unos.nextInt();
					b.vratiKnjigu(brojRacuna, brojKnjige);
					break;
				case 5:
					System.out.println("Detalji o postojeæim knjigama: ");
					b.ispisDetaljaOKnjigama();
					break;
				case 6:
					System.out.println("Detalji o postojeæim racunima: ");
					b.ispisDetaljaORacunima();
					break;
				case 7:
					System.out.println("Hvala na paznji.");
					break;
				default:
					System.out.println("Ne postoji ta opcija.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogrešan unos.");
				unos.nextLine();
			}
		}

		unos.close();
	}

}
