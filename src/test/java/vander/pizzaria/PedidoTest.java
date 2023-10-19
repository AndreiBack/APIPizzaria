package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.entity.Pedido;
import vander.pizzaria.entity.Pizza;
import vander.pizzaria.entity.Produto;
import vander.pizzaria.repository.PedidoRepository;
import vander.pizzaria.service.PedidoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class PedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
     void testGetAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());
        pedidos.add(new Pedido());

        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoService.getAllPedidos();

        assert result.size() == pedidos.size();
       verify(pedidoRepository, times(0)).save(any(Pedido.class));

    }

    @Test
     void testCreatePedido() {
        Pedido pedido = new Pedido();
        pedido.setQuantidade(2);
        pedido.setPizzas(new ArrayList<>());
        pedido.setProdutos(new ArrayList<>());

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido result = pedidoService.createPedido(pedido);

        assert result != null;
       verify(pedidoRepository, times(1)).save(any(Pedido.class));

    }

    @Test
     void testUpdatePedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setQuantidade(2);
        pedido.setPizzas(new ArrayList<>());
        pedido.setProdutos(new ArrayList<>());

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido updatedPedido = pedidoService.updatePedido(id, pedido);

        assert updatedPedido != null;
        assert updatedPedido.getId().equals(id);
       verify(pedidoRepository, times(1)).save(any(Pedido.class));

    }

    @Test
     void testDeletePedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        boolean result = pedidoService.deletePedido(id);

        assert result;
       verify(pedidoRepository, times(0)).save(any(Pedido.class));

    }
    @Test
     void testCalcularValorPedido() {
        Pedido pedido = new Pedido();

        List<Pizza> pizzas = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();

        Pizza pizza1 = new Pizza();
        pizza1.setValor(10.0);
        pizzas.add(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setValor(15.0);
        pizzas.add(pizza2);

        Produto produto1 = new Produto();
        produto1.setValor(5.0);
        produtos.add(produto1);

        Produto produto2 = new Produto();
        produto2.setValor(8.0);
        produtos.add(produto2);

        pedido.setPizzas(pizzas);
        pedido.setProdutos(produtos);

        double valorTotal = pedidoService.calcularValorPedido(pedido);

        assertEquals(38.0, valorTotal, 0.01);
    }
}
