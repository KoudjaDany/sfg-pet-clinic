package training.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import training.springframework.sfgpetclinic.model.Owner;
import training.springframework.sfgpetclinic.model.Vet;
import training.springframework.sfgpetclinic.services.OwnerService;
import training.springframework.sfgpetclinic.services.VetService;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Michael");
        owner.setFirstName("Weston");

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Kenfack");
        owner2.setFirstName("Toto");

        ownerService.save(owner2);

        System.out.println("Owners Loaded...");

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Sam");
        vet.setLastName("Axe");

        vetService.save(vet);


        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Valeruan");
        vet2.setLastName("Fudjit");

        vetService.save(vet2);

        System.out.println("Vets Loaded...");
    }
}
