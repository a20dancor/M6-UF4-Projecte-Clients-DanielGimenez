package cat.inspedralbes.m6.uf4.gimdan.clients;

public class Telefon implements ITelefon{
	String telefon;
	String nifClient;
	
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getNifClient() {
		return nifClient;
	}
	public void setNifClient(String nifClient) {
		this.nifClient = nifClient;
	}
	@Override
	public String toString() {
		return "Telefon [telefon=" + telefon + ", nifClient=" + nifClient + "]";
	}

}
