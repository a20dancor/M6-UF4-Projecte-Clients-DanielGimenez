package cat.inspedralbes.m6.uf4.gimdan.clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void create(ITelefon telefon) {
		String insert = "Insert into TELEFONS VALUES('" + telefon.getTelefon() + "', '" + telefon.getNifClient() + "')";
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
	public ITelefon read(String nif) {
		String select = "SELECT * FROM CLIENTS WHERE NIF='" + nif;
		ITelefon telefon = new Telefon();
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(select);
			while (rs.next()) {
				telefon.setTelefon(rs.getString(1));
				telefon.setNifClient(rs.getString(2));
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return telefon;
	}

	@Override
	public List<ITelefon> readAll() {
		String select = "SELECT * FROM CLIENTS";
		List<ITelefon> llistaTelefons = new ArrayList<>();

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(select);
			while (rs.next()) {
				ITelefon telefon = new Telefon();
				telefon.setTelefon(rs.getString(1));
				telefon.setNifClient(rs.getString(2));
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
	public void update(ITelefon telefon) {
		String update = "Update TELEFON SET NIF_CLIENT = '" + telefon.getNifClient() + "' WHERE TELEFON = '"
				+ telefon.getTelefon() + "'";
		Statement stat;
		try {
			stat = con.createStatement();
			stat.executeUpdate(update);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(ITelefon telefon) {
		String delete = "DELETE * FROM TELEFON WHERE NIF_CLIENT ='" + telefon.getNifClient() + "'";
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
