/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mdetest.concretesyntax.eap;

/**
 *
 * @author Rincew
 */
public class EAPException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>EAPException</code> without detail message.
     */
    public EAPException() {
    }


    /**
     * Constructs an instance of <code>EAPException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EAPException(String msg) {
        super(msg);
    }

    public EAPException(Throwable t) {
        super(t);
    }
}
