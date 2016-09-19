package uk.gov.justice.payment.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jayway.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.justice.payment.api.json.CreatePaymentRequest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration


public class PaymnetApiApplicationTest {

	//WireMockServer wm = new WireMockServer(options().bindAddress("publicapi.integration.pymnt.uk").port(80));

	@Test
	public void exampleTest() {
		System.out.println("Hello World!  Test");
	}

	@Test
	public void createPayment() {

		CreatePaymentRequest paymentRequest = new CreatePaymentRequest();
		paymentRequest.setAmount(10);
		paymentRequest.setDescription("TestDesc");
		paymentRequest.setReference("TestRef");
		paymentRequest.setReturnUrl("https://localhost:8443/payment-result");

		RestAssured.port = 8181;
		given().contentType("application/json").
				queryParam("name", "James").body(getJson(paymentRequest)).
				when().post("/payments").
				then().statusCode(201)
				;
	}

	private String getJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
