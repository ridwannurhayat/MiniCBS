package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.test.AbstractIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class LoanAccountResourceTest extends AbstractIntegrationTest {

    @Test
    void testCreateAndGetLoanAccount() {
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
        String id =
                given()
                        .contentType("application/json")
                        .body(payload)
                        .when().post("/loan-accounts")
                        .then()
                        .statusCode(201)
                        .body("id", notNullValue())
                        .extract().path("id");

        // Retrieve
        given()
                .when().get("/loan-accounts/" + id)
                .then()
                .statusCode(200)
                .body("dueDate", equalTo("2026-12-31"));
    }

    @Test
    void testListLoanAccounts() {
        given()
                .when().get("/api/loan-accounts")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }
}
