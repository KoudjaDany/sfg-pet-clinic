package training.springframework.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.springframework.sfgpetclinic.model.PetType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(petTypeId).name("cat").build());
    }

    @Test
    void findAll() {
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void findById() {
        PetType petType = petTypeMapService.findById(petTypeId);
        assertNotNull(petType);
        assertEquals("cat", petType.getName());
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void save() {
        PetType petType = petTypeMapService.save(PetType.builder().build());
        assertNotNull(petType);
        assertNotNull(petType.getId());
        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        assertEquals(0, petTypeMapService.findAll().size());
    }
}