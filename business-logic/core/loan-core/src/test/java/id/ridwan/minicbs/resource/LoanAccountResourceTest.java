package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.service.LoanAccountService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class LoanAccountResourceTest {

    @InjectMock
    LoanAccountService service;

    @Test
    void testCreateAndGetLoanAccount() {
        UUID id = UUID.randomUUID();
        LoanAccountDto dto = new LoanAccountDto(id, LocalDate.now(), null, null, null, null);
        
        // Mock create
        Mockito.when(service.create(any(LoanAccountDto.class)))
               .thenReturn(Uni.createFrom().item(dto));
        
        // Mock findById
        Mockito.when(service.findById(id))
               .thenReturn(Uni.createFrom().item(dto));

        String payload = """
            {
              "dueDate": "2026-12-31",
              "interest": { "rate": 0.12 },
              "repayment": {
                "repaymentMethod": "EQUAL_INSTALLMENT",
                "repaymentDay": 15,
                "repaymentPeriod": "MONTHLY"
              },
              "disbursements": [
                { "disbursementDate": "2025-03-01", "amount": 15000000 }
              ],
              "installments": [
                { "installmentDate": "2025-04-01", "amount": 1250000,
                  "principal": 1000000, "interest": 250000 }
              ]
            }
        """;

        // Create
        given()
                .contentType("application/json")
                .body(payload)
                .when().post("/loan-accounts")
                .then()
                .statusCode(200) // Default status for returning object is 200
                .body("id", notNullValue());

        // Retrieve
        given()
                .when().get("/loan-accounts/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.toString()));
    }

    @Test
    void testListLoanAccounts() {
        // Mock findPagedAndFiltered
        // The service returns Uni<Map<String, Object>>
        Map<String, Object> response = Map.of(
            "content", Collections.emptyList(),
            "totalElements", 0L
        );
        
        Mockito.when(service.findPagedAndFiltered(any(), any(), any(), any()))
               .thenReturn(Uni.createFrom().item(response));

        given()
                .when().get("/loan-accounts")
                .then()
                .statusCode(200)
                .body("content", empty());
    }
}
