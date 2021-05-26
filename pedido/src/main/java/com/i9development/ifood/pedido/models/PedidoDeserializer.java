package com.i9development.ifood.pedido.models;

import com.i9development.ifood.pedido.dto.PedidoRealizadoDTO;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {

	public PedidoDeserializer() {
		super(PedidoRealizadoDTO.class);
		
	}

}
