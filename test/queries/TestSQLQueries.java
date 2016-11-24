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

	@Test
	public void obtainSenariosAssociatedToAnUseCase() {
		EAPConnection c = EAPConnection.CreateConnection("./test/resources/ForTesting 01.EAP");
		String frId = "11";
		int size = 0;
		
		String sqlText = "SELECT t_object.[Object_OD], t_object.[Notes] "
				+ "FROM t_object, t_connector "
				+ "WHERE t_connector.[Start_Object_ID]=" + frId
				+ " AND t_connector.[End_Object_ID]=t_object.[Object_OD]"
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
