package com.i9development.ifood.pedido.models;

import java.util.Date;

import com.i9development.ifood.pedido.models.valueobject.Localizacao;



public class Restaurante {
	public Long id;

	public String proprietario;
	public String cnpj;
	public String nome;
	public Localizacao localizacao;
	public Date dataCriacao;
	public Date dataAtualizacao;
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", proprietario=" + proprietario + ", cnpj=" + cnpj + ", nome=" + nome
				+ ", localizacao=" + localizacao + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + "]";
	}
	
	
	
}
