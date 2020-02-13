package training.springframework.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Pet;
import training.springframework.sfgpetclinic.model.PetType;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetMapService petMapService;
    PetTypeMapService petTypeMapService;

    final Long ownerId = 1L;
    final String toto = "Toto";

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(1L).build());
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(1L).petType(petTypeMapService.findById(1L)).build());
        //ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService = new OwnerMapService(petMapService, petTypeMapService);
        ownerMapService.save(Owner.builder().id(ownerId).lastName(toto).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());

    }

    @Test
    void save() {
        Owner owner = ownerMapService.save(Owner.builder().build());
        assertNotNull(owner);
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 1L;
        Owner owner = ownerMapService.save(Owner.builder().id(id).build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Owner owner = ownerMapService.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveWithPetsWithoutIdNorPetType() {
        //When
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().build());

        //Then
        assertThrows(RuntimeException.class, () -> ownerMapService.save(Owner.builder().pets(pets).build()));
    }

    @Test
    void saveWithPetsWithoutIdWithPetTypeNoId() {
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().petType(PetType.builder().build()).build());
        Owner owner = ownerMapService.save(Owner.builder().pets(pets).build());
        assertNotNull(owner);
        assertEquals(2, ownerMapService.getPetTypeService().findAll().size());
        assertEquals(2, petMapService.findAll().size());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveWithPetsWithoutIdWithPetTypeWithId() {
        Set<Pet> pets = new HashSet<>();
        PetType petType = petTypeMapService.findById(1L);
        pets.add(Pet.builder().petType(petType).build());
        Owner owner = ownerMapService.save(Owner.builder().pets(pets).build());
        assertNotNull(owner);
        assertEquals(1, ownerMapService.getPetTypeService().findAll().size());
        assertEquals(2, petMapService.findAll().size());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveWithPetsAndPetType() {
        Set<Pet> pets = new HashSet<>();
        pets.add(petMapService.findById(1L));
        Owner owner = ownerMapService.save(Owner.builder().pets(pets).build());
        assertNotNull(owner);
        assertEquals(1, ownerMapService.getPetTypeService().findAll().size());
        assertEquals(1, petMapService.findAll().size());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        Owner ownerToDelete = ownerMapService.findById(ownerId);
        ownerMapService.delete(ownerToDelete);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void getNextId() {
        Long nextId = ownerMapService.getNextId();
        assertEquals(2, nextId);
    }

    @Test
    void testSave() {

    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(toto);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner foo = ownerMapService.findByLastName("foo");
        assertNull(foo);
    }
}