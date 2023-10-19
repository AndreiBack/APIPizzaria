package vander.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.entity.Pedido;
import vander.pizzaria.entity.Pizza;
import vander.pizzaria.entity.Produto;
import vander.pizzaria.repository.PedidoRepository;

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
        calcularValorPedido(pedido);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isPresent()) {
            Pedido pedidoToUpdate = existingPedido.get();
            pedidoToUpdate.setQuantidade(pedido.getQuantidade());
            pedidoToUpdate.setFuncionario(pedido.getFuncionario());
            pedidoToUpdate.setPizzas(pedido.getPizzas());
            pedidoToUpdate.setProdutos(pedido.getProdutos());

            double valorTotalPedido = calcularValorPedido(pedidoToUpdate);
            pedidoToUpdate.setValorTotal(valorTotalPedido);

            return pedidoRepository.save(pedidoToUpdate);
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

        for (Pizza pizza : pedido.getPizzas()) {
            System.out.println("Pizza valor: " + pizza.getValor()); // Debug statement
            totalValue += pizza.getValor();
        }

        for (Produto produto : pedido.getProdutos()) {
            System.out.println("Produto valor: " + produto.getValor()); // Debug statement
            totalValue += produto.getValor();
        }
        totalValue *= pedido.getQuantidade();
        pedido.setValorTotal(totalValue);
        return totalValue;
    }
}