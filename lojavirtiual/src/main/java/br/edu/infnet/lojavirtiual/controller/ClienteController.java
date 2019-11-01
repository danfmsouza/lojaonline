package br.edu.infnet.lojavirtiual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lojavirtiual.model.Cliente;
import br.edu.infnet.lojavirtiual.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ClienteController {

		@Autowired
		private  ClienteService clienteService;
		@ApiOperation(value = "Retorna uma lista de clientes")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Lista retornada com sucesso"),
				@ApiResponse(code = 400, message = "Erro de chamada da API"),
				@ApiResponse(code = 500, message = "Falha no processamento da chamada")	
		})
		@RequestMapping(value = "/clientes", method = RequestMethod.GET, produces = "application/json")
		public List<Cliente> listaTodos() {
			return clienteService.listaTodos();
		}
		@RequestMapping(value = "/clientes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public Cliente criar(@RequestBody Cliente cliente) {
			return clienteService.salvar(cliente);
		}
		
		@RequestMapping(value = "/clientes/{id}" , method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
		public ResponseEntity<Cliente> editar(@PathVariable(value="id")Integer id, @RequestBody Cliente cliente) {
			if(clienteService.obterPorId(id) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				Cliente clienteAlterado = clienteService.alterar(id, cliente);
				return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
			}
			
		}
		@ApiOperation(value = "Remove um cliente")
		@RequestMapping(value = "/clientes/{id}" , method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
		public ResponseEntity<Object> deletar(@PathVariable(value="id")Integer id) {
			Cliente cliente = clienteService.obterPorId(id);
			if(cliente == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				clienteService.deletar(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		public ClienteService getClienteService() {
			return clienteService;
		}

		public void setClienteService(ClienteService clienteService) {
			this.clienteService = clienteService;
		}
}
