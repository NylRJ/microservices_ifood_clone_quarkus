package com.i9development.ifood.pedido.models;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.i9development.ifood.pedido.dto.PedidoRealizadoDTO;

public class PedidoRealizadoIncoming {
	
	@Incoming("pedidos")
	public void lerPedidos(PedidoRealizadoDTO dto) {
		System.out.println("------------------");
		System.out.println(dto);
	}
	
}
