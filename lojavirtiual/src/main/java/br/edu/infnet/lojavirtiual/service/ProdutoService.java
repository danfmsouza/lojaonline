package br.edu.infnet.lojavirtiual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import br.edu.infnet.lojavirtiual.model.Produto;

@Service
public class ProdutoService {
	private Map<Integer, Produto> produtos;
	private int count;
	
	public ProdutoService() {
		count = 1;
		produtos = new TreeMap<Integer, Produto>();
		produtos.put(count++, new Produto(1, "Galaxy S6", 2200.0));
		produtos.put(count++, new Produto(2, "Allstar", 200.0));
		produtos.put(count++, new Produto(3, "TV OLED", 1200.0));
	}

	public List<Produto> listaTodos() {
		return new ArrayList<Produto>(produtos.values());
	}

	public Produto salvar(Produto produto) {
		produto.setId(count);
		produtos.put(count++, produto);
		return produto;
	}

	public Produto alterar(Integer id, Produto produto) {
		produtos.put(id, produto);
		return produto;
	}

	public void deletar(Integer id) {
		produtos.remove(id);
	}
}
