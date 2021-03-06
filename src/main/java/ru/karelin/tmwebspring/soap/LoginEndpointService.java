package ru.karelin.tmwebspring.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-06-03T11:54:19.833+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "LoginEndpointService",
                  wsdlLocation = "http://localhost:9090/taskman/soap/login?wsdl",
                  targetNamespace = "http://soap.tmwebspring.karelin.ru/")
public class LoginEndpointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.tmwebspring.karelin.ru/", "LoginEndpointService");
    public final static QName LoginEndpointPort = new QName("http://soap.tmwebspring.karelin.ru/", "LoginEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/taskman/soap/login?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LoginEndpointService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/taskman/soap/login?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LoginEndpointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LoginEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LoginEndpointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public LoginEndpointService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public LoginEndpointService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public LoginEndpointService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns LoginEndpoint
     */
    @WebEndpoint(name = "LoginEndpointPort")
    public LoginEndpoint getLoginEndpointPort() {
        return super.getPort(LoginEndpointPort, LoginEndpoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LoginEndpoint
     */
    @WebEndpoint(name = "LoginEndpointPort")
    public LoginEndpoint getLoginEndpointPort(WebServiceFeature... features) {
        return super.getPort(LoginEndpointPort, LoginEndpoint.class, features);
    }

}
