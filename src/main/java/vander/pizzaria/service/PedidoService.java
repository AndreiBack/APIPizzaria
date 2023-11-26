package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.entity.Pedido;
import vander.pizzaria.entity.Pizza;
import vander.pizzaria.entity.Produto;
import vander.pizzaria.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido createPedido(Pedido pedido) {
        if (pedido.getProdutos() == null) {
            pedido.setProdutos(new ArrayList<>());
        }
        calcularValorPedido(pedido);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isPresent()) {
            Pedido pedidoToUpdate = existingPedido.get();
            pedidoToUpdate.setFuncionario(pedido.getFuncionario());
            pedidoToUpdate.setStatus(pedido.getStatus());
            pedidoToUpdate.setPizzas(pedido.getPizzas());
            pedidoToUpdate.setProdutos(pedido.getProdutos());
            pedidoToUpdate.setObservacao(pedido.getObservacao());
            double valorTotalPedido = calcularValorPedido(pedidoToUpdate);
            pedidoToUpdate.setValorTotal(valorTotalPedido);

            return pedidoRepository.save(pedidoToUpdate);
        }
        if (pedido.getProdutos() == null) {
            pedido.setProdutos(new ArrayList<>());
        }
        return null;
    }

    public boolean deletePedido(Long id) {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public double calcularValorPedido(Pedido pedido) {
        double totalValue = 0.0;

        System.out.println("Pedido: " + pedido); // Debug statement

        List<Pizza> pizzas = pedido.getPizzas();
        if (pizzas != null) {
            for (Pizza pizza : pizzas) {
                System.out.println("Pizza valor: " + pizza.getValor()); // Debug statement
                totalValue += pizza.getValor();
            }
        }

        List<Produto> produtos = pedido.getProdutos();
        if (produtos != null) {
            for (Produto produto : produtos) {
                System.out.println("Produto valor: " + produto.getValor()); // Debug statement
                totalValue += produto.getValor();
            }
        }

        pedido.setValorTotal(totalValue);
        return totalValue;
    }
}