package br.com.discover.fidelidade.rest.v1.resource;

import java.math.RoundingMode;
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

import br.com.discover.fidelidade.model.NotaFiscal;
import br.com.discover.fidelidade.rest.v1.Validacoes.GeradorId;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaCNPJ;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaData;
import br.com.discover.fidelidade.rest.v1.Validacoes.ValidaValor;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastro;
import br.com.discover.fidelidade.rest.v1.response.ErrorCadastroResponse;
import br.com.discover.fidelidade.rest.v1.response.ErrorResponse;
import br.com.discover.fidelidade.rest.v1.response.ListaResponse;
import br.com.discover.fidelidade.rest.v1.response.NotaFiscalResponse;
import br.com.discover.fidelidade.rest.v1.response.UsuarioResponse;
import br.com.discover.fidelidade.rest.v1.resquest.NotaFiscalRequest;
import br.com.discover.fidelidade.service.NotaFiscalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/notafiscal")
@Api(value = "notafiscal")
@Produces({ MediaType.APPLICATION_JSON })
public class NotaFiscalResource {
	
	@Autowired
	NotaFiscalService notaFiscalService;
	
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Recupera nota fiscal pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Nota fiscal encontrada", response = UsuarioResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Nota fiscal não encontrada"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Nota fiscal inválida"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response getNotaFiscalById(@PathParam("id") String id) {

		Integer idNotaFiscal = null;

		try {
			idNotaFiscal = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		NotaFiscal notaFiscal = notaFiscalService.recuperaNotaFiscal(idNotaFiscal);

		if (notaFiscal == null) {
			return Response.status(HttpStatus.SC_NO_CONTENT).build();
		}

		return Response.status(HttpStatus.SC_OK).entity(new NotaFiscalResponse(notaFiscal)).build();
	}
	
	@POST
	@Path("/cadastro")
	@ApiOperation(value = "Cadastra uma nova nota fiscal")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Nota fiscal cadastrada", response = UsuarioResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Nota fiscal não cadastrada"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro no cadastro da nota fiscal"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response saveNotaFiscal(@RequestBody NotaFiscalRequest notaFiscal) {
		
		notaFiscal.setIdNotaFiscal(GeradorId.getID());
		notaFiscal.setValor(notaFiscal.getValor().setScale(2, RoundingMode.HALF_DOWN));
		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		
		try {
			if (!ValidaCNPJ.isCNPJ(notaFiscal.getCnpj())) {
				ErrorCadastroResponse erroCnpj = new ErrorCadastroResponse("cnpj", "CNPJ fornecido inválido");
				listaErros.add(erroCnpj);
			}
			if (!ValidaData.isData(notaFiscal.getDataEmissao())) {
				ErrorCadastroResponse erroData = new ErrorCadastroResponse("dataEmissao", "Data de emissão fornecida inválida");
				listaErros.add(erroData);
			}
			if (!ValidaValor.isValor(notaFiscal.getValor())) {
				ErrorCadastroResponse erroValor = new ErrorCadastroResponse("valor", "Valor fornecido inválido");
				listaErros.add(erroValor);
			}
			if (listaErros.isEmpty() == false) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}

		notaFiscalService.saveNotaFiscal(notaFiscal);
		return Response.status(HttpStatus.SC_OK).entity(new NotaFiscalResponse(notaFiscal)).build();
	}
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Atualiza uma nota fiscal já existente")
	@ApiResponses(value = { @ApiResponse(code = HttpStatus.SC_OK, message = "Nota fiscal atualizada"),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Nota fiscal não atualizada"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na atualização da nota fiscal"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response updateNotaFiscal(@RequestBody NotaFiscalRequest notaFiscal, @PathParam("id") String id) {

		Integer idNotaFiscal = null;

		try {
			idNotaFiscal = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		notaFiscal.setIdNotaFiscal(idNotaFiscal);

		List<ErrorCadastroResponse> listaErros = new ArrayList<ErrorCadastroResponse>();
		
		if (notaFiscal.getCnpj() != null && !notaFiscal.getCnpj().isEmpty()) {
			if (!ValidaCNPJ.isCNPJ(notaFiscal.getCnpj())) {
				ErrorCadastroResponse erroCnpj = new ErrorCadastroResponse("cnpj", "CNPJ fornecido inválido");
				listaErros.add(erroCnpj);
			}
		}
		if (notaFiscal.getDataEmissao() != null && !notaFiscal.getDataEmissao().isEmpty()) {
			if (!ValidaData.isData(notaFiscal.getDataEmissao())) {
				ErrorCadastroResponse erroData = new ErrorCadastroResponse("dataEmissao", "Data de emissão fornecida inválida");
				listaErros.add(erroData);
			}
		}
		if (notaFiscal.getValor() != null) {
			if (!ValidaValor.isValor(notaFiscal.getValor())) {
				ErrorCadastroResponse erroValor = new ErrorCadastroResponse("valor", "Valor fornecido inválido");
				listaErros.add(erroValor);
			}
		}
		
		try {
			if (!listaErros.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).entity(new ErrorCadastro(listaErros)).build();
		}

		notaFiscalService.updateNotaFiscal(notaFiscal);
		return Response.status(HttpStatus.SC_OK).build();
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Deleta uma nota fiscal do sistema")
	@ApiResponses(value = { @ApiResponse(code = HttpStatus.SC_OK, message = "Nota fiscal deletada"),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Nota fiscal não deletada"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Erro na exclusão da nota fiscal"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response deleteNotaFiscal(@PathParam("id") String id) {
		Integer idNotaFiscal = null;

		try {
			idNotaFiscal = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		notaFiscalService.deleteNotaFiscal(idNotaFiscal);
		return Response.status(HttpStatus.SC_OK).build();
	}
	
	@GET
	@Path("/lista/{id}")
	@ApiOperation(value = "Recupera lista de notas fiscais pelo ID do usuário")
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatus.SC_OK, message = "Notas fiscais encontradas", response = NotaFiscalResponse.class),
			@ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = "Notas fiscais não encontradas"),
			@ApiResponse(code = HttpStatus.SC_BAD_REQUEST, message = "Notas fiscais inválidas"),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "Erro interno do servidor", response = ErrorResponse.class) })
	public Response getNotasFiscaisById(@PathParam("id") String id) {

		Integer idUsuario = null;

		try {
			idUsuario = new Integer(id);
		} catch (NumberFormatException e) {
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}

		List<NotaFiscal> listaEnderecos = new ArrayList<NotaFiscal>();
		listaEnderecos = notaFiscalService.recuperaNotasFiscais(idUsuario);

		if (listaEnderecos.isEmpty()) {
			return Response.status(HttpStatus.SC_NO_CONTENT).build();
		}

		return Response.status(HttpStatus.SC_OK).entity(new ListaResponse<NotaFiscal>(listaEnderecos)).build();
	}
}
