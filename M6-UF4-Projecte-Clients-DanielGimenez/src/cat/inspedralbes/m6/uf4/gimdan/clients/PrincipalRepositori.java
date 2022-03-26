package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.ArrayList;
import java.util.List;

public class PrincipalRepositori {
	public static void main(String[] args) {
		
		IClientRepositori repositori= new ClientRepositori();
		
		Client client= new Client();
		client.setNIF("123456789");
		client.setNom("Nomdelclient");
		
		Telefon telefon= new Telefon();
		telefon.setNifClient(client.getNIF());
		telefon.setTelefon("987654321");
		client.addTelefon(telefon);
		
		Telefon telefon2= new Telefon();
		telefon2.setNifClient(client.getNIF());
		telefon2.setTelefon("555555555");
		
		client.addTelefon(telefon2);
		System.out.println(client);
		//repositori.delete(client);
		//repositori.create(client);
		
		client.setNom("NouNom");
		
		Telefon telefon3= new Telefon();
		telefon3.setNifClient(client.getNIF());
		telefon3.setTelefon("222222222");
//		client.addTelefon(telefon3);
//		repositori.update(client); /*sembla que funciona*/

		client.removeTelefon(telefon3.getTelefon());
		repositori.update(client);
		
		client.addTelefon(telefon3);
		repositori.update(client);
		
		Client client2= new Client();
		
		client2.setNom("Client2Nom");
		client2.setNIF("NIFCLIENT");
		Telefon telefon4= new Telefon();
		telefon4.setNifClient(client2.getNIF());
		telefon4.setTelefon("333333333");
		client2.addTelefon(telefon4);
		repositori.delete(client2);
		repositori.create(client2);
		repositori.readAll();
		
	}
}
