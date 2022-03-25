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
		repositori.delete(client);
		repositori.create(client);
		//repositori.delete(client); /*va bien*/
		//repositori.readAll();

		client.setNom("NouNom");
		Telefon telefon3= new Telefon();
		telefon3.setNifClient(client.getNIF());
		telefon3.setTelefon("2222222222");
		client.addTelefon(telefon3);
		IClient c= repositori.read("123456789");
		System.out.println(c);

		repositori.update(client); /*sembla que funciona*/
		
		
	}
}
