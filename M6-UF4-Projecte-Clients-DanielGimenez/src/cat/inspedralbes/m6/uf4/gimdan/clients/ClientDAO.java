package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientsDAO {

	static Connection con = null;

	@Override
	public void obrir() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.205.104:3306/dani", "dani", "root");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void create(IClient client) {
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
	}

	@Override
	public IClient read(String nif) {
		String select = "SELECT * FROM CLIENTS WHERE NIF='" + nif;
		IClient client = null;
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
	}

	@Override
	public List<IClient> readAll() {
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
	}

	@Override
	public void update(IClient client) {
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

	@Override
	public void tancar() {
		try {
			if (con != null || !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
