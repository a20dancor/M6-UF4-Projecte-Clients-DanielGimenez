package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientsDAO {

	static Connection con = null;
	Boolean obert = false;

	@Override
	public void obrir() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani", "dani", "root");
			obert = true;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		
	}

	@Override
	public void create(IClient client) {
		if (obert) {
			if (client != null) {
				if (client.getNIF() != null) {
					String insert = "Insert into CLIENTS VALUES('" + client.getNIF() + "', '" + client.getNom() + "')";
					Statement stat;
					try {
						stat = con.createStatement();
						stat.executeUpdate(insert);
						stat.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					throw new RuntimeException("El NIF ES NULL!");
				}
			} else {
				throw new RuntimeException("Estas intentant guardar un client null!");
			}
		}

		else {
			throw new RuntimeException("NO ESTÀ OBERT EL DAO!");
		}
	}

	@Override
	public IClient read(String nif) {
		if (obert) {
			String select = "SELECT * FROM CLIENTS WHERE NIF='" + nif + "'";
			IClient client = new Client();
			Statement stat;
			try {
				stat = con.createStatement();
				ResultSet rs = stat.executeQuery(select);
				while (rs.next()) {
					client.setNIF(rs.getString(1));
					client.setNom(rs.getString(2));
				}
				rs.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return client;
		} else {
			return null;
		}
	}

	@Override
	public List<IClient> readAll() {
		if (obert) {
			String select = "SELECT * FROM CLIENTS";
			List<IClient> llistaClients = new ArrayList<>();

			Statement stat;
			try {
				stat = con.createStatement();
				ResultSet rs = stat.executeQuery(select);
				while (rs.next()) {
					IClient client = new Client();
					client.setNIF(rs.getString(1));
					client.setNom(rs.getString(2));
					llistaClients.add(client);
				}
				rs.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return llistaClients;
		} else {
			return null;
		}
	}

	@Override
	public void update(IClient client) {
		if (obert) {
			String update = "Update CLIENTS SET NOM = '" + client.getNom() + "' WHERE NIF = '" + client.getNIF() + "'";
			Statement stat;
			try {
				stat = con.createStatement();
				stat.executeUpdate(update);
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(IClient client) {
		if (obert) {
			String delete = "DELETE FROM CLIENTS WHERE NIF ='" + client.getNIF() + "'";
			Statement stat;
			try {
				stat = con.createStatement();
				stat.executeUpdate(delete);
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void tancar() {
		if (obert) {
			try {
				if (con != null || !con.isClosed()) {
					con.close();
					obert = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("S'ha de executar el metode obrir() abans");
		}
	}

	@Override
	public void reset() {

		String createTableClients = "CREATE TABLE IF NOT EXISTS CLIENTS(NIF VARCHAR(9), NOM VARCHAR(20), PRIMARY KEY(NIF))";
		String dropTableClients = "DROP TABLE IF EXISTS CLIENTS";
		String dropTableTelefons="DROP TABLE IF EXISTS TELEFONS";
		String createTableTelefons="CREATE TABLE IF NOT EXISTS TELEFONS (TELEFON VARCHAR(9), NIF_CLIENT VARCHAR(9), PRIMARY KEY(TELEFON), FOREIGN KEY(NIF_CLIENT) REFERENCES CLIENTS(NIF))";

		Statement stat;
		try {
			stat = con.createStatement();
			stat.executeUpdate(dropTableTelefons);
			stat.executeUpdate(dropTableClients);
			stat.executeUpdate(createTableClients);
			stat.executeUpdate(createTableTelefons);
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
