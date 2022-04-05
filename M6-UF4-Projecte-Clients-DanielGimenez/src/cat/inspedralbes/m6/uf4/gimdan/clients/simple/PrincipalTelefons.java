package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.Iterator;
import java.util.List;

public class PrincipalTelefons {
	public static void main(String[] args) {
		
		IClient clientA = new Client();
		clientA.setNIF("11111111A");
		clientA.setNom("Andreu");
		clientA.addTelefon("911111111");
		
		
		IClient clientB = new Client();
		clientB.setNIF("22222222B");
		clientB.setNom("Bea");
		clientB.addTelefon("921111111");
		clientB.addTelefon("922111111");
		
		ITelefonDAO daoTelefons = new TelefonDAO();
		
		try {
			daoTelefons.obrir();
			
			daoTelefons.create(clientA);
			daoTelefons.create(clientB);
			
			System.out.println("Telefons guardats");
			System.out.println("--------------------------------");
			System.out.println(clientA.getTelefons());
			System.out.println(clientB.getTelefons());
			
			List<String>telefonsA = daoTelefons.read("11111111A");
			List<String>telefonsB  = daoTelefons.read("22222222B");
			
			System.out.println("Telefons llegits");
			System.out.println("--------------------------------");
			System.out.println("Client A");
			for (int i = 0; i < telefonsA.size(); i++) {
				System.out.println(telefonsA.get(i).toString());
			}
			System.out.println("Client B");
			for (int i = 0; i < telefonsB.size(); i++) {
				System.out.println(telefonsB.get(i).toString());
			}
			
			
			System.out.println("Llegir Tots els telefons");
			System.out.println("--------------------------------");
			List<String>totsTelefons= daoTelefons.readAll();
			
			for (int i = 0; i < totsTelefons.size(); i++) {
				System.out.println(totsTelefons.get(i).toString());
			}
			daoTelefons.tancar();
			
			
		}catch (RuntimeException rte) {
			rte.printStackTrace();
		}
		
		
		
	}
}
