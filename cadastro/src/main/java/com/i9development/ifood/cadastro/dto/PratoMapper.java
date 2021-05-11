package com.i9development.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.i9development.ifood.cadastro.domain.Prato;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    PratoDTO toDTO(Prato p);

    Prato toPrato(AdicionarPratoDTO dto);

    void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);

}
