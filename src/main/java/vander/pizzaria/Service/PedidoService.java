package vander.pizzaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vander.pizzaria.Entity.Pedido;
import vander.pizzaria.Entity.Pizza;
import vander.pizzaria.Entity.Produto;
import vander.pizzaria.Repository.PedidoRepository;

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
        calcularValorPedido(pedido); // Calcula o valor total do pedido
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
            pedidoToUpdate.setValor(valorTotalPedido);

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

    private double calcularValorPedido(Pedido pedido) {
        double totalValue = 0.0;

        // Somar valores das pizzas
        for (Pizza pizza : pedido.getPizzas()) {
            totalValue += pizza.getValor();
        }

        // Somar valores dos produtos
        for (Produto produto : pedido.getProdutos()) {
            totalValue += produto.getValorTotal();
        }

        pedido.setValor(totalValue);
        return totalValue;
    }
}