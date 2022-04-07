package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.util.List;

public interface IClient {

	/**
	 * Afegeix un telefon a aquest client. No s'ha de comprovar si ja existeix previament.
	 * 
	 * @param telefon El nou telefon a afegir.
	 * @throws RuntimeException Si el telefon no te 9 xifres com a minim
	 */
	public void addTelefon(String telefon);

	/**
	 * Elimina el telefon del client. Si existeixen diverses copies nomes s'elimina la primera.
	 * 
	 * @param telefon El telefon que s'eliminara. Si no existeix no ha causar cap tipus d'error. 
	 * 
	 */
	
	public void removeTelefon(String telefon);

	/**
	 * Assigna els telefons del client
	 * @param telefons Una List amb els telefons.
	 * @throws RuntimeException Si la List es <code>null</code>
	 */
	public void setTelefons(List<String> telefons);
	
	/**
	 * Obte la llista de telefons del client. 
	 * 
	 * @return Una List amb els telefons. Si no n'hi ha, retorna una List buida.
	 */
	public List<String> getTelefons();

	/**
	 * Canvia el nom del client.
	 * 
	 * @param nom El nou nom del client. Pot ser <code>null</code>.
	 */
	public void setNom(String nom);

	/**
	 * Obte el nom del client.
	 * 
	 * @return Una String amb el nom del client. Pot ser <code>null</code> si encara
	 *         no s'ha configurat.
	 */
	public String getNom();

	/**
	 * Canvia el nif del client. Hauria de ser unic en tota l'aplicacio, tot i que
	 * no s'ha de comprovar.
	 * 
	 * @param nif El nou nif del client.
	 * @throws RuntimeException Si el nif es <code>null</code> o te menys de 9
	 *                          caracters.
	 */
	public void setNIF(String nif);

	/**
	 * Obte el nif del client.
	 * 
	 * @return Una String amb el nif del client o <code>null</code> si encara no ha
	 *         sigut configurat.
	 */
	public String getNIF();

	/**
	 * Retorna una representacio del client.
	 * 
	 * @return Una String amb el nom de la classe, el nom i el nif del client. Per
	 *         exemple: "Client Pep 22222222B"
	 */
	@Override
	public String toString();
}
