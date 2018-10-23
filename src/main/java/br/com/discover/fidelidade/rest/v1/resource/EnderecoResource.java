package br.com.discover.fidelidade.rest.v1.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.discover.fidelidade.model.Endereco;
import br.com.discover.fidelidade.rest.v1.Validacoes.GeradorId;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaCEP;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaEstado;
import br.com.discover.fidelidade.rest.v1.response.EnderecoResponse;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastro;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastroResponse;
import br.com.discover.fidelidade.rest.v1.response.ErrorResponse;
import br.com.discover.fidelidade.rest.v1.response.ListaResponse;
import br.com.discover.fidelidade.rest.v1.response.UsuarioResponse;
import br.com.discover.fidelidade.rest.v1.resquest.EnderecoRequest;
import br.com.discover.fidelidade.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/endereco")
@Api(value = "endereco")
@Produces({ MediaType.APPLICATION_JSON })
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Recupera Endereço pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Endereço encontrado", response = UsuarioResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Endereço não encontrado"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Endereço inválido"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response getEnderecoById(@PathParam("id") String id) {

		Integer idEndereco = null;

		try {
			idEndereco = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		Endereco endereco = enderecoService.recuperaEndereco(idEndereco);

		if (endereco == null) {
			return Response.status(HttpStatus.SC_NO_CONTENT).build();
		}

		return Response.status(HttpStatus.SC_OK).entity(new EnderecoResponse(endereco)).build();
	}

	@POST
	@Path("/cadastro")
	@ApiOperation(value = "Cadastra um Novo Endereço")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Endereço cadastrado", response = UsuarioResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Endereço não cadastrado"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro no cadastro de endereço"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response saveEndereco(@RequestBody EnderecoRequest endereco) {

		endereco.setIdEndereco(GeradorId.getID());
		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		try {
			if (!ValidaCEP.isCep(endereco.getCep())) {
				ErrorCadastroResponse erroCep = new ErrorCadastroResponse("cep", "CEP fornecido inválido");
				listaErros.add(erroCep);
			}
			if (!ValidaEstado.isEstado(endereco.getEstado())) {
				ErrorCadastroResponse erroEstado = new ErrorCadastroResponse("estado", "Estado fornecido inválido");
				listaErros.add(erroEstado);
			}
			if (listaErros.isEmpty() == false) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}

		enderecoService.saveEndereco(endereco);
		return Response.status(HttpStatus.SC_OK).entity(new EnderecoResponse(endereco)).build();
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Atualiza um endereco já existente")
	@ApiResponses(value = { @ApiResponse(code = HttpStatus.SC_OK, message = "Endereco atualizado"),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Endereco não atualizado"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na atualização de endereco"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response updateEndereco(@RequestBody EnderecoRequest endereco, @PathParam("id") String id) {

		Integer idEndereco = null;

		try {
			idEndereco = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		endereco.setIdEndereco(idEndereco);

		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		
		if (endereco.getCep() != null && !endereco.getCep().isEmpty()) {
			if (!ValidaCEP.isCep(endereco.getCep())) {
				ErrorCadastroResponse erroCep = new ErrorCadastroResponse("cep", "CEP fornecido inválido");
				listaErros.add(erroCep);
			}
		}
		if (endereco.getEstado() != null && !endereco.getEstado().isEmpty()) {
			if (!ValidaEstado.isEstado(endereco.getEstado())) {
				ErrorCadastroResponse erroEstado = new ErrorCadastroResponse("estado", "Estado fornecido inválido");
				listaErros.add(erroEstado);
			}
		}
		
		try {
			if (!listaErros.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}

		enderecoService.updateEndereco(endereco);
		return Response.status(HttpStatus.SC_OK).build();
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Deleta um endereço do sistema")
	@ApiResponses(value = { @ApiResponse(code = HttpStatus.SC_OK, message = "Endereço deletado"),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Endereço não deletado"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na exclusão do cadastro do endereço"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response deleteEndereco(@PathParam("id") String id) {
		Integer idEndereco = null;

		try {
			idEndereco = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		enderecoService.deleteEndereco(idEndereco);
		return Response.status(HttpStatus.SC_OK).build();
	}
	
	@GET
	@Path("/lista/{id}")
	@ApiOperation(value = "Recupera lista de endereços pelo ID do usuário")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Endereços encontrados", response = EnderecoResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Endereços não encontrados"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Endereços inválidos"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response getEnderecosById(@PathParam("id") String id) {

		Integer idUsuario = null;

		try {
			idUsuario = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		listaEnderecos = enderecoService.recuperaEnderecos(idUsuario);

		if (listaEnderecos.isEmpty()) {
			return Response.status(HttpStatus.SC_NO_CONTENT).build();
		}

		return Response.status(HttpStatus.SC_OK).entity(new ListaResponse<Endereco>(listaEnderecos)).build();
	}
}
