package training.springframework.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.repositories.OwnerRepository;
import training.springframework.sfgpetclinic.services.OwnerService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {OwnerService.class, OwnerRepository.class}) //FIXME find how to implement integration tests with junit5.
class OwnerSpringDataJpaServiceIT {

    OwnerService ownerService;

    @Autowired
    OwnerRepository ownerRepository;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        ownerService = new OwnerSpringDataJpaService(ownerRepository);
        owners.add(Owner.builder().id(1L).firstName("owner1 firstName").lastName("owner1 lastName").build());
        owners.add(Owner.builder().id(2L).firstName("owner2 firstName").lastName("owner2 lastName").build());
        owners.add(Owner.builder().id(3L).firstName("toto").lastName("titi").build());
    }

    @Test
    void showData() {
    }

    @Test
    void findByLastName() {

    }

    @Test
    void findByLastNameLike() {
        //Given
        //When
        Set<Owner> owners = ownerService.findByLastNameLike("");
        //Then
        assertEquals(owners.size(), this.owners.size());

        //When
         owners = ownerService.findByLastNameLike("owner");
        //Then
        assertEquals(2, owners.size());

        //When
         owners = ownerService.findByLastNameLike("toto");
        //Then
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}