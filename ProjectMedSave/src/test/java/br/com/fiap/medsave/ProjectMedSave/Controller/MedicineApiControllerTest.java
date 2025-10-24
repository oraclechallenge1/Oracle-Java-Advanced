package br.com.fiap.medsave.ProjectMedSave.Controller;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Medicine;
import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.MedicineDTO;
import br.com.fiap.medsave.ProjectMedSave.service.MedicineService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("MedicineApiController")
public class MedicineApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicineService medicineService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        MedicineService medicineService() {
            return Mockito.mock(MedicineService.class);
        }
    }

    @Nested
    @DisplayName("GET /api/v1/medicines")
    class FindAll {

        @Test
        @DisplayName("Dado lista com itens, quando buscar, então retorna 200 e array com 2 elementos")
        void should_return_200_with_items() throws Exception {
            var m1 = new Medicine();
            m1.setId(1L);
            m1.setNameMedication("Dipirona");

            var m2 = new Medicine();
            m2.setId(2L);
            m2.setNameMedication("Amoxicilina");

            BDDMockito.given(medicineService.findAll()).willReturn(List.of(m1, m2));

            mockMvc.perform(get("/api/v1/medicines"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].nameMedication").value("Dipirona"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].nameMedication").value("Amoxicilina"));
        }

        @Test
        @DisplayName("Dado lista vazia, quando buscar, então retorna 200 e array vazio")
        void should_return_200_with_empty_list() throws Exception {
            BDDMockito.given(medicineService.findAll()).willReturn(List.of());

            mockMvc.perform(get("/api/v1/medicines"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json("[]"));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/medicines/{id}")
    class FindById {

        @Test
        @DisplayName("Dado um ID existente, então, quando buscar, retornar 200 e o DTO correto")
        void shouldReturnMedicineWhenFound() throws Exception {
            var id = 1L;
            var medicine = Medicine.builder()
                    .id(id)
                    .nameMedication("Dipirona")
                    .build();

            BDDMockito
                    .given(medicineService.findById(id))
                    .willReturn(Optional.of(medicine));

            mockMvc.perform(get("/api/v1/medicines/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.nameMedication").value("Dipirona"));
        }

        @Test
        @DisplayName("Dado m ID inexistente, quando realizar a busca, retornar código 404.")
        void should_return_404_when_medicine_not_found() throws Exception {
            var id = 1L;

            BDDMockito.given(medicineService.findById(id))
                    .willReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/medicines/{id}", id))
                    .andExpect(status().isNotFound());
        }
    }

    // post
    @Nested
    @DisplayName("POST /api/v1/medicines")
    class CreateMedicine {

        void should_create_and_return_201() throws Exception {
            List<Long> activeIngredientIds = Arrays.asList(1L, 2L);
            List<Long> pharmFormsIds = Arrays.asList(1L, 2L);
            var reg = MedicineDTO.toEntity(MedicineDTO.builder()
                    .nameMedication("Teste")
                    .statusMed("Ativo")
                    .categoryMedicineId(2L)
                    .activeIngredientIds(activeIngredientIds)
                    .pharmFormIds(pharmFormsIds)
                    .unitMeasureId(1L)
                    .build()
            );

            /* var created = MedicineDTO.builder()
                    .id(1L)
                    .
             */
        }
    }
    // removeById
    // putById

}
