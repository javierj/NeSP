/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdetest.concretesyntax.eap;

import java.sql.SQLException;

/**
 *
 * @author Rincew
 */
public class EAPConnectionFacade {

    static EAPConnection c = null;
    static EAPPackageDAO packageDAO = null;
    static EAPFunctionalRequirementDAO frDAO = null;
    //static EAPSystemTestCaseDocumentDAO dppDAO = null;
    //static EAPCommonDAO commonDAO = null;
    
    /**
     * For testing only
     */
    static String fName = null;

    /**
     *
     * @param fileName
     */
    public static void Connect(String fileName) {
        if (c != null) {
            System.err.println("Facade already connected");
        }
        c = EAPConnection.CreateConnection(fileName);
        fName = fileName;
    }

    /**
     *
     * @return
     */
    public static EAPPackageDAO getEAPPackageDAO() {
        if (packageDAO == null) {
            packageDAO = new EAPPackageDAO(c);
        }
        return packageDAO;
    }

        /**
     *
     * @return
*/
    public static EAPFunctionalRequirementDAO getEAPFunctionalRequirementDAO() {
        if (frDAO == null) {
        	frDAO = new EAPFunctionalRequirementDAO(c);
        }
        return frDAO;
    }

    /**
     *
     * @return
     
     public static EAPSystemTestCaseDocumentDAO getEAPSystemTestCaseDocumentDAO() {
        if (dppDAO == null) {
        	dppDAO = new EAPSystemTestCaseDocumentDAO(c);
        }
        return dppDAO;
    }*/


     /**
      *
      
     public static EAPCommonDAO getEAPCommonDAO() {
        if (commonDAO == null) {
            commonDAO = new EAPCommonDAO(c);
        }
        return commonDAO;
    }*/



    /**
     *
     */
    public static void Close() {
        try {
            c.con.close();
            c = null;
            frDAO = null;
            packageDAO = null;
            fName = null;
        } catch (SQLException ex) {
           // Logger.getLogger(EAPConnectionFacade.class.getName()).log(Level.SEVERE, null, ex);
        	System.err.println("Closing Database: " + ex);
        }
    }
}
