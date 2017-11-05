package esb.flows.implem.utils;

public class Endpoints {
 
    // file inputs
    public static final String CSV_INPUT_FILE_HOTEL = "file:/Users/iliasnaamane/microservices-uns/integration/input"
            + "?fileName=hotels.csv" ;
    public static final String CSV_INPUT_FILE_CAR = "file:/Users/iliasnaamane/microservices-uns/integration/input"
            + "?fileName=car.csv";

    public static final String CSV_INPUT_FILE_VOLS ="file:/Users/iliasnaamane/microservices-uns/integration/input"
            + "?fileName=vol.csv" ;


    
   
    // file outputs
    public static final String LETTER_OUTPUT_DIR = "file:/Users/iliasnaamane/microservices-uns/integration/output";
    
    // activemq endpoints
    public static final String BUILD_HOTEL_SPEC = "activemq:handle-hotel-spec";
     public static final String BUILD_VOL_QUEUE = "activemq:handle-vol-requests";
    public static final String BUILD_CAR_QUEUE = "activemq:handle-car-requests";
    
    // direct endpoints
    public static final String SEARCH_HOTEL_1 = "direct:search-hotel-1";
    public static final String SEARCH_HOTEL_2 = "direct:search-hotel-2";
    public static final String AGGREGATION_HOTEL = "direct:aggregation-hotel";

    public static final String SEARCH_CAR_1 = "direct:search-car-1";
    public static final String SEARCH_CAR_2 = "direct:search-car-2";
    public static final String SEARCH_VOLA = "direct:search-vol-serviceA";
    public static final String SEARCH_VOLB = "direct:search-vol-serviceB";
    public static final String VOLS_AGGREGATE = "direct:aggregate-vols";
    public static final String CARS_AGGREGATE = "direct:aggregate-cars";
    


    public static final String BUSINESS_TRAVEL_REST = "direct:businesstravel-rest";

    
    public static final String BUSINESS_TRAVEL_REST2 = "direct:businesstravel-rest2";

    
    //services endpoints
    public static final String HOTEL_RPC_ENDPOINT = "http://localhost:9010/hotel-rpc/ExternalHotelFinderService";

    public static final String HOTEL_EXTERNAL_REST_ENDPOINT = "http://localhost:8003/tcs-hotel-service/hotels";

    public static final String CAR_RPC_ENDPOINT = "spring-ws:http://localhost:9020/car-rpc/ExternalCarRentalService";
    
    public static final String BUSINESS_TRAVEL_ENDPOINT = "http://localhost:9040/travels";

    public static final String VOL_JAXRS_ENDPOINT ="http://localhost:9030/vols/webapi/vols";
    
public static final String VOLS_EXTERNAL_JAXRS_ENDPOINT ="http://localhost:9080/flightreservation-service-document/registry";
    
    public static final String CAR_EXTERNAL_REST_ENDPOINT = "http://localhost:9090/cars";
    
    
  
 public static final String DEATH_POOL = "activemq:global:dead";
    
  
    
 
}
