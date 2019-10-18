package br.edu.infnet.lojavirtiual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lojavirtiual.model.Produto;
import br.edu.infnet.lojavirtiual.service.ProdutoService;

@RestController
public class ProdutoController {

		@Autowired
		private  ProdutoService produtoService;
		@RequestMapping(value = "/produtos", method = RequestMethod.GET, produces = "application/json")
		public List<Produto> listaTodos() {
			return produtoService.listaTodos();
		}
		@RequestMapping(value = "/produtos", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public Produto criar(@RequestBody Produto produto) {
			return produtoService.salvar(produto);
		}
		
		@RequestMapping(value = "/produtos/{id}" , method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
		public Produto editar(@PathVariable(value="id")Integer id, @RequestBody Produto produto) {
			return produtoService.alterar(id, produto);
		}
		@RequestMapping(value = "/produtos/{id}" , method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
		public void deletar(@PathVariable(value="id")Integer id) {
			produtoService.deletar(id);
		}
		public ProdutoService getProdutoService() {
			return produtoService;
		}

		public void setProdutoService(ProdutoService produtoService) {
			this.produtoService = produtoService;
		}
}