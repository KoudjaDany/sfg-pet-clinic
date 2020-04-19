package training.springframework.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.repositories.OwnerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    private final String TOTO = "toto";
    private final Long ownerId = 1L;

    @InjectMocks
    OwnerSpringDataJpaService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    Owner ownerToReturn;

    @BeforeEach
    void setUp() {
        ownerToReturn = Owner.builder().id(ownerId).lastName(TOTO).build();

    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(ownerToReturn));
        Owner owner = ownerService.findByLastName(TOTO);
        assertEquals(1L, owner.getId());
        assertEquals(TOTO, owner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findByLastNameDoesNotExist() {
        //Owner owner = ownerService.findByLastName("toto");
        assertThrows(EntityNotFoundException.class,()->ownerService.findByLastName(TOTO));
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(ownerToReturn));
        Owner owner = ownerService.findById(ownerId);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        assertThrows(EntityNotFoundException.class,()->ownerService.findById(ownerId));
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(ownerToReturn);
        Owner owner = ownerService.save(Owner.builder().build());
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().build());
        ownerSet.add(Owner.builder().build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> owners = ownerService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void delete() {
        ownerService.delete(ownerToReturn);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(ownerId);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastNameLike() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().build());
        ownerSet.add(Owner.builder().build());

        when(ownerRepository.findByLastNameLike(anyString())).thenReturn(ownerSet);
        Set<Owner> owners = ownerService.findByLastNameLike(TOTO);
        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository).findByLastNameLike(anyString());
    }
}