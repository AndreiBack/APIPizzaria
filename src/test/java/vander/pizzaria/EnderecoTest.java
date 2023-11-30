package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import vander.pizzaria.dto.EnderecoDTO;
import vander.pizzaria.entity.Endereco;
import vander.pizzaria.repository.EnderecoRepository;
import vander.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnderecoTest {
   @InjectMocks
   EnderecoService service;
   @Mock
   EnderecoRepository repository;
   @Mock
   ModelMapper modelMapper;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   void findById() {

      Long id = 1L;
      Endereco endereco = new Endereco();
      endereco.setId(id);
      endereco.setBairro("Bairro Teste");
      EnderecoDTO expectedDto = new EnderecoDTO();
      expectedDto.setId(id);
      expectedDto.setBairro("Bairro Teste");

      when(repository.findById(id)).thenReturn(Optional.of(endereco));
      when(modelMapper.map(endereco, EnderecoDTO.class)).thenReturn(expectedDto);


      EnderecoDTO result = service.findById(id);


      assertEquals(expectedDto, result);
   }

   @Test
   void findAll() {
      List<Endereco> enderecos = new ArrayList<>();
      enderecos.add(new Endereco());
      enderecos.add(new Endereco());

      List<EnderecoDTO> expectedDtos = new ArrayList<>();
      expectedDtos.add(new EnderecoDTO());
      expectedDtos.add(new EnderecoDTO());

      when(repository.findAll()).thenReturn(enderecos);
      when(modelMapper.map(any(Endereco.class), eq(EnderecoDTO.class))).thenReturn(new EnderecoDTO());

      List<EnderecoDTO> result = service.findAll();


      assertEquals(expectedDtos.size(), result.size());
   }

   @Test
   void create() {
      EnderecoDTO enderecoDTO = new EnderecoDTO();
      enderecoDTO.setBairro("Bairro Teste");
      enderecoDTO.setNumero(123);
      enderecoDTO.setCep("12345-678");
      enderecoDTO.setRua("Rua Teste");

      Endereco endereco = new Endereco();
      endereco.setBairro("Bairro Teste");
      endereco.setNumero(123);
      endereco.setCep("12345-678");
      endereco.setRua("Rua Teste");

      when(repository.save(any(Endereco.class))).thenReturn(endereco);

      String result = service.create(enderecoDTO);

      assertEquals("Sucesso ao cadastrar novo Registro", result);
   }


   @Test
   void delete() {
      Long id = 1L;
      Endereco endereco = new Endereco();
      endereco.setId(id);
      when(repository.findById(id)).thenReturn(Optional.of(endereco));
      service.delete(id);
      verify(repository, times(1)).deleteById(id);
   }
}