package bibliotekaProjekat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Biblioteka {
	ArrayList<Racun> listaRacuna = new ArrayList<>();
	ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
	Path knjigePath = Paths.get("knjige.txt");
	Path racuniPath = Paths.get("korisnici.txt");

	public void upisiKnjigu() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(knjigePath);
		for (Knjiga k : listaKnjiga) {
			writer.write(
					k.getBrojKnjige() + "," + k.getImeKnjige() + "," + k.isStatus() + "," + k.getDatumPosudjivanja());
			writer.newLine();
		}
		writer.flush();
	}

	public void ucitajKnjige() throws IOException, ParseException {
		Scanner reader = new Scanner(knjigePath);
		listaKnjiga.clear();
		reader.useDelimiter(",");
		String linija = "";
		while((reader.hasNextLine())) {
			linija = reader.nextLine();
			Scanner ls = new Scanner(linija);
			ls.useDelimiter(",");
			int brojKnjige = ls.nextInt();
			String imeKnjige = ls.next();
			boolean status = ls.nextBoolean();
			String datum = ls.next();
			if (status) {
				SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				Date date = formatter.parse(datum);
				Knjiga k = new Knjiga(brojKnjige, imeKnjige, status, date);
				listaKnjiga.add(k);
			}else {
				Knjiga k = new Knjiga(brojKnjige, imeKnjige, status);
				listaKnjiga.add(k);
			}
			ls.close();
		}
		
		reader.close();
		
	}

	public void upisiKorisnika() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(racuniPath);
		for (Racun r : listaRacuna) {
			writer.write(r.getBrojRacuna() + " " + r.getIme() + " " + r.getBrojKnjiga());
			writer.newLine();
		}
		writer.flush();
	}

	public void ucitajKorisnike() throws IOException {
		Scanner reader = new Scanner(racuniPath);
		listaRacuna.clear();
		while (reader.hasNext()) {
			int brojRacuna = reader.nextInt();
			String ime = reader.next();
			while (reader.hasNext("[a-zA-Z']+")) {
				ime += " " + reader.next();
			}
			int brojKnjiga = reader.nextInt();
			Racun r = new Racun(brojRacuna, ime, brojKnjiga);

			listaRacuna.add(r);
		}

		reader.close();
	}

	public void kreirajRacun(int brojRacuna, String imeKorisnika) throws IOException {
		Racun racun = new Racun(brojRacuna, imeKorisnika);
		boolean validanRacun = true;
		for (Racun e : listaRacuna) {
			if (e.getBrojRacuna() == brojRacuna) {
				validanRacun = false;
				System.out.println("Racun sa brojem " + brojRacuna + " vec postoji.");
			}
		}
		if (brojRacuna < 0) {
			validanRacun = false;
			System.out.println("Nije moguce kreirati racun sa negativnim brojem.");
		}
		if (validanRacun) {
			listaRacuna.add(racun);
			upisiKorisnika();
			System.out.println("Uspijesno ste kreirali racun.");
		}
	}

	public void kreirajKnjigu(int brojKnjige, String imeKnjige) throws IOException {
		Knjiga knjiga = new Knjiga(brojKnjige, imeKnjige);
		boolean validnaKnjiga = true;
		for (Knjiga e : listaKnjiga) {
			if (e.getBrojKnjige() == brojKnjige) {
				validnaKnjiga = false;
				System.out.println("Knjiga sa brojem " + brojKnjige + " vec postoji.");
			}
		}
		if (brojKnjige < 0) {
			validnaKnjiga = false;
			System.out.println("Nije moguce kreirati knjigu sa negativnim brojem.");
		}
		if (validnaKnjiga) {
			listaKnjiga.add(knjiga);
			upisiKnjigu();
			System.out.println("Uspjesno ste kreirali knjigu.");
		}
	}

	public void podigniKnjigu(int brojRacuna, int brojKnjige) throws IOException {
		boolean validnoPodizanje = false;
		boolean validanRacun = false;
		int index = 0;
		for (Knjiga e : listaKnjiga) {
			if (e.getBrojKnjige() == brojKnjige) {
				validnoPodizanje = true;
				if (e.isStatus()) {
					validnoPodizanje = false;
					System.out.println("Knjiga je vec izdata.");
				}
				for (Racun r : listaRacuna) {
					if (r.getBrojRacuna() == brojRacuna && r.getBrojKnjiga() < 3) {
						validanRacun = true;
					}
				}
			}
		}
		if (validanRacun && validnoPodizanje) {
			java.util.Date datumPosudjivanja = new java.util.Date();
			for (Knjiga e : listaKnjiga) {
				if (e.getBrojKnjige() == brojKnjige) {
					e.setStatus(true);
					e.setDatumPosudjivanja(datumPosudjivanja);
				}
			}
			for (Racun e : listaRacuna) {
				if (e.getBrojRacuna() == brojRacuna) {
					e.setBrojKnjiga(e.getBrojKnjiga() + 1);
					index = listaRacuna.indexOf(e);
				}
			}
			System.out.println("Knjiga uspijesno izdata korisniku " + listaRacuna.get(index).getIme());
			upisiKnjigu();
			upisiKorisnika();
		} else {
			System.out.println("Knjiga se ne može izdati.");
		}
	}

	public void vratiKnjigu(int brojRacuna, int brojKnjige) throws IOException {
		boolean validnoVracanje = false;
		boolean validanRacun = false;

		for (Knjiga e : listaKnjiga) {
			if (e.getBrojKnjige() == brojKnjige) {
				validnoVracanje = true;
				if (!e.isStatus()) {
					validnoVracanje = false;
					System.out.println("Knjiga nije izdata korisniku.");
				}
				for (Racun r : listaRacuna) {
					if (r.getBrojRacuna() == brojRacuna) {
						validanRacun = true;
					}
				}
			}
		}
		if (validanRacun && validnoVracanje) {
			for (Knjiga e : listaKnjiga) {
				if (e.getBrojKnjige() == brojKnjige) {
					e.setStatus(false);
				}
			}
			for (Racun e : listaRacuna) {
				if (e.getBrojRacuna() == brojRacuna) {
					e.setBrojKnjiga(e.getBrojKnjiga() - 1);
				}
			}
			System.out.println("Knjiga uspijesno vracena");
			upisiKnjigu();
			upisiKorisnika();
		} else {
			System.out.println("Pogresan unos.");
		}
	}

	public void ispisDetaljaORacunima() {
		for (Racun e : listaRacuna) {
			System.out.println("Broj racuna: " + e.getBrojRacuna());
			System.out.println("Ime: " + e.getIme());
			System.out.println("Broj knjiga: " + e.getBrojKnjiga());
			System.out.println();
		}
	}

	public void ispisDetaljaOKnjigama() {
		for (Knjiga e : listaKnjiga) {
			System.out.println("Broj knjige: " + e.getBrojKnjige());
			System.out.println("Ime knjige: " + e.getImeKnjige());
			if (e.isStatus()) {
				System.out.println("Knjiga izdata " + e.getDatumPosudjivanja());
			} else {
				System.out.println("Knjiga nije izdata.");
			}
			System.out.println();
		}
	}
}
