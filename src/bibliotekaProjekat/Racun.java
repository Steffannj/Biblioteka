package bibliotekaProjekat;

public class Racun {
	private int brojRacuna;
	private String ime;
	private int brojKnjiga;

	Racun() {

	}

	public Racun(int brojRacuna, String ime) {
		this.brojRacuna = brojRacuna;
		this.ime = ime;
	}

	public Racun(int brojRacuna, String ime, int brojKnjiga) {
		this.brojRacuna = brojRacuna;
		this.ime = ime;
		this.brojKnjiga = brojKnjiga;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getBrojKnjiga() {
		return brojKnjiga;
	}

	public void setBrojKnjiga(int brojKnjiga) {
		this.brojKnjiga = brojKnjiga;
	}

}
