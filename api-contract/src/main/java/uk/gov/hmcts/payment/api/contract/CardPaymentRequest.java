package uk.gov.hmcts.payment.api.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "createCardPaymentRequestDtoWith")
@Wither
public class CardPaymentRequest {

    @NotNull
    @Min(1)
    private Integer amount;

    @NotEmpty
    private String description;

    @NotEmpty
    @URL(protocol = "https")
    private String returnUrl;

    @NotEmpty
    private String currency;

    @NotEmpty
    private String ccdCaseNumber;

    @NotEmpty
    private String caseReference;

    @NotEmpty
    @JsonProperty("site_id")
    private String siteId;

    private List<FeeDto> feeDtos;
}