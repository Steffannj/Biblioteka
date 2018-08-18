package bibliotekaProjekat;
import java.util.Date;

public class Knjiga {
	private int brojKnjige;
	private String imeKnjige;
	private boolean status;
	private Date datumPosudjivanja;
	Knjiga() {

	}

	public Knjiga(int brojKnjige, String imeKnjige) {
		this.brojKnjige = brojKnjige;
		this.imeKnjige = imeKnjige;

	}
	public Knjiga(int brojKnjige, String imeKnjige, boolean status) {
		this.brojKnjige = brojKnjige;
		this.imeKnjige = imeKnjige;
		this.status = status;
	}

	public Knjiga(int brojKnjige, String imeKnjige, boolean status, Date datumPosudjivanja) {
		this.brojKnjige = brojKnjige;
		this.imeKnjige = imeKnjige;
		this.status = status;
		this.datumPosudjivanja = datumPosudjivanja;
	}

	public int getBrojKnjige() {
		return brojKnjige;
	}

	public void setBrojKnjige(int brojKnjige) {
		this.brojKnjige = brojKnjige;
	}

	public String getImeKnjige() {
		return imeKnjige;
	}

	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDatumPosudjivanja() {
		return datumPosudjivanja;
	}

	public void setDatumPosudjivanja(Date datumPosudjivanja) {
		this.datumPosudjivanja = datumPosudjivanja;
	}

	@Override
	public String toString() {
		return "Knjiga [brojKnjige=" + brojKnjige + ", imeKnjige=" + imeKnjige + ", status=" + status
				+ ", datumPosudjivanja=" + datumPosudjivanja + "]";
	}

}
