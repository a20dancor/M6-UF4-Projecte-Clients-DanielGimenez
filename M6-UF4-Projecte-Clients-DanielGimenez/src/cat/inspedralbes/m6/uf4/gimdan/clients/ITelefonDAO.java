package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.util.List;

public interface ITelefonDAO {

	void obrir();
	
	void create(List<ITelefon> telefon);
	
	List<ITelefon>read(String nif);
	
	List<ITelefon> readAll();
	
	void update(List<ITelefon> telefons);
	
	void delete(String nif);
	
	void tancar();
	
	
	
}
