package training.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.PetType;
import training.springframework.sfgpetclinic.services.OwnerService;
import training.springframework.sfgpetclinic.services.PetService;
import training.springframework.sfgpetclinic.services.PetTypeService;
import training.springframework.sfgpetclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    public static final  String CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    @InjectMocks
    PetController petController;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    VisitService visitService;

    MockMvc mockMvc;

    Set<PetType> petTypes;
    Owner owner;
    Pet pet;


    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        petTypes = new HashSet<>();
        PetType cats = PetType.builder().id(1L).name("cats").build();
        petTypes.add(cats);
        petTypes.add(PetType.builder().id(2L).name("dogs").build());
        owner = Owner.builder().id(1L).pets(new HashSet<>()).build();
        pet = Pet.builder().id(1L).petType(cats).owner(owner).build();
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
    }



    @Test
    void getCreatePetForm() throws Exception {
        //Given

        //When
        mockMvc.perform(get("/owners/1/pets/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name(CREATE_OR_UPDATE_PET_FORM));

        //Then
        verify(ownerService).findById(anyLong());
        verify(petTypeService).findAll();

    }

    @Test
    void getUpdatePetForm() throws Exception {
        //Given
        when(petService.findById(anyLong())).thenReturn(pet);
        //When
        mockMvc.perform(get("/owners/1/pets/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name(CREATE_OR_UPDATE_PET_FORM));

        //Then
        verify(ownerService).findById(anyLong());
        verify(petService).findById(anyLong());
        verify(petTypeService).findAll();

    }

    @Test
    void createPet() throws Exception {
        //Given

        //When
        mockMvc.perform(post("/owners/1/pets/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        //Then
        verify(ownerService).findById(anyLong());
        verify(petService).save(any());
    }

    @Test
    void updatePet() throws Exception {
        //Given

        //When
        mockMvc.perform(post("/owners/1/pets/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("petTypes"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        //Then
        verify(petService).save(any());
    }
}