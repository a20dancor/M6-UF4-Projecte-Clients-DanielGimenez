package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.List;

public class PrincipalClient {

	//Funcio auxiliar de comprovacio de clients. Dos clients son iguals si tenen mateix nif, nom i telèfons.
		private static boolean comparaClients(IClient a, IClient b) {

			// Diferent nif?
			if (!a.getNIF().equals(b.getNIF())) {
				return false;
			}
			// Diferent nom?
			if (!a.getNom().equals(b.getNom())) {
				return false;
			}
			// Iguals telefons?
			if (a.getTelefons() == null && b.getTelefons() == null) {
				return true;
			}
			// Comprovem telefon a telefon
			for (int i = 0; i < a.getTelefons().size(); i++) {
				String telefA = a.getTelefons().get(i);
				String telefB = b.getTelefons().get(i);
				if (!telefA.equals(telefB)) {
					return false;
				}
			}
			return true;
		}

	//Funcio auxiliar per esborrar tota la BD
		private static void resetejaTot() {
			IClientsDAO daoClients = new ClientDAO();
			
			daoClients.obrir();
			
			daoClients.reset();
			
			daoClients.tancar();
			
			
			
			
			//DROP TABLE IF EXISTS CLIENTS;
			//CREATE TABLE IF NOT EXISTS CLIENTS(NIF VARCHAR(9), NOM VARCHAR(20), PRIMARY KEY(NIF));
			//DROP TABLE IF EXISTS TELEFON
			//CREATE TABLE IF NOT EXISTS TELEFONS (TELEFON VARCHAR(9), NIF_CLIENT VARCHAR(9), PRIMARY KEY(TELEFON), FOREIGN KEY(NIF_CLIENT) REFERENCES CLIENTS(NIF))
			// TODO Fes que aquest metode esborri totes les dades de la BD, fitxer o
			// qualsevol lloc on guardes dades
			// En cas contrari, tindras problemes de PRIMARY KEY duplicades cada vegada que tornes
			// a executar el programa
		}


