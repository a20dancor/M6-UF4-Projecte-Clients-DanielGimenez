package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.List;

public interface ITelefonDAO {

	void obrir();
	
	void create(ITelefon telefon);
	
	ITelefon read(String nif);
	
	List<ITelefon> readAll();
	
	void update(ITelefon telefon);
	
	void delete(ITelefon telefon);
	
	void tancar();
	
	
	
}
