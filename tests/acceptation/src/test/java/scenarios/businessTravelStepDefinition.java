/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenarios;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class businessTravelStepDefinition {
    private static final String PATH = "/travels";
    private static final String APPLICATION_JSON = "application/json";
    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("https://api.myjson.com/bins/hv8lt");
    //private final String jsonString = new Scanner(new , "UTF-8").useDelimiter("\\Z").next();
    private final String jsonString;
    private final WireMockServer wireMockServer = new WireMockServer();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public businessTravelStepDefinition() throws MalformedURLException, IOException {
        this.jsonString = new Scanner(new URL("https://api.myjson.com/bins/tu1v5").openStream(), "UTF-8").useDelimiter("\\Z").next();
    }
    @When("users submit a business travel$")
    public void usersSubmitABusinessTravel() throws IOException{
        System.out.println(jsonString);
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo(PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
     
                .willReturn(aResponse().withStatus(200)));
        HttpPost request = new HttpPost("http://localhost:8080/travels");
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
        verify(postRequestedFor(urlEqualTo(PATH))
                .withHeader("content-type", equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }
    
    @Then("^the node server should handle it and return a success status$")
    public void theNodeServerShouldHandleItAndReturnASuccessStatus() {
    }
    
    @When("^users get all business travels$")
    public void usersGetAllBusinessTravels() throws IOException {
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/travels")).withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(jsonString)));

        HttpGet request = new HttpGet("http://localhost:8080/travels");
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = convertResponseToString(httpResponse);
        assertThat(responseString, containsString("Rabat"));
        wireMockServer.stop();
    }
    
    @Then("^the node server should return data$")
    public void theNodeServerShouldReturnData(){
        
    }
    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
}
