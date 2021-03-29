package com.i9development.ifood.cadastro;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.i9development.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.i9development.ifood.cadastro.dto.RestauranteMapper;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurante")
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name = "ifood-oauth", scopes = {})
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
	public List<Restaurante> buscar() {

		return Restaurante.listAll();
	}

	@POST
	@Transactional
	public Response adicionar(AdicionarRestauranteDTO dto) {
		Restaurante restaurante = restauranteMapper.toRestaurante(dto);
		restaurante.persist();
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public void atualizar(@PathParam("id") Long id, Restaurante dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException();
		}
		Restaurante restaurante = restauranteOp.get();
		restaurante.nome = dto.nome;

		restaurante.persist();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response delete(@PathParam("id") Long id) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
			throw new NotFoundException("Não encontrado");
		});
		return Response.status(Status.NO_CONTENT).build();
	}

	// Pratos

	@GET
	@Path("{idRestaurante}/pratos")
	@Tag(name = "prato")
	public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		return Prato.list("restaurante", restauranteOp.get());
	}

	@POST
	@Path("{idRestaurante}/pratos")
	@Transactional
	@Tag(name = "prato")
	public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}
		// Utilizando dto, recebi detached entity passed to persist:
		Prato prato = new Prato();
		prato.nome = dto.nome;
		prato.descricao = dto.descricao;

		prato.preco = dto.preco;
		prato.restaurante = restauranteOp.get();
		prato.persist();

		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	@Tag(name = "prato")
	@Tag(name = "restaurante")
	public Response atualizar(@PathParam("idRestaurante") Long idRestaurante,@PathParam("idRestaurante") Long id , Prato dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}
		
		Optional<Prato> pratoOp = Prato.findByIdOptional(id);
		if (pratoOp.isEmpty()) {
			throw new NotFoundException("Prato não existe");
		}
		Prato prato = pratoOp.get();
		prato.preco = dto.preco;
		prato.restaurante = restauranteOp.get();
		prato.persist();

		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	@Tag(name = "prato")
	public Response delete(@PathParam("idRestaurante") Long idRestaurante,@PathParam("idRestaurante") Long id , Prato dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}
		
		Optional<Prato> pratoOp = Prato.findByIdOptional(id);
	
		pratoOp.ifPresentOrElse(Prato::delete,() ->{
			throw new NotFoundException();
		});

		return Response.status(Status.NO_CONTENT).build();
	}

}