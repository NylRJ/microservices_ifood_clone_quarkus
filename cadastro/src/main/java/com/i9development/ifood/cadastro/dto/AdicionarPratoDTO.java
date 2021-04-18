package com.i9development.ifood.cadastro.dto;

import java.math.BigDecimal;

import com.i9development.ifood.cadastro.infra.DTO;
import com.i9development.ifood.cadastro.infra.ValidDTO;

@ValidDTO
public class AdicionarPratoDTO implements DTO {
	public String nome;

	public String descricao;

	public BigDecimal preco;
	
	
}
