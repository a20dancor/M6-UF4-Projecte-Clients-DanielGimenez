package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {

	String nom;
	String nif;
	List<String>telefons;
	
	public Client() {
		telefons= new ArrayList<>();
	}
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNIF() {
		return nif;
	}

	public void setNIF(String nif) {
		this.nif = nif;
	}
	
	@Override
	public void addTelefon(String telefon) {
		telefons.add(telefon);		
	}

	@Override
	public void removeTelefon(String telefon) {
		telefons.remove(telefon);
	}

	public List<String> getTelefons() {
		return telefons;
	}

	public void setTelefons(List<String> telefons) {
		this.telefons = telefons;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", nif=" + nif + ", telefons=" + telefons + "]";
	}


}
