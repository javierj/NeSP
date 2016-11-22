/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdetest.concretesyntax.eap;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;
import mdetest.metamodels.functionalrequirement.Subsystem;
import mdetest.metamodels.functionalrequirement.SystemActor;
import mdetest.metamodels.base.Constraint;


/**
 *
 * @author Rincew
 */
public class EAPFunctionalRequirementDAO {

	EAPConnection c;
	String sqlText;
	Map<FunctionalRequirement, String> frId;
	Map<SystemActor, String> saId;
	Map<String, String> frxsa;

	/**
	 * 
	 * @param c
	 */
	public EAPFunctionalRequirementDAO(EAPConnection c) {
		this.c = c;
		frId = new HashMap<FunctionalRequirement, String>();
		saId = new HashMap<SystemActor, String>();
		frxsa = new HashMap<String, String>();
	}

	/**
	 * Load all use cases in the package indicated.
	 * 
	 * @param pb not in use
	 * @param pbId
	 * @return
	 */
	public List<FunctionalRequirement> getAllFunctionalRequirementsIn(
			Subsystem pb, String pbId) {

		List<FunctionalRequirement> lfr = new java.util.ArrayList<FunctionalRequirement>(
				100);
		FunctionalRequirement fr = null;

		String sqlText = "select t_object.[Object_ID], "
				+ "t_object.[Name], t_object.[Note] " + "from t_object "
				+ "where t_object.[Object_Type]='UseCase' "
				+ "and t_object.[Package_ID]=" + pbId
				+ " order by t_object.[Name];";

		try {
			ResultSet rs = this.c.executeQuery(sqlText);
			String key;
			while (rs.next()) {
				fr = new FunctionalRequirement();
				
				key = rs.getString(1);
				fr.setName(rs.getString(2));
				fr.setDescription(rs.getString(3));
				fr.setInPackage(pb);

				fr.setPreconditions(this.getPeconditions(key));
				fr.setPosconditions(this.getPostconditions(key));

				
				lfr.add(fr);
				this.frId.put(fr, key);
			}
			rs.close();

			//for (FunctionalRequirement s : lfr) {
				//s.setComponentes(this.getComponentesDePrueba(s.getId()));

				//-- Los va,os a enlazar de otra manera
				//this.addActorsIdFromFR(s);
				
				// Lospasos se los ponemos a partir del DAO.
				// this.addStepsFromFR(s);
				
				//System.out.println(s.getName() + " Actores: " + fr.getIdActores().size());
				//System.err.println("Falta los actores");
			//}

			/*
			List<TestStepBean> pasos;
			for (SystemTestCaseBean s : lstc) {
			pasos = this.getPasosDePrueba(s);

			// Esto s epuede simplifica
			s.setSteps(pasos);
			}

			// Añadimos las pruebas a los pasos
			for (SystemTestCaseBean s : lstc) {

			for (TestStepBean ts : s.getSteps()) {
			this.añadirPruebasALosPasos(ts);
			}

			}
			 */

			// Añadimos los tagged values
			//System.err.println("Falta los tagged values");
/*			for (FunctionalRequirement s : lfr) {
				sqlText = "select Property, Value "
						+ "from t_objectproperties "
						+ "where t_objectproperties.[Object_ID]=" + s.getId()
						+ ";";

				rs = this.c.executeQuery(sqlText);

				String prop, val;
				while (rs.next()) {
					prop = rs.getString(1);
					val = rs.getString(2);

					if (prop.trim().equalsIgnoreCase("Comentarios")) {
						s.setComentarios(val);
					}
					if (prop.trim().equalsIgnoreCase("Parametros de entrada")) {
						s.setParametrosEntrada(val);
					}
					if (prop.trim().equalsIgnoreCase("Resultado esperado")) {
						s.setResultadoEsperado(val);
					}
					if (prop.trim().equalsIgnoreCase("Resultado real")) {
						s.setResultadoReal(val);
					}
				}

			}
*/
		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(ex);
		}

		assert lfr.size() < 100;

		//System.out.println("Consulta SQL:\n"+sqlText);

		return lfr;
	}

