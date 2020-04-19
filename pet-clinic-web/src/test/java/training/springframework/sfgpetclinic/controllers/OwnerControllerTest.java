package training.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.services.OwnerService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    MockMvc mockMvc;

    Set<Owner> owners;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().build());
        owners.add(Owner.builder().build());
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);
        String viewName = ownerController.listOwners(model);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));
        verify(ownerService, atLeast(1)).findAll();
    }

    @Test
    void listOwnersByIndex() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);
        String viewName = ownerController.listOwners(model);
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));
        verify(ownerService, atLeast(1)).findAll();
    }

    @Test
    void listOwnersBy() throws Exception {

        //Given
        when(ownerService.findAll()).thenReturn(owners);
        String viewName = ownerController.listOwners(model);

        //When
        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));

        //Then
        verify(ownerService, atLeast(1)).findAll();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        //Given
        when(ownerService.findByLastNameLike(anyString())).thenReturn(owners);
        //When
        mockMvc.perform(get("/owners/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));
        //Then

    }

    @Test
    void processFindFormReturnOne() throws Exception {
        //Given
        when(ownerService.findByLastNameLike(anyString())).thenReturn(Collections.singleton(Owner.builder().id(1L).lastName("lastName").build()));
        //When
        mockMvc.perform(get("/owners/search"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        //Then
    }

    @Test
    void showOwner() throws Exception {
        //Given
        Owner owner = Owner.builder().id(1L).lastName("owner").firstName("owner").build();
        when(ownerService.findById(anyLong())).thenReturn(owner);

        //When
        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/ownerDetails"));
        //Then
        verify(ownerService,  only()).findById(anyLong());
    }

    @Test
    void initCreateForm() throws Exception {
        mockMvc.perform(get("/owners/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreateForm() throws Exception {
        //Given
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        //When
        mockMvc.perform(post("/owners/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("redirect:/owners/1"));
        //Then
        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        //Given
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        //When
        mockMvc.perform(get("/owners/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
        //then
        verify(ownerService).findById(anyLong());
    }

    @Test
    void processUpdateForm() throws Exception {
        //Given
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        //When
        mockMvc.perform(post("/owners/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("redirect:/owners/1"));
        //Then
        verify(ownerService).save(ArgumentMatchers.any());
    }
}