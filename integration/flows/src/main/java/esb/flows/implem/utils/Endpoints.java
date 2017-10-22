package esb.flows.implem.utils;

public class Endpoints {
    // file inputs
    public static final String CSV_INPUT_FILE_HOTEL = "file:/Users/iliasnaamane/microservices-uns/integration/input"
            + "?fileName=hotels.csv" ;
    
    // file outputs
    public static final String LETTER_OUTPUT_DIR = "file:/Users/iliasnaamane/microservices-uns/integration/output";
    
    // activemq endpoints
    public static final String BUILD_HOTEL_SPEC = "activemq:handle-hotel-spec";
    
    // direct endpoints
    public static final String SEARCH_HOTEL_1 = "direct:search-hotel-1";
    public static final String SEARCH_HOTEL_2 = "direct:search-hotel-2";
    
    //services endpoints
    public static final String HOTEL_RPC_ENDPOINT = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService";
    //public static final String HOTEL_RPC_WSDL_URL = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService?wsdl&";
    //public static final String HOTEL_SERVICE_NAME = "ExternalHotelFinderService";
    //public static final String PORT_NAME = "ExternalHotelFinderPort";
   /* 
    public static final  String HOTEL_RPC_URL = HOTEL_RPC_ENDPOINT
                + "wsdlURL="+HOTEL_RPC_WSDL_URL
                + "serviceName="+HOTEL_SERVICE_NAME
                + "portName="+PORT_NAME;*/
    
    
    
}