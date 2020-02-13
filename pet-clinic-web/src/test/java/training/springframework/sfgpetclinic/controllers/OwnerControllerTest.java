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
import training.springframework.sfgpetclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        when(ownerService.findAll()).thenReturn(owners);
        String viewName = ownerController.listOwners(model);
        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));
        verify(ownerService, atLeast(1)).findAll();
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("not-implemented"));
        verifyNoInteractions(ownerService);
    }
}