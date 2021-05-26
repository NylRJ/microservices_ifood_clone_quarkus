package com.i9development.ifood.pedido.models;

import org.bson.types.Decimal128;

public class Prato {
	public String nome;
	public String descricao;
	public Decimal128 preco;
	@Override
	public String toString() {
		return "Prato [nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + "]";
	}
	
	
	
	
}
