package com.i9development.ifood.pedido.models;

import java.util.List;

import com.i9development.ifood.pedido.models.valueobject.Localizacao;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {
	public String cliente;
	public List<Prato> pratos;
	public Restaurante restaurante;
	public String entregador;
	public Localizacao localizacaoEntregador;
	@Override
	public String toString() {
		return "Pedido [cliente=" + cliente + ", pratos=" + pratos + ", restaurante=" + restaurante + ", entregador="
				+ entregador + ", localizacaoEntregador=" + localizacaoEntregador + "]";
	}
	
	
	
}
