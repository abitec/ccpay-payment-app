package uk.gov.justice.payment.api.json.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeeshan on 21/09/2016.
 */

@RunWith(SpringRunner.class)

public class PaymentObjectsUnitTest {


        ClassLoader classLoader = getClass().getClassLoader();


    @Test
    public void createPaymentSuccessJsonToObjectMapping() throws Exception {


        File file = new File(classLoader.getResource("createPaymentResponse.json").getFile());
        ObjectMapper mapper = new ObjectMapper();
        CreatePaymentResponse obj = mapper.readValue(file, CreatePaymentResponse.class);
        assertEquals(obj.getReference(), "TestRef");
    }

    @Test
    public void createPaymentRequestJsonToObjectMapping() throws Exception {
        File file = new File(classLoader.getResource("createPaymentRequest.json").getFile());
        ObjectMapper mapper = new ObjectMapper();
        CreatePaymentRequest obj = mapper.readValue(file, CreatePaymentRequest.class);
        assertEquals(obj.getReference(), "12345");
    }

    @Test
    public void viewPaymentSuccessJsonToObjectMapping() throws Exception {
        File file = new File(classLoader.getResource("viewPaymentResponse.json").getFile());
        ObjectMapper mapper = new ObjectMapper();
        ViewPaymentResponse obj = mapper.readValue(file, ViewPaymentResponse.class);
        assertEquals(obj.getPaymentId(), "nvbblu5hp5r4d37mjg98687mui");
    }
}