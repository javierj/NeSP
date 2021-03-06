package queries;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import mdetest.concretesyntax.eap.EAPConnection;
import mdetest.concretesyntax.eap.EAPException;
import mdetest.metamodels.base.Constraint;

/**
 * Tsting soem wuries for extracing information from EAP file.
 * @author Javier
 *
 */
public class TestSQLQueries {

	EAPConnection c;
	
	public TestSQLQueries() {
		c = EAPConnection.CreateConnection("./test/resources/ForTesting 01.EAP");
	}
	
	@Test
	public void obtainAllSenarios() {
		int size = 0;
		
		String sqlText = "SELECT t_object.[Object_ID], t_object.[Name], t_object.[Note] "
				+ "FROM t_object "
				+ "WHERE t_object.[Stereotype]='scenario'" 
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			
			while (rs.next()) {
				size++;
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}

		assertEquals(2, size);
	}

	
	@Test
	public void obtainSenariosAssociatedToAnUseCase() {
		String frId = "11";
		int size = 0;
		
		String sqlText = "SELECT t_object.[Object_ID], t_object.[Name], t_object.[Note] "
				+ "FROM t_object, t_connector "
				+ "WHERE t_connector.[Start_Object_ID]=" + frId
				+ " AND t_connector.[End_Object_ID]=t_object.[Object_ID]"
				+ " AND t_object.[Stereotype]='scenario'"
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			
			while (rs.next()) {
				size++;
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}


		sqlText = "SELECT t_object.[Object_ID], t_object.[Name], t_object.[Note] "
				+ "FROM t_object, t_connector "
				+ "WHERE t_connector.[End_Object_ID]=" + frId
				+ " AND t_connector.[Start_Object_ID]=t_object.[Object_ID]"
				+ " AND t_object.[Stereotype]='scenario'"
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			
			while (rs.next()) {
				size++;
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}
		
		assertEquals(2, size);
	}

}
