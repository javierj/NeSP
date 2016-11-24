package iwt2.concretesyntax.eap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import iwt2.metamodel.gherkin.Scenario;
import mdetest.concretesyntax.eap.EAPConnection;
import mdetest.concretesyntax.eap.EAPException;
import mdetest.concretesyntax.eap.EAPPackageDAO;
import mdetest.concretesyntax.eap.EAPParentDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;

public class EAPScenariosDAO extends EAPParentDAO {

	public EAPScenariosDAO(EAPConnection c) {
		super(c);
	}
	
	
	public List<Scenario> getScenariosFor(FunctionalRequirement fr) {
		List<Scenario> scenarios = new ArrayList<>();
		Scenario scen;
		
		if (fr.getInternalId() == null) {
			System.err.println("EAPScenariosDAO::getScenariosFor -- No internal id setted.");
		}
		
		String sqlText = "SELECT t_object.[Object_ID], t_object.[Name], t_object.[Note] "
				+ "FROM t_object, t_connector "
				+ "WHERE t_connector.[Start_Object_ID]=" + fr.getInternalId()
				+ " AND t_connector.[End_Object_ID]=t_object.[Object_ID]"
				+ " AND t_object.[Stereotype]='scenario'"
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			while (rs.next()) {
				scen = new Scenario();
				scen.setDescription(rs.getString(3));
				scenarios.add(scen);
				
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}


		sqlText = "SELECT t_object.[Object_ID], t_object.[Name], t_object.[Note] "
				+ "FROM t_object, t_connector "
				+ "WHERE t_connector.[End_Object_ID]=" + fr.getInternalId()
				+ " AND t_connector.[Start_Object_ID]=t_object.[Object_ID]"
				+ " AND t_object.[Stereotype]='scenario'"
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			
			while (rs.next()) {
				scen = new Scenario();
				scen.setDescription(rs.getString(3));
				scenarios.add(scen);
				
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}
		
		return scenarios;
	}

}
