package iwt2.concretesyntax.eap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import iwt2.metamodel.gherkin.Scenario;
import mdetest.concretesyntax.eap.EAPConnection;
import mdetest.concretesyntax.eap.EAPException;
import mdetest.concretesyntax.eap.EAPParentDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;

public class EAPScenariosDAO extends EAPParentDAO {

	public EAPScenariosDAO(EAPConnection c) {
		super(c);
	}
	
	
	/**
	 * This method reads all objectes stererotiped as "scenearios" associated to 
	 * a functional requirement using an assocacition cnnection.
	 * No stsreotype is checked for the association.
	 * @param fr
	 * @return
	 */
	public List<Scenario> getScenariosLinkedTo(FunctionalRequirement fr) {
		List<Scenario> scenarios = new ArrayList<>();
		
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
				scenarios.add(extractScenario(rs));	
				
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
				scenarios.add(extractScenario(rs));	
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}
		
		return scenarios;
	}


	/**
	 * This method serach for use cases stereotypes as 'scenarios' that are defined in a 
	 * use case diagram defined into de functional requirement fr
	 * @param fr
	 * @return
	 */
	public List<Scenario> getScenariosSonsOf(FunctionalRequirement fr) {
		List<Scenario> scenarios = new ArrayList<>();
		String diagramID = null;
		
		if (fr.getInternalId() == null) {
			System.err.println("EAPScenariosDAO::getScenariosSonsOf -- No internal id setted.");
		}

		// Refactorizar. Esta cosulta da el ID de un diagrama interno.
		String sqlText = "SELECT t_diagram.[Diagram_ID]"
+ " FROM `t_diagram`"
+ " WHERE t_diagram.[ParentID] = " + fr.getInternalId()
+ " AND t_diagram.[Diagram_Type] = 'Use Case'"
				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			while (rs.next()) {
				diagramID = rs.getString(1);	
				
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}


		if (diagramID == null) {
			System.err.println("EAPScenariosDAO::getScenariosSonsOf -- No internal diagram.");
			return scenarios;
		}

		
		sqlText = "SELECT * "
+"FROM t_object, `t_diagramobjects` "
+"WHERE t_object.[Object_ID] = t_diagramobjects.[Object_ID] "
+ "AND t_diagramobjects.[Diagram_ID] = "+diagramID
+" AND t_object.[Object_Type] = 'UseCase'"

				+ ";";

		try {
			ResultSet rs = c.executeQuery(sqlText);
			
			while (rs.next()) {
				scenarios.add(extractScenario(rs));	
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}
		
		return scenarios;
	}

	
	
	
	private Scenario extractScenario(ResultSet rs) throws SQLException {
		Scenario scen = new Scenario();
		scen.setName(rs.getString("Name"));
		scen.setDescription(rs.getString("Note"));
		return scen;
	}
}
