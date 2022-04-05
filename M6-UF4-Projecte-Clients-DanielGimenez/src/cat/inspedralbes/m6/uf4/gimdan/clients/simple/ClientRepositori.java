package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

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
		daoTelefon.create(client);
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
		System.out.println("Llista de Clients:");

		for (int i = 0; i < llistaClients.size(); i++) {
			llistaClients.get(i).setTelefons(daoTelefon.read(llistaClients.get(i).getNIF()));
		}

		for (int i = 0; i < llistaClients.size(); i++) {
			System.out.println(llistaClients.get(i));
		}

		daoTelefon.tancar();
		daoClient.tancar();

		
	}

	@Override
	public void update(IClient client) {
		daoClient.obrir();
		daoTelefon.obrir();
		daoClient.update(client);
		daoTelefon.update(client);
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
