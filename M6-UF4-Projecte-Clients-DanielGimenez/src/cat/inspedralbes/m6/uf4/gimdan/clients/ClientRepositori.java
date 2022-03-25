package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.ArrayList;
import java.util.List;

public class ClientRepositori implements IClientRepositori {

	IClientsDAO daoClient = new ClientDAO();
	ITelefonDAO daoTelefon = new TelefonDAO();

	@Override
	public void create(IClient client) {
		daoClient.obrir();
		daoTelefon.obrir();
		daoClient.create(client);
		List<ITelefon> llistaTelefons = new ArrayList<>();
		for (int i = 0; i < client.getTelefons().size(); i++) {
			ITelefon telefon = new Telefon();
			telefon.setTelefon(client.getTelefons().get(i).getTelefon());
			telefon.setNifClient(client.getNIF());
			llistaTelefons.add(telefon);
		}
		daoTelefon.create(llistaTelefons);
		daoTelefon.tancar();
		daoClient.tancar();
	}

	@Override
	public IClient read(String nif) {
		daoClient.obrir();
		daoTelefon.obrir();
		IClient client = daoClient.read(nif);
		client.setTelefons(daoTelefon.read(nif));
		daoTelefon.tancar();
		daoClient.tancar();
		return client;
	}

	@Override
	public void readAll() {
		daoClient.obrir();
		daoTelefon.obrir();
		List<IClient> llistaClients = daoClient.readAll();
		List<ITelefon> llistaTelefons = daoTelefon.readAll();
		System.out.println("Llista de Clients:");


		for (int i = 0; i < llistaClients.size(); i++) {
			for (int j = 0; j < llistaTelefons.size(); j++) {
				if (llistaTelefons.get(j).getNifClient().equals(llistaClients.get(i).getNIF())) {
					llistaClients.get(i).addTelefon(llistaTelefons.get(j));
				}
			}
		}
		
		for (int i = 0; i < llistaClients.size(); i++) {
			System.out.println(llistaClients.get(i));
		}

//		System.out.println("Llista de telefons");
//		for (int i = 0; i < llistaTelefons.size(); i++) {
//			System.out.println(llistaTelefons.get(i));
//
//		}
		daoTelefon.tancar();
		daoClient.tancar();

	}

	@Override
	public void update(IClient client) {
		daoClient.obrir();
		daoTelefon.obrir();
		daoClient.update(client);
		daoTelefon.update(client.getTelefons());
		daoTelefon.tancar();
		daoClient.tancar();
	}

	@Override
	public void delete(IClient client) {
		daoClient.obrir();
		daoTelefon.obrir();
		daoTelefon.delete(client.getNIF());
		daoClient.delete(client);
		daoTelefon.tancar();
		daoClient.tancar();
	}

}