		public static void main(String[] args)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException {

			resetejaTot();

			// TODO: Crea un client a partir de la teva classe real
			IClient clientA = new Client();
			clientA.setNIF("11111111A");
			clientA.setNom("Andreu");
			clientA.addTelefon("911111111");

			// TODO: Crea un client a partir de la teva classe real
			IClient clientB = new Client();
			clientB.setNIF("22222222B");
			clientB.setNom("Bea");
			clientB.addTelefon("921111111");
			clientB.addTelefon("922111111");

			// TODO: Crea un DAO de clients a partir de la teva classe real
			IClientsDAO daoClients = new ClientDAO();

	/////////////////////////////////////////////////////////////////////////////
	// FUNCIONAMENT NORMAL - COMPROVACIÓ GUARDAR, LLEGIR UN CLIENT I LLEGIR TOTS
	/////////////////////////////////////////////////////////////////////////////		
			try {
				daoClients.obrir();
				// Guardar
				daoClients.create(clientA);
				daoClients.create(clientB);

				System.out.println("Clients guardats");
				System.out.println("--------------------------------");
				System.out.println(clientA);
				System.out.println(clientB);

				// Llegir
				IClient llegitA = daoClients.read("11111111A");
				IClient llegitB = daoClients.read("22222222B");

				System.out.println("Clients llegits");
				System.out.println("--------------------------------");
				System.out.println(llegitA);
				System.out.println(llegitB);

				if (comparaClients(llegitA, clientA) && comparaClients(llegitB, clientB)) {
					System.out.println("Els clients llegits coincideixen amb els guardats");
				} else {
					System.out.println("Els clients llegits no coincideixen amb els guardats");
				}

				// Llegir tots
				List<IClient> tots = daoClients.readAll();
				if (comparaClients(tots.get(0), clientA) && comparaClients(tots.get(1), clientB)) {
					System.out.println("Els clients llegits de cop coincideixen amb els guardats");
				} else {
					System.out.println("Els clients llegits de cop no coincideixen amb els guardats");
				}

				daoClients.tancar();

			} catch (RuntimeException rte) {
				rte.printStackTrace();
			}

	/////////////////////////////////////////////////////////////////////////////
	// FUNCIONAMENT ESPECIAL
	/////////////////////////////////////////////////////////////////////////////

	// Hi ha excepcions quan es crea un Client amb nif null
			// TODO: Crea un client a partir de la teva classe real
			IClient clientY =  new Client();
			try {
				clientY.setNIF(null);
				System.out.println("ERROR: Quan el nif es null s'hauria de llençar una excepcio");
			} catch (Exception e) {
				System.out.println("OK: Quan el nif es null es llança una excepcio");
			}

	// Hi ha excepcions quan es crea un Client amb nif amb menys de 9 caracters
			// TODO: Crea un client a partir de la teva classe real
			IClient clientX =  new Client();
			try {
				clientX.setNIF("123");
				System.out.println("ERROR: Quan el nif no te 9 caracters com a minim, s'hauria de llençar una excepcio");
			} catch (Exception e) {
				System.out.println("OK: Quan el nif no te 9 caracters  com a minim  es llança una excepcio");
			}

	// No ha de passar res si s'obre el dao 2 vegades
			daoClients.obrir();
			daoClients.obrir();
			// TODO: Crea un client a partir de la teva classe real
			IClient clientZ =  new Client();
			clientZ.setNIF("99999999Z");
			clientZ.setNom("Zach");
			daoClients.create(clientZ);
			IClient llegitZ = daoClients.read("99999999Z");
			if (comparaClients(clientZ, llegitZ)) {
				System.out.println("OK: Obrir deixa preparat sempre el DAO");
			} else {
				System.out.println("ERROR: Obrir no deixa preparat sempre el DAO");
			}
			daoClients.tancar();

	// Si es tanca sense obrir es llanca excepcio
			try {
				daoClients.tancar();
				System.out.println("ERROR: Tancar sense obrir no esta llencant una excepcio");
			} catch (Exception e) {
				System.out.println("OK: Si es tanca sense obrir s'esta llancant una excepcio");
			}

	// Si es guarda sense obrir es llanca excepcio
			try {

				// TODO: Crea un client a partir de la teva classe real
				IClient clientW =  new Client();
				clientW.setNIF("89898989W");
				clientW.setNom("Walter");
				daoClients.create(clientW);
				System.out.println("ERROR: Si es guarda sense obrir no es llanca excepcio");
			} catch (Exception e) {
				System.out.println("OK: Si es guarda sense obrir es llanca excepcio");
			}

	// Si es guarda un client amb nif null es llanca excepcio
			daoClients.obrir();
			try {
				// TODO: Crea un client a partir de la teva classe real
				IClient clientV =  new Client();
				clientV.setNom("Vincent");
				daoClients.create(clientV);
				System.out.println("ERROR: Si es guarda un client amb nif null no es llanca excepcio");
			} catch (RuntimeException re) {
				System.out.println("OK: Si es guarda un client amb nif null es llanca excepcio");
			}
			daoClients.tancar();

	// Aquest cas no esta especificat. Guardar client null
			daoClients.obrir();
			try {
				daoClients.create(null);
				System.out.println("???: Guardar client null no llanca excepcio");
			} catch (RuntimeException re) {
				System.out.println("???: Guardar client null llanca excepcio");
			}
			daoClients.tancar();

	// S'actualitza el nom
			daoClients.obrir();

			// TODO: Crea un client a partir de la teva classe real
			IClient clientT =  new Client();
			clientT.setNom("Teresa");
			clientT.setNIF("78787878X");
			daoClients.create(clientT);

			clientT.setNom("Tere");
			daoClients.update(clientT);

			IClient llegitT = daoClients.read("78787878X");
			if (comparaClients(llegitT, clientT)) {
				System.out.println("OK: S'actualitza el nom");
			} else {
				System.out.println("ERROR: No s'actualitza el nom");
			}
			daoClients.tancar();

		}

	}