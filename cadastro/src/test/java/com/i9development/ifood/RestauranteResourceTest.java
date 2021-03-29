package com.i9development.ifood;

import javax.ws.rs.core.Response.Status;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.i9development.ifood.cadastro.Restaurante;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {
	
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testBuscarRestaurantes() {
		String resultado = given()
				.when().get("/restaurantes")
				.then()
				.statusCode(200)
				.extract()	
				.asString();
		Approvals.verifyJson(resultado);
	}
	
	private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);
    }
	
	//put
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testAlterarRestaurantes() {
		Restaurante dto = new Restaurante();
		dto.nome = "NovoNome";
		Long parameterValue = 123L;
		given()
				.when().pathParam("id", parameterValue)
				.body(dto)
				.when().put("/restaurantes/{id}")
				.then()
				.statusCode(Status.NO_CONTENT.getStatusCode())
				.extract()	
				.asString();
		Restaurante foundObject = Restaurante.findById(parameterValue);
		
		Assert.assertEquals(dto.nome, foundObject.nome);
		
	}
}