	/**
	 *A precondition must be a constraint into the use case of type "Precondici�n"
	 * @param fr_id id of the funnctional requirement
	 * @return
	 */
	public List<Constraint> getPeconditions(String fr_id) {
		List<Constraint> pre = new ArrayList<Constraint>(8);

		String sqlText = "select t_objectconstraint.CONSTRAINT, t_objectconstraint.[Notes] "
				+ "from t_objectconstraint "
				+ "where t_objectconstraint.[ConstraintType]='Precondici�n' "
				+ "and t_objectconstraint.[Object_ID]="
				+ fr_id
				+ " "
				+ "order by t_objectconstraint.CONSTRAINT;";

		try {
			ResultSet rs = this.c.executeQuery(sqlText);
			Constraint c;
			while (rs.next()) {
				c = new Constraint();
				c.setValue(rs.getString(1));
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + this.sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}

		assert pre.size() < 8;
		return pre;
	}

	/**
	 *
	 * @param be
	 * @return
	 */
	public List<Constraint> getPostconditions(String be) {
		List<Constraint> pre = new ArrayList<Constraint>(5);
		//String nota;

		String sqlText = "select t_objectconstraint.CONSTRAINT, t_objectconstraint.[Notes] "
				+ "from t_objectconstraint "
				+ "where t_objectconstraint.[ConstraintType]='Postcondici�n' "
				+ "and t_objectconstraint.[Object_ID]="
				+ be
				+ " "
				+ "order by t_objectconstraint.CONSTRAINT;";

		try {
			ResultSet rs = this.c.executeQuery(sqlText);
			Constraint c;
			while (rs.next()) {
				c = new Constraint();
				c.setValue(rs.getString(1));
			}
			rs.close();

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + this.sqlText);
			throw new EAPException(
					"Error recuperando información de las precondiciones");
		}

		assert pre.size() < 6;
		return pre;
	}

	/**
	 * 
	 * @param pbAux Used for tracing only
	 * @param id Id of the package containing the actors.
	 * @return
	 */
	public List<SystemActor> getAllSystemActorsIn(Subsystem pbAux, String id) 
	{

		List<SystemActor> lfr = new java.util.ArrayList<SystemActor>(30);
		SystemActor sa = null;

		List<Long> ids = new ArrayList<Long>(10);
		
			// No usamos esta
		 /*select o1.[Name], o2.[Name]
		 from t_object o1, t_object o2, t_connector
		 where o1.[Object_Type] = 'UseCase'
		 and o2.[Object_Type] = 'Actor'
		 and o1.[Object_ID] = 7701
		 and (
		 (t_connector.[Start_Object_ID] = o1.[Object_ID]
		 and t_connector.[End_Object_ID] = o2.[Object_ID])
		 or
		 (t_connector.[Start_Object_ID] = o2.[Object_ID]
		 and t_connector.[End_Object_ID] = o1.[Object_ID])

		 );*/
		 

		// Rescriir la consulta
		/*
		 * "select t_object.[Object_ID], "
				+ "t_object.[Name], t_object.[Note]
				*/
		String sqlText = "select t_object.[Object_ID], "
			+ "t_object.[Name], t_object.[Note]"
				//+ " from t_object o1, t_object o2, t_connector"
			+ " from t_object"
				//+ " where o1.[Object_Type] = 'UseCase'"
				//+ " and o2.[Object_Type] = 'Actor' "
			+ " where t_object.[Object_Type] = 'Actor' "
				+ "and t_object.[Package_ID]=" + id
				//+ " and o1.[Object_ID] = " + fr.getId()
				//+ " and ( "
				//+ " (t_connector.[Start_Object_ID] = o1.[Object_ID]"
				//+ " and t_connector.[End_Object_ID] = o2.[Object_ID])" + " or"
				//+ " (t_connector.[Start_Object_ID] = o2.[Object_ID]"
				//+ " and t_connector.[End_Object_ID] = o1.[Object_ID])" + " );";
				;
		//"order by t_objectconstraint.CONSTRAINT;";

		try {
			ResultSet rs = this.c.executeQuery(sqlText);
			
			String key;
			
			while (rs.next()) {
				
				//ids.add(rs.getLong(1));
				sa = new SystemActor();
				sa.setName(rs.getString(2));
				sa.setDescription(rs.getString(3));
				sa.setInPackage(pbAux);
				
				key = rs.getString(1);
				
				assert this.saId.containsKey(sa) == false;
				this.saId.put(sa, key);
				lfr.add(sa);
			}

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					ex);
		}

		assert ids.size() < 10;
		
		//fr.setIdActores(ids);

		return lfr;
	}

	public String getFRId(FunctionalRequirement fr) {
		// TODO Auto-generated method stub
		assert this.frId.get(fr) != null;
		return this.frId.get(fr);
	}



	/**
	 *
	 * @param fr
	 
	void addStepsFromFR(FunctionalRequirement fr) {

		//List<StepBean> ls = new ArrayList<StepBean>(20);
	
		String sqlText = "select " +
				" t_objectscenarios.[Scenario], "
				+ " t_objectscenarios.[Notes], "
				+ " t_objectscenarios.[ScenarioType]"
				+ ",t_objectscenarios.[EValue]"
				+ " from t_objectscenarios"
				+ " where t_objectscenarios.[Object_ID]= " + fr.getId()
				+ " order by t_objectscenarios.[Scenario]"
				+ " ;";


		try {
			ResultSet rs = this.c.executeQuery(sqlText);


			while (rs.next()) {
				fr.addStep(new StepBean(fr.getId(),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)));
			}

		} catch (SQLException ex) {

			System.out.println("Consulta SQL:\n" + sqlText);
			throw new EAPException(
					ex);
		}

		//assert ids.size() < 10;
		//return ids;
		//fr.setIdActores(ids);

	}


*/

}

