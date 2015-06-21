package de.fh_muenster.buecherwelt.buecherwelt;

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 20.06.15.
 */
public class AusleihverwaltungServiceImpl implements  AusleihverwaltungService{


    /**
     * Namespace is the targetNamespace in the WSDL.
     */
    private static final String NAMESPACE = "http://webservices.bw.de/";

    private static final String URL = "http://192.168.0.15:8080/buecherwelt/Ausleihverwaltung";

    /**
     * TAG contains the class name and is used for logging.
     */
    private static final String TAG = AusleihverwaltungServiceImpl.class.getName();

    /**
     * sessionId contains the session id delivered from the server.
     */
    private int sessionId;


    public List<Ausleihe> getAllAusleihen() throws NoSessionException{
        List<Ausleihe> result = new ArrayList<Ausleihe>();
        String METHOD_NAME = "getAllAusleihen";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME);

            Log.d(TAG, response.toString());
            //SoapPrimitive sid = (SoapPrimitive) response.getProperty("id");
            //this.sessionId = Integer.valueOf(sid.toString());

            for (int j = 1; j < response.getPropertyCount(); j++) {
                SoapObject soapAccountEntry = (SoapObject) response.getProperty(j);
                SoapPrimitive soapAusleiheId = (SoapPrimitive) soapAccountEntry.getProperty("id");
                SoapPrimitive soapDate = (SoapPrimitive) soapAccountEntry.getProperty("leihdatum");
                SoapPrimitive soapKundenId = (SoapPrimitive) soapAccountEntry.getProperty("kundenId");
                SoapPrimitive soapBuchId = (SoapPrimitive) soapAccountEntry.getProperty("buchId");

                Ausleihe ausleihe = new Ausleihe(Integer.valueOf(soapAusleiheId.toString()), new Date(response.getPrimitivePropertySafelyAsString("leihdatum")), Integer.valueOf(soapKundenId.toString()), Integer.valueOf(soapBuchId.toString()));
                result.add(ausleihe);
            }
            return result;
        }
        catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }

    }

    @Override
    public void neuesAusleiheHinzufuegen(int id, Date leihdatum, int kundenId, int buchId) throws NoSessionException {
        Ausleihe result = null;
        String METHOD_NAME = "neuesBuchHinzufuegen";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME,  leihdatum, kundenId, buchId);
            Log.d(TAG, response.toString());
            //SoapPrimitive sid = (SoapPrimitive) response.getProperty("id");
            //this.sessionId = Integer.valueOf(sid.toString());

            if (sessionId != 0) {
                result = new Ausleihe(id, leihdatum, kundenId, buchId);
            }
            else {
                throw new NoSessionException("Please Login!");
            }
        } catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }
    }

    public List<Ausleihe> getAusleihenByKundenId(int id) throws NoSessionException{
        List<Ausleihe> result = new ArrayList<Ausleihe>();
        String METHOD_NAME = "getAusleihenByKundenId";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME, 2);

            //Log.d(TAG, response.toString());
            //SoapPrimitive sid = (SoapPrimitive) response.getProperty("id");
            //this.sessionId = Integer.valueOf(sid.toString());
            if(response == null){
                return null;
            }else {
                for (int j = 1; j < response.getPropertyCount(); j++) {
                    SoapObject soapAccountEntry = (SoapObject) response.getProperty(j);
                    SoapPrimitive soapAusleiheId = (SoapPrimitive) soapAccountEntry.getProperty("id");
                    SoapPrimitive soapDate = (SoapPrimitive) soapAccountEntry.getProperty("leihdatum");
                    SoapPrimitive soapKundenId = (SoapPrimitive) soapAccountEntry.getProperty("kundenId");
                    SoapPrimitive soapBuchId = (SoapPrimitive) soapAccountEntry.getProperty("buchId");

                    Ausleihe ausleihe = new Ausleihe(Integer.valueOf(soapAusleiheId.toString()), new Date(response.getPrimitivePropertySafelyAsString("leihdatum")), Integer.valueOf(soapKundenId.toString()), Integer.valueOf(soapBuchId.toString()));
                    result.add(ausleihe);
                }
                return result;
            }
        }
        catch (SoapFault e) {
            throw new NoSessionException(e.getMessage());
        }

    }



    /**
     * Diese Methode delegiert einen Methodenaufruf an den hinterlegten WebService.
     * @param methodName
     * @return
     */
    private SoapObject executeSoapAction(String methodName, Object... args) throws SoapFault {

        Object result = null;

	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);

	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }

	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);

	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            @SuppressWarnings({"unused", "unchecked"})
            //List<HeaderProperty> respHeaders = androidHttpTransport.call(NAMESPACE + methodName, envelope, reqHeaders);
                    //fuehrt zu CXF-Fehler! neue Version ohne SOAP-Action funktioniert:
                    List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);

	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return (SoapObject) result;
    }
}


