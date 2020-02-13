package training.springframework.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.PetType;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;
    final Long petId = 1L;
    final Owner owner = Owner.builder().id(1L).firstName("toto").build();
    final PetType petType = PetType.builder().id(1L).name("cat").build();

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).owner(owner).petType(petType).build());
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
        assertNotNull(pet.getId());
        assertEquals(2L, pet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveWithExistingId() {
        Pet pet = petMapService.save(Pet.builder().id(petId).build());
        assertNotNull(pet);
        assertNotNull(pet.getId());
        assertEquals(petId, pet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void delete() {
        Pet pet = petMapService.findById(1L);
        petMapService.delete(pet);
        assertNull(petMapService.findById(1L));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);
        assertNull(petMapService.findById(petId));
        assertEquals(0, petMapService.findAll().size());
    }

}