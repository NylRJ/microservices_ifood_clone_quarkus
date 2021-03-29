package com.i9development.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.i9development.ifood.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
	
	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)	
	public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
	
	
	@Mapping(target = "nome", source = "nomeFantasia")	
	public void toRestaurante(AtualizarRestauranteDTO dto);
	
}
