package vander.pizzaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vander.pizzaria.Entity.Pedido;
import vander.pizzaria.Repository.PedidoRepository;
import vander.pizzaria.Service.PedidoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class PedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());
        pedidos.add(new Pedido());

        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoService.getAllPedidos();

        assert result.size() == pedidos.size();
    }

    @Test
    public void testCreatePedido() {
        Pedido pedido = new Pedido();
        pedido.setQuantidade(2); // Exemplo de quantidade
        pedido.setPizzas(new ArrayList<>()); // Exemplo de lista de pizzas
        pedido.setProdutos(new ArrayList<>()); // Exemplo de lista de produtos

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido result = pedidoService.createPedido(pedido);

        assert result != null;
    }

    @Test
    public void testUpdatePedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setQuantidade(2); // Exemplo de quantidade
        pedido.setPizzas(new ArrayList<>()); // Exemplo de lista de pizzas
        pedido.setProdutos(new ArrayList<>()); // Exemplo de lista de produtos

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido updatedPedido = pedidoService.updatePedido(id, pedido);

        assert updatedPedido != null;
        assert updatedPedido.getId().equals(id);
    }

    @Test
    public void testDeletePedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        boolean result = pedidoService.deletePedido(id);

        assert result;
    }
}
