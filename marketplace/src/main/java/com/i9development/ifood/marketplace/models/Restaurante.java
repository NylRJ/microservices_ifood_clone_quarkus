package com.i9development.ifood.marketplace.models;

import java.util.Date;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {
	public Long id;

	public String proprietario;
	public String cnpj;
	public String nome;
	public Localizacao localizacao;
	public Date dataCriacao;
	public Date dataAtualizacao;

	public void persist(PgPool pgPool) {
		pgPool.preparedQuery("insert into localizacao (id, latitude, longitude) values ($1, $2, $3)")
				.execute(Tuple.of(localizacao.id, localizacao.latitude, localizacao.longitude)).await().indefinitely();

		pgPool.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1, $2, $3)")
				.execute(Tuple.of(id, nome, localizacao.id)).await().indefinitely();

	}

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
	}

}