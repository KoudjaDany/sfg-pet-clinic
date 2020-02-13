package training.springframework.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.springframework.sfgpetclinic.model.Pet;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;
    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        assertEquals(1L, petMapService.findAll().size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);
        assertNotNull(pet);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdNotExisting() {
        Pet pet = petMapService.findById(0L);
        assertNull(pet);
    }

    @Test
    void saveWithNoId() {
        Pet pet = petMapService.save(Pet.builder().build());
        assertNotNull(pet);
        assertEquals(2L, pet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveWithExistingId() {
        Pet pet = petMapService.save(Pet.builder().id(petId).build());
        assertNotNull(pet);
        assertEquals(petId, pet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void delete() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void getNextId() {
    }

    @Test
    void showData() {
    }
}