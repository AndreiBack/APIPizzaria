package vander.pizzaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vander.pizzaria.controller.SaborController;
import vander.pizzaria.dto.SaborDTO;
import vander.pizzaria.service.SaborService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SaborControllerTest {

   @InjectMocks
   private SaborController controller;

   @Mock
   private SaborService service;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   void testGetAllSabores() {
      List<SaborDTO> sabores = new ArrayList<>();
      sabores.add(new SaborDTO());
      sabores.add(new SaborDTO());

      when(service.getAll()).thenReturn(sabores);

      ResponseEntity<List<SaborDTO>> response = controller.getAllSabores();

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(sabores, response.getBody());

      verify(service, times(1)).getAll();
   }

   @Test
   void testCreateSabor() {
      SaborDTO saborDTO = new SaborDTO();
      SaborDTO createdSabor = new SaborDTO();

      when(service.create(saborDTO)).thenReturn(createdSabor);

      ResponseEntity<SaborDTO> response = controller.createSabor(saborDTO);

      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(createdSabor, response.getBody());

      verify(service, times(1)).create(saborDTO);
   }

   @Test
   void testUpdateSabor() {
      Long saborId = 1L;
      SaborDTO saborDTO = new SaborDTO();
      SaborDTO updatedSabor = new SaborDTO();

      when(service.update(saborId, saborDTO)).thenReturn(updatedSabor);

      ResponseEntity<SaborDTO> response = controller.updateSabor(saborId, saborDTO);

      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(updatedSabor, response.getBody());

      verify(service, times(1)).update(saborId, saborDTO);
   }

   @Test
   void testUpdateSaborNotFound() {
      Long saborId = 1L;
      SaborDTO saborDTO = new SaborDTO();

      when(service.update(saborId, saborDTO)).thenReturn(null);

      ResponseEntity<SaborDTO> response = controller.updateSabor(saborId, saborDTO);

      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
      assertEquals(null, response.getBody());

      verify(service, times(1)).update(saborId, saborDTO);
   }

   @Test
   void testDeleteSabor() {
      Long saborId = 1L;

      when(service.delete(saborId)).thenReturn(true);

      ResponseEntity<Void> response = controller.deleteSabor(saborId);

      assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

      verify(service, times(1)).delete(saborId);
   }

   @Test
   void testDeleteSaborNotFound() {
      Long saborId = 1L;

      when(service.delete(saborId)).thenReturn(false);

      ResponseEntity<Void> response = controller.deleteSabor(saborId);

      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

      verify(service, times(1)).delete(saborId);
   }
}
