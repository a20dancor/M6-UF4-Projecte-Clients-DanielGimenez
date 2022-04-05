package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.ArrayList;
import java.util.List;

public class PrincipalRepositori {
	public static void main(String[] args) {

		IClientRepositori repositori = new ClientRepositori();

		IClient clientA = new Client();
		clientA.setNIF("11111111A");
		clientA.setNom("Andreu");
		clientA.addTelefon("911111111");

		IClient clientB = new Client();
		clientB.setNIF("22222222B");
		clientB.setNom("Bea");
		clientB.addTelefon("921111111");
		clientB.addTelefon("922111111");

		repositori.create(clientA);
		repositori.create(clientB);

		IClient clientLlegir = repositori.read(clientA.getNIF());

		System.out.println(clientLlegir);

		System.out.println("LLegir tots els clients");
		repositori.readAll();

		repositori.delete(clientA);

		System.out.println("LLegir tots els clients");
		repositori.readAll();

	}
}
