package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.List;

public interface IClientsDAO {

	/**
	 * Prepara el dao per treballar. Es obligatori obrir abans de fer res mes. No ha
	 * de passar res dolent si es crida moltes vegades a aquest metode.
	 */
	void obrir();

	/**
	 * Guarda un client a la persistencia. No s'especifica que ha de passar si el
	 * client ja existeix aixi que pots controlar la possible excepcio o tirar-la
	 * 
	 * @param client El client a guardar.
	 * @throws RuntimeException si no s'ha cridat abans a <code>obrir()</code>
	 * @throws RuntimeException si el client te nif <code>null</code>
	 */
	void create(IClient client);

	/**
	 * Llegeix un client a partir d'un nif.
	 * 
	 * @param nif El nif del client a buscar.
	 * @return El client amb aquest nif o <code>null</code> si no s'ha trobat.
	 * @throws RuntimeException si no s'ha cridat abans a <code>obrir()</code>
	 */
	IClient read(String nif);

	/**
	 * Llegeix tots els clients que hi ha en la persistencia.
	 * 
	 * @return Una llista amb els clients que hi ha. Si no n'hi ha cap, la llista ha
	 *         d'estar buida. <b>No s'ha de retornar <code>null</code></b>
	 * @throws RuntimeException si no s'ha cridat abans a <code>obrir()</code>
	 */
	List<IClient> readAll();

	/**
	 * Actualitza el nom d'un client a la persistencia. El nif no s'actualitza ja
	 * que es l'identificador.
	 * 
	 * @param client El client a actualitzar.
	 * @throws RuntimeException si no s'ha cridat abans a <code>obrir()</code>
	 * @throws RuntimeException si el client te nif <code>null</code>
	 */
	void update(IClient client);

	void delete(IClient client);

	/**
	 * Finalitza el treball amb el dao.
	 * 
	 * @throws RuntimeException si no s'ha cridat abans a <code>obrir()</code>
	 */
	void tancar();

	void reset();
}
