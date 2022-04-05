package cat.inspedralbes.m6.uf4.gimdan.clients.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TelefonDAO implements ITelefonDAO {

	static Connection con = null;

	@Override
	public void obrir() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dani", "dani", "root");
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void create(IClient client) {
		Statement stat;
		try {
			stat = con.createStatement();
			for (int i = 0; i < client.getTelefons().size(); i++) {
				String insert = "Insert into TELEFONS VALUES('" + client.getTelefons().get(i) + "', '" + client.getNIF()
						+ "')";
				stat.executeUpdate(insert);
			}

			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> read(String nif) {
		List<String> llistaTelefons = new ArrayList<>();
		String select = "SELECT * FROM TELEFONS WHERE NIF_CLIENT='" + nif + "'";

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(select);
			while (rs.next()) {
				String telefon = rs.getString(1);
				llistaTelefons.add(telefon);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return llistaTelefons;
	}

	@Override
	public List<String> readAll() {
		String select = "SELECT * FROM TELEFONS";
		List<String> llistaTelefons = new ArrayList<>();

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(select);
			while (rs.next()) {
				String telefon = rs.getString(1);
				llistaTelefons.add(telefon);
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return llistaTelefons;
	}

	@Override
	public void update(IClient client) {
		List<String> llistaAnterior = read(client.getNIF());

		client.getTelefons().removeAll(llistaAnterior);

		delete(client.getNIF());
		
		create(client);

	}

	@Override
	public void delete(String nif) {
		String delete = "DELETE FROM TELEFONS WHERE NIF_CLIENT ='" + nif + "'";
		Statement stat;
		try {
			stat = con.createStatement();
			stat.executeUpdate(delete);
			stat.close();
		} catch (SQLException e) {
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
