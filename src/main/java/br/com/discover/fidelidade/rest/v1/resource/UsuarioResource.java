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

import br.com.discover.fidelidade.model.Usuario;
import br.com.discover.fidelidade.rest.v1.Validacoes.GeradorId;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaCPF;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaDataNascimento;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaEmail;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaTelefone;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastro;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastroResponse;
import br.com.discover.fidelidade.rest.v1.response.ErrorResponse;
import br.com.discover.fidelidade.rest.v1.response.UsuarioResponse;
import br.com.discover.fidelidade.rest.v1.resquest.UsuarioRequest;
import br.com.discover.fidelidade.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/usuario")
@Api(value = "usuario")
@Produces({MediaType.APPLICATION_JSON})
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Recupera Usuário pelo ID")
	@ApiResponses(value = {
		@ApiResponse(code = HttpStatus.SC_OK, message = "Usuário encontrado", response = UsuarioResponse.class),
		@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Usuário não encontrado"),
		@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Usuário inválido"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class)
	})
	public Response getUsuarioById(@PathParam("id") String id) {
		
		Integer idUsuario = null;
		
		try {
			idUsuario = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}
		
		Usuario usuario = usuarioService.recuperaUsuario(idUsuario);
		
		if (usuario == null) {
			return Response.status(HttpStatus.SC_NO_CONTENT).build();
		}
		
		return Response.status(HttpStatus.SC_OK).entity(new UsuarioResponse(usuario)).build();
	}
	
	@POST
	@Path("/cadastro")
	@ApiOperation(value = "Cadastra um Novo Usuário")
	@ApiResponses(value = {
		@ApiResponse(code = HttpStatus.SC_OK, message = "Usuário cadastrado", response = UsuarioResponse.class),
		@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Usuário não cadastrado"),
		@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro no cadastro de usuário"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class)
	})
	public Response saveUsuario(@RequestBody UsuarioRequest usuario) {
		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		usuario.setIdUsuario(GeradorId.getID());
		try {
			if (!ValidaCPF.isCPF(usuario.getCpf())) {
				ErrorCadastroResponse erroCpf = new ErrorCadastroResponse("cpf", "Cpf fornecido inválido");
				listaErros.add(erroCpf);
			}
			if (!ValidaDataNascimento.isDataNascimento(usuario.getDataNascimento())) {
				ErrorCadastroResponse erroDataNascimento = new ErrorCadastroResponse("dataNascimento", "Data de nascimento inserida inválida");
				listaErros.add(erroDataNascimento);
			}
			if (!ValidaEmail.isEmail(usuario.getEmail())) {
				ErrorCadastroResponse erroEmail = new ErrorCadastroResponse("email", "Email fornecido inválido");
				listaErros.add(erroEmail);
			}
			if (!ValidaTelefone.isTelefone(usuario.getTelefone())) {
				ErrorCadastroResponse erroTelefone = new ErrorCadastroResponse("telefone", "Telefone fornecido inválido");
				listaErros.add(erroTelefone);
			}
			if (!listaErros.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}
		
		usuarioService.saveUsuario(usuario);
		return Response.status(HttpStatus.SC_OK).entity(new UsuarioResponse(usuario)).build();
	}
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Atualiza um usuário já existente")
	@ApiResponses(value = {
		@ApiResponse(code = HttpStatus.SC_OK, message = "Usuário atualizado"),
		@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Usuário não atualizado"),
		@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na atualização de usuário"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class)
	})
	public Response updateUsuario(@RequestBody UsuarioRequest usuario, @PathParam("id") String id) {
		
		Integer idUsuario = null;
		
		try {
			idUsuario = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}
		
		usuario.setIdUsuario(idUsuario);
		
		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		
		if (usuario.getCpf() != null && !usuario.getCpf().isEmpty()) {
			if (!ValidaCPF.isCPF(usuario.getCpf())) {
				ErrorCadastroResponse erroCpf = new ErrorCadastroResponse("cpf", "Cpf fornecido inválido");
				listaErros.add(erroCpf);
			}
		}
		if (usuario.getDataNascimento() != null && !usuario.getDataNascimento().isEmpty()) {
			if (!ValidaDataNascimento.isDataNascimento(usuario.getDataNascimento())) {
				ErrorCadastroResponse erroDataNascimento = new ErrorCadastroResponse("dataNascimento", "Data de nascimento inserida inválida");
				listaErros.add(erroDataNascimento);
			}
		}
		if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
			if (!ValidaEmail.isEmail(usuario.getEmail())) {
				ErrorCadastroResponse erroEmail = new ErrorCadastroResponse("email", "Email fornecido inválido");
				listaErros.add(erroEmail);
			}
		}
		if (usuario.getTelefone() != null && !usuario.getTelefone().isEmpty()) {
			if (!ValidaTelefone.isTelefone(usuario.getTelefone())) {
				ErrorCadastroResponse erroTelefone = new ErrorCadastroResponse("telefone", "Telefone fornecido inválido");
				listaErros.add(erroTelefone);
			}
		}
		
		try {
			if (listaErros.isEmpty() == false) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}
		
		usuarioService.updateUsuario(usuario);
		return Response.status(HttpStatus.SC_OK).build();
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Deleta um usuário do sistema")
	@ApiResponses(value = {
		@ApiResponse(code = HttpStatus.SC_OK, message = "Usuário deletado"),
		@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Usuário não deletado"),
		@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na exclusão do cadastro do usuário"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class)
	})
	public Response deleteUsuario(@PathParam("id") String id) {
		Integer idUsuario = null;
		
		try {
			idUsuario = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}
		
		usuarioService.deleteUsuario(idUsuario);
		return Response.status(HttpStatus.SC_OK).build();
	}
}
