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
import uk.gov.hmcts.payment.api.contract.util.CurrencyCode;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "createCreditAccountPaymentRequestDtoWith")
@Wither
public class CreditAccountPaymentRequest {

    @NotNull
    @Min(1)
    @Digits(integer = 10, fraction = 2, message = "Payment amount cannot have more than 2 decimal places")
    private BigDecimal amount;

    @NotEmpty
    private String description;

    @NotEmpty
    private String ccdCaseNumber;

    @NotEmpty
    private String caseReference;

    @NotEmpty
    private String serviceName;

    private CurrencyCode currency;

    @NotEmpty
    private String customerReference;

    @NotEmpty
    private String organisationName;

    @NotEmpty
    private String accountNumber;

    @NotEmpty
    @JsonProperty("site_id")
    private String siteId;

    @Valid
    private List<FeeDto> fees;
}