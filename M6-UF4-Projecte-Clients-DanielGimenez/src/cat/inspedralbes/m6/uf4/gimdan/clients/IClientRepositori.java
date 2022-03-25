package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.List;

public interface IClientRepositori {
	
	void create(IClient client);
	
	IClient read(String nif);
	
	void readAll();
	
	void update(IClient client);
	
	void delete(IClient client);
		
}
