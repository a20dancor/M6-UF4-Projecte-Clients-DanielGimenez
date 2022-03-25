package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {

	String nom;
	String nif;
	List<ITelefon> telefons;

	public Client() {
		telefons = new ArrayList<>();
	}

	@Override
	public void addTelefon(ITelefon telefon) {

		if (telefon.getTelefon().length() >= 9) {
			telefons.add(telefon);
		} else {
			throw new RuntimeException("El telefon ha de tenir una mida de 9 caracters minim.");
		}

	}

	@Override
	public void removeTelefon(String telefon) {
		telefons.remove(telefon);

	}

	@Override
	public void setTelefons(List<ITelefon> telefons) {

		if (telefons == null) {
			throw new RuntimeException("La llista de telefons es null");
		} else {
			this.telefons = telefons;
		}

	}

	@Override
	public List<ITelefon> getTelefons() {
		return telefons;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;

	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void setNIF(String nif) {
		if (nif.length() >= 9) {
			this.nif = nif;
		} else {
			throw new RuntimeException("El nif ha de tenir una mida de 9 caracters minim.");
		}

	}

	@Override
	public String getNIF() {
		return nif;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", nif=" + nif + ", telefons=" + telefons + "]";
	}

}
