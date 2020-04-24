package training.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.model.Visit;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.VisitService;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

    VisitController visitController;

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(visitService, petService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void loadAllVisits() {
        //Given
        Pet pet = buildPet();
        when(petService.findById(anyLong())).thenReturn(pet);
        //When
        //Then
    }

    private Pet buildPet() {
        //Given
        Visit visit = Visit.builder().id(1L).date(LocalDate.of(2018, Month.DECEMBER, 12)).description("Cleaning nails").build();
        Visit visit2 = Visit.builder().id(2L).date(LocalDate.of(2019, Month.DECEMBER, 12)).description("Cleaning nails").build();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        visits.add(visit2);

        return Pet.builder()
                .id(1L)
                .owner(Owner.builder().id(1L).lastName("owner").firstName("Owner").build())
                .petType(PetType.builder().id(1L).name("Cat").build())
                .name("Puppy")
                .birthDate(LocalDate.of(2015, Month.APRIL, 24))
                .visits(visits)
                .build();
    }

    @Test
    void addVisit() throws Exception {
        //Given
        Pet pet = buildPet();
        when(petService.findById(anyLong())).thenReturn(pet);
        //When
        mockMvc.perform(get("/owners/1/pets/1/visits/add"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void updateVisit() throws Exception {
        //Given
        Visit visit = Visit.builder().id(1L).date(LocalDate.of(2018, Month.DECEMBER, 12)).description("Cleaning nails").build();
        Pet pet = buildPet();
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.findById(anyLong())).thenReturn(visit);
        //When
        mockMvc.perform(get("/owners/1/pets/1/visits/update/1"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(status().isOk());
        //Then
    }

    @Test
    void saveOrUpdateVisit() throws Exception {
        //Given
        //Given
        Pet pet = buildPet();
        when(petService.findById(anyLong())).thenReturn(pet);
        //When
        mockMvc.perform(post("/owners/1/pets/1/visits/save"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("redirect:/owners/details/1"))
                .andExpect(status().is3xxRedirection());

        //Then
        verify(visitService).save(any());
    }
}