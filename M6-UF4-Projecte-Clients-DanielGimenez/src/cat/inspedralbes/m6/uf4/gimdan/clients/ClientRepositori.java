package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.List;

public class ClientRepositori implements IClientRepositori{

	IClientsDAO daoClient;
	ITelefonDAO daoTelefon;
	@Override
	public void create(IClient client) {
		daoClient.obrir();
		daoClient.create(client);
		//daoTelefon.create(client.getTelefons());
		daoTelefon.tancar();
		daoClient.tancar();
	}

	@Override
	public IClient read(String nif) {
		daoClient.obrir();
		daoTelefon.obrir();
		daoClient.read(nif);
		daoTelefon.read(nif);
		
		daoTelefon.tancar();
		daoClient.tancar();
		return null;
	}

	@Override
	public List<IClient> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IClient client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(IClient client) {
		// TODO Auto-generated method stub
		
	}

}
