package com.i9development.ifood.pedido.dto;

import java.util.List;

public class PedidoRealizadoDTO {

    public List<PratoPedidoDTO> pratos;

    public RestauranteDTO restaurante;

    public String cliente;

	@Override
	public String toString() {
		return "PedidoRealizadoDTO [pratos=" + pratos + ", restaurante=" + restaurante + ", cliente=" + cliente + "]";
	}

    
    
}
