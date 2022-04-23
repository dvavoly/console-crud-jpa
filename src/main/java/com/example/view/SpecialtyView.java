package com.example.view;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.repository.jpa.JpaSpecialtyRepositoryImpl;
import com.example.service.SpecialtyService;
import com.example.service.impl.SpecialtyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialtyView.class);
    private final Scanner scanner;
    private final SpecialtyService specialtyService;

    public SpecialtyView(Scanner scanner) {
        this.scanner = scanner;
        SpecialtyRepository repository = new JpaSpecialtyRepositoryImpl();
        specialtyService = new SpecialtyServiceImpl(repository);
    }

    public void showMenu() {
        boolean showMenu = true;
        while (showMenu) {
            LOGGER.info("""
                                        
                    1.Display All specialty
                    2.Create specialty
                    3.Update specialty
                    4.Delete specialty
                    0.Exit
                    """);
            switch (scanner.nextInt()) {
                case 0 -> showMenu = false;
                case 1 -> displayAllSpecialties();
                case 2 -> createSpecialty(scanner);
                case 3 -> updateSpecialty(scanner);
                case 4 -> deleteSpecialty(scanner);
            }
        }
    }

    private void displayAllSpecialties() {
        List<Specialty> allSpecialties = specialtyService.getAllSpecialties();
        if (allSpecialties.size() == 0) {
            LOGGER.info("We don't have any specialty. Create one first.");
        } else {
            LOGGER.info(allSpecialties.toString());
        }
    }

    public void createSpecialty(Scanner scanner) {
        LOGGER.info("Enter specialty");
        String specialtyName = scanner.nextLine(); //FIXME
        specialtyName = scanner.nextLine();
        Specialty specialty = new Specialty();
        specialty.setSpecialtyName(specialtyName);
        specialtyService.saveSpecialty(specialty);
    }

    private void updateSpecialty(Scanner scanner) {
        List<Specialty> allSpecialties = specialtyService.getAllSpecialties();
        if (allSpecialties.size() == 0) {
            LOGGER.info("We don't have any specialty. Create one first.");
        } else {
            LOGGER.info(allSpecialties.toString());
            LOGGER.info("Enter the ID of the specialty you want to update:");
            int id = scanner.nextInt();
            LOGGER.info("Enter the new specialty name:");
            String name = scanner.nextLine(); // FIXME
            name = scanner.nextLine();
            Specialty specialty = new Specialty(id, name);
            specialtyService.updateSpecialty(specialty);
        }
    }

    private void deleteSpecialty(Scanner scanner) {
        List<Specialty> allSpecialties = specialtyService.getAllSpecialties();
        if (allSpecialties.size() == 0) {
            LOGGER.info("We don't have any specialty. Create one first.");
        } else {
            LOGGER.info(allSpecialties.toString());
            LOGGER.info("Enter the ID of the specialty you want to remove:");
            int id = scanner.nextInt();
            specialtyService.deleteSpecialtyById(id);
        }
    }
}
