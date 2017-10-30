package esb.flows.implem.utils;

public class Endpoints {
    // file inputs
    public static final String CSV_INPUT_FILE_HOTEL = "file:/home/obisama/Desktop/finalfinal/microservices-uns/integration/input"
            + "?fileName=hotels.csv" ;
     public static final String CSV_INPUT_FILE_CAR = "file:/home/obisama/Desktop/finalfinal/microservices-uns/integration/input"
            + "?fileName=car.csv" ;
    public static final String CSV_INPUT_FILE_VOLS ="file:/home/obisama/Desktop/finalfinal/microservices-uns/integration/input" 
            + "?fileName=vol.csv" ;;
    // file outputs
    public static final String LETTER_OUTPUT_DIR = "file:/home/obisama/Desktop/finalfinal/microservices-uns/integration/output";
    
    // activemq endpoints
    public static final String BUILD_HOTEL_SPEC = "activemq:handle-hotel-spec";
    public static final String BUILD_VOL_SPEC = "activemq:handle-vol-spec";
    
    // direct endpoints
    public static final String SEARCH_HOTEL_1 = "direct:search-hotel-1";
    public static final String SEARCH_HOTEL_2 = "direct:search-hotel-2";
    public static final String SEARCH_CAR = "direct:search-car";
    public static final String SEARCH_VOL = "direct:search-vol";

    //services endpoints
    public static final String HOTEL_RPC_ENDPOINT = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService";

    public static final String HOTEL_EXTERNAL_REST_ENDPOINT = "http://localhost:8003/tcs-hotel-service/hotels";
//public static final String HOTEL_RPC_WSDL_URL = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService?wsdl&";

    public static final String CAR_RPC_ENDPOINT = "http://localhost:9020/car-rpc/ExternalCarRentalService";

     public static final String VOL_JAXRS_ENDPOINT ="http://localhost:9030/vols/webapi/vols";
    //public static final String HOTEL_RPC_WSDL_URL = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService?wsdl&";

    //public static final String HOTEL_SERVICE_NAME = "ExternalHotelFinderService";
    //public static final String PORT_NAME = "ExternalHotelFinderPort";
   /* 
    public static final  String HOTEL_RPC_URL = HOTEL_RPC_ENDPOINT
                + "wsdlURL="+HOTEL_RPC_WSDL_URL
                + "serviceName="+HOTEL_SERVICE_NAME
                + "portName="+PORT_NAME;*/
    
    
    
}
