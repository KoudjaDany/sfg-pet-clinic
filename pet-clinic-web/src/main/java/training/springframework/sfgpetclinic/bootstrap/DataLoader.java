package training.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import training.springframework.sfgpetclinic.model.*;
import training.springframework.sfgpetclinic.services.*;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner = new Owner();
        owner.setFirstName("Michael");
        owner.setLastName("Weston");
        owner.setAddress("4N 24 Bonamoussadi");
        owner.setCity("Douala");
        owner.setTelephone("+237654987452");

        Pet mikesPet = new Pet();
        mikesPet.setName("Rosco");
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setBirthDate(LocalDate.of(2013,5,12));
        mikesPet.setOwner(owner);

        Pet fionasCat = new Pet();
        fionasCat.setOwner(owner);
        fionasCat.setBirthDate(LocalDate.of(2015, 3, 8));
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setName("Minou");

        owner.getPets().add(mikesPet);
        owner.getPets().add(fionasCat);
        owner = ownerService.save(owner);

        fionasCat = owner.getPets().stream().filter(pet -> "Minou".equals(pet.getName())).findFirst().get();

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneeze kitty");

        visitService.save(catVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kenfack");
        owner2.setLastName("Toto");
        owner2.setAddress("4N 24 Bonamoussadi");
        owner2.setCity("Douala");
        owner2.setTelephone("+237654987452");

        ownerService.save(owner2);

        System.out.println("Owners Loaded...");

        Vet vet = new Vet();
        vet.setFirstName("Sam");
        vet.setLastName("Axe");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        radiology.setName("Radiology");
        Specialty savedRadiology = specialityService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        surgery.setName("Surgery");
        Specialty savedSurgery = specialityService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        dentistry.setName("Dentistry");
        Specialty savedDentistry = specialityService.save(dentistry);

        vet.getSpecialties().add(savedSurgery);
        vet.getSpecialties().add(savedRadiology);
        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Valeruan");
        vet2.setLastName("Fudjit");

        vet2.getSpecialties().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Vets Loaded...");

        ownerService.showData();
        vetService.showData();
        petTypeService.showData();
        specialityService.showData();
        visitService.showData();
    }
}
