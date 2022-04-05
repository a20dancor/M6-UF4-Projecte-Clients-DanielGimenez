package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.List;

public interface ITelefonDAO {

	void obrir();
	
	void create(IClient client);
	
	List<String>read(String nif);
	
	List<String> readAll();
	
	void update(IClient client);
	
	void delete(String nif);
	
	void tancar();
	
	
	
}
