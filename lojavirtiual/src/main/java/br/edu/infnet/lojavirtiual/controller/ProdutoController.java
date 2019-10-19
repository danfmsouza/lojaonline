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

import br.edu.infnet.lojavirtiual.model.Produto;
import br.edu.infnet.lojavirtiual.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProdutoController {

		@Autowired
		private  ProdutoService produtoService;
		@ApiOperation(value = "Retorna uma lista de produtos")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Lista retornada com sucesso"),
				@ApiResponse(code = 400, message = "Erro de chamada da API"),
				@ApiResponse(code = 500, message = "Falha no processamento da chamada")	
		})
		@RequestMapping(value = "/produtos", method = RequestMethod.GET, produces = "application/json")
		public List<Produto> listaTodos() {
			return produtoService.listaTodos();
		}
		@RequestMapping(value = "/produtos", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public Produto criar(@RequestBody Produto produto) {
			return produtoService.salvar(produto);
		}
		
		@RequestMapping(value = "/produtos/{id}" , method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
		public ResponseEntity<Produto> editar(@PathVariable(value="id")Integer id, @RequestBody Produto produto) {
			if(produtoService.obterPorId(id) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				Produto produtoAlterado = produtoService.alterar(id, produto);
				return new ResponseEntity<Produto>(produtoAlterado, HttpStatus.OK);
			}
			
		}
		@ApiOperation(value = "Remove um produto")
		@RequestMapping(value = "/produtos/{id}" , method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
		public ResponseEntity<Object> deletar(@PathVariable(value="id")Integer id) {
			Produto produto = produtoService.obterPorId(id);
			if(produto == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				produtoService.deletar(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		public ProdutoService getProdutoService() {
			return produtoService;
		}

		public void setProdutoService(ProdutoService produtoService) {
			this.produtoService = produtoService;
		}
}
