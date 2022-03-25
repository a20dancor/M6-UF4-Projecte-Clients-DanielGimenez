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
	public void create(List<ITelefon> llistaTelefons) {
		Statement stat;
		try {
			stat = con.createStatement();
			for (int i = 0; i < llistaTelefons.size(); i++) {
				String insert = "Insert into TELEFONS VALUES('" + llistaTelefons.get(i).getTelefon() + "', '"
						+ llistaTelefons.get(i).getNifClient() + "')";
				stat.executeUpdate(insert);
			}

			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ITelefon> read(String nif) {
		List<ITelefon> llistaTelefons = new ArrayList<>();
		String select = "SELECT * FROM TELEFONS WHERE NIF_CLIENT='" + nif + "'";

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return llistaTelefons;
	}

	@Override
	public List<ITelefon> readAll() {
		String select = "SELECT * FROM TELEFONS";
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
	public void update(List<ITelefon> telefons) {
		List<ITelefon> llistaAnterior = read(telefons.get(0).getNifClient());
		for (int i = 0; i < llistaAnterior.size(); i++) {
			System.out.println(llistaAnterior.get(i));
		}
		System.out.println("Nova llista");
		for (int i = 0; i < llistaAnterior.size(); i++) {
			System.out.println(llistaAnterior.get(i));
		}
		
//		for (int i = 0; i < telefons.size(); i++) {
//			llistaAnterior.add(telefons.get(i));
//		}
//		for (int i = 0; i < llistaAnterior.size(); i++) {
//			System.out.println(llistaAnterior.get(i));
//		}
		delete(telefons.get(0).getNifClient());
//		for (int i = 0; i < llistaAnterior.size(); i++) {
//			create(llistaAnterior);
//		}

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
