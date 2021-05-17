package com.i9development.ifood.marketplace.models;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
@JsonIgnoreProperties
@Blocking
public class RestauranteCadastrado {

	@Inject
	PgPool pgPool;

	
	@Incoming("restaurantes")
	public void receberRestaurante(JsonObject json) {
		Restaurante restaurante = json.mapTo(Restaurante.class);
		System.out.println("-----------------------------");
		System.out.println(json);
		System.out.println("-----------------------------");
		System.out.println(restaurante);
		//restaurante.persist(pgPool);
	}
}