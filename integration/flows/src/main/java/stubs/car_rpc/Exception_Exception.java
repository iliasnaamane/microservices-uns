
package stubs.car_rpc;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-11-11T15:38:11.024+01:00
 * Generated source version: 3.1.10
 */

@WebFault(name = "Exception", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/")
public class Exception_Exception extends java.lang.Exception {
    
    private stubs.car_rpc.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, stubs.car_rpc.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, stubs.car_rpc.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public stubs.car_rpc.Exception getFaultInfo() {
        return this.exception;
    }
}