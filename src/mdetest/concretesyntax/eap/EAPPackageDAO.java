package mdetest.concretesyntax.eap;

import java.util.*;
import java.sql.*;
import mdetest.metamodels.functionalrequirement.Subsystem;;

/**
 *
 * @author Rincew
 */
public class EAPPackageDAO {

    EAPConnection c;
    String sqlText;
    Map<Subsystem, String> ids;
    String lastId;

    /**
     * You should use the connection facade to obtain an instance of this 
     * class.
     * @param c
     */
    public EAPPackageDAO(EAPConnection c) {
        this.c = c;
        ids = new HashMap<Subsystem, String>();
    }

    /**
     *
     * @param name
     * @return
     */
    public Subsystem getPackage(String name) {
        List<Subsystem> lp = this.getAllPackagesWithName(name);
    	assert ( lp.size() == 1) : "\n" + lp.size() + " paquetes con el nombre: " + name;
    	if (lp.isEmpty()) {
    		return null;
    	}
    	return lp.get(0);
    }

    /**
     * Get all packages with the same name
     * @param name
     * @return
     */
    public List<Subsystem> getAllPackagesWithName(String name) {

    	Subsystem p = null;
        List<Subsystem> lp = new ArrayList<Subsystem>(20);

        int num_pack = 0;

        this.sqlText = "select t_package.[Package_ID], t_package.[Name], " +
        		"t_package.[Notes] "

                + "from t_package "
                + "where name='" +
                name + "'" +
                " order by t_package.[Name];";
        try {
            ResultSet rs = c.executeQuery(sqlText);

            while (rs.next()) {
                p = new Subsystem();
                
                lastId = rs.getString(1);
                
                
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                lp.add(p);
                
                /*
                assert ids.containsKey(p) == false
                 : "Paquete con nombre repetido: " + p;
                */
                ids.put(p, lastId);
                num_pack++;
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Consulta SQL:\n" + this.sqlText);
            throw new EAPException(ex);
        }

        //assert ( num_pack == 1) : "\nMas de un paquete con el nombre: " + name; -- Puede ser 0
        assert lp.size() < 20;

        return lp;
    }


    /**
     * No code uses this method
     * 
     * Get all packages with the same stereotype
     * @param name
     * @return
     
    public List<Subsystem> getAllPackagesWithStereotype(String sName) {

    	Subsystem p = null;
        List<Subsystem> lp = new ArrayList<Subsystem>(40);

        int num_pack = 0;

        this.sqlText = "select t_object.[Package_ID], t_object.[Name], " +
        		"t_object.[Notes] "
                + "from t_object "
                + "where stereotype='" +
                sName + "'"
                + " and t_object.Object_Type ='Package'"
                + " order by t_package.[Name];";
        try {
            ResultSet rs = c.executeQuery(sqlText);
            String key;
            
            while (rs.next()) {
                p = new Subsystem();
                key = rs.getString(1);
                
                
               
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                
                lp.add(p);

                assert ids.containsKey(p) == false;
                ids.put(p, key);

                num_pack++;
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Consulta SQL:\n" + this.sqlText);
            throw new EAPException(ex);
        }

        assert ( num_pack == 1) 
        : "\nMas de un paquete con el nombre: " + p.getName();
        assert lp.size() < 10;

        return lp;
    }
*/

    /**
     * SELECT t_package.Parent_ID 
     * FROM t_package 
     * WHERE t_package.Package_ID
     * 
     * @param pb No hago nada con ese parámetro.
     * @param packageId Id del paquete padre
     * @return
     */
    public List<Subsystem> getAllPackagesIn(Subsystem pb, String packageId) {

        List<Subsystem> lp = new java.util.ArrayList<Subsystem>(100);
        Subsystem p;

        this.sqlText = "select t_package.[Package_ID], " +
        		"t_package.[Name], " +
        		"t_package.[Notes] "

                + "from t_package "
                + "where t_package.[Parent_ID] = "

                + packageId + "" + " " +
                "order by t_package.[Name];";
        try {
            ResultSet rs = c.executeQuery(sqlText);
            
            while (rs.next()) {
                p = new Subsystem();
             
                String key = rs.getString(1);
                
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setInPackage(pb);
                //p.setStereotype(rs.getString(4));

                /*Es válido que sea repetido porque s euede intentar leer varias veces
                 * assert ids.containsKey(p) == false
                	: "Paquete repetido: " + p
                	+ " id:"+key
                	+ " . contenido del map: " + ids
                	+ ". Consulta SQL:" + sqlText;*/
  
                /* No me ha funcionado. Bucle infinito */
                if (ids.containsKey(p) == false)
                	ids.put(p, key);
			
                
                lp.add(p);
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Consulta SQL:\n" + this.sqlText);
            throw new EAPException(ex);
        }
        //assert !(lp.isEmpty()) 
        //	: "\n" + this.sqlText + "\n" + lp.size();
        assert lp.size() < 100 : "Paquetes: " + lp.size();

        return lp;
    }


    public String getLastId() {
    	return this.lastId;
    }
    
    /**
     * IDS must change when reading a package
     * from the EAP.
     * 
     * @param s
     * @return
     */
    public String getIdOfSubsystem(Subsystem s) {
    	assert this.ids.containsKey(s) : "Subsystem not readed yet";
    	return this.ids.get(s);
    }
}
