package br.edu.infnet.lojavirtiual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import br.edu.infnet.lojavirtiual.model.Cliente;

@Service
public class ClienteService {
	private Map<Integer, Cliente> clientes;
	private int count;
	
	public ClienteService() {
//		count = 1;
//		clientes = new TreeMap<Integer, Cliente>();
//		clientes.put(count++, new Cliente(1, "Galaxy S6", 2200.0));
//		clientes.put(count++, new Cliente(2, "Allstar", 200.0));
//		clientes.put(count++, new Cliente(3, "TV OLED", 1200.0));
	}

	public List<Cliente> listaTodos() {
		return new ArrayList<Cliente>(clientes.values());
	}

	public Cliente salvar(Cliente cliente) {
		cliente.setId(count);
		clientes.put(count++, cliente);
		return cliente;
	}

	public Cliente alterar(Integer id, Cliente cliente) {
		clientes.put(id, cliente);
		return cliente;
	}

	public void deletar(Integer id) {
		clientes.remove(id);
	}

	public Cliente obterPorId(Integer id) {
		return clientes.get(id);
	}
}
