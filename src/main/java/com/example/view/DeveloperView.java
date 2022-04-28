package com.example.view;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.enums.Status;
import com.example.repository.DeveloperRepository;
import com.example.repository.impl.DeveloperRepositoryImpl;
import com.example.service.DeveloperService;
import com.example.service.impl.DeveloperServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperView.class);
    private final Scanner scanner;
    private final DeveloperService developerService;

    public DeveloperView(Scanner scanner) {
        this.scanner = scanner;
        DeveloperRepository repository = new DeveloperRepositoryImpl();
        developerService = new DeveloperServiceImpl(repository);
    }

    public void showMenu() {
        boolean showMenu = true;
        while (showMenu) {
            LOGGER.info("""
                                        
                    1.Display All Developers
                    2.Create Developer
                    3.Update Developer
                    4.Delete Developer
                    0.Exit
                    """);
            switch (scanner.nextInt()) {
                case 0 -> showMenu = false;
                case 1 -> displayAllDevelopers();
                case 2 -> createDevelopers();
                case 3 -> updateDevelopers();
                case 4 -> deleteDevelopers();
            }
        }
    }

    public void displayAllDevelopers() {
        List<Developer> allDevelopers = developerService.getAllDevelopers();
        if (allDevelopers.size() == 0) {
            LOGGER.info("We don't have any Developer. Create one first.");
        } else {
            LOGGER.info(allDevelopers.toString());
        }
    }

    public void createDevelopers() {
        LOGGER.info("Enter First name:");
        String firstName = scanner.nextLine();
        firstName = scanner.nextLine(); //FIXME
        LOGGER.info("Enter Last name:");
        String lastName = scanner.nextLine();
        LOGGER.info("Enter specialty:");
        String specialtyName = scanner.nextLine();
        Specialty specialty = new Specialty();
        specialty.setSpecialtyName(specialtyName);
        List<Skill> skills = new ArrayList<>();
        LOGGER.info("Enter skills, type quit for exit");
        while (true) {
            String skillName = scanner.nextLine();
            if ("quit".equals(skillName)) {
                break;
            }
            Skill skill = new Skill();
            skill.setSkillName(skillName);
            skills.add(skill);
        }
        Developer result = Developer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .specialty(specialty)
                .skills(skills)
                .status(Status.ACTIVE)
                .build();
        developerService.saveDeveloper(result);
    }

    private void updateDevelopers() {
        List<Developer> allDevelopers = developerService.getAllDevelopers();
        if (allDevelopers.size() == 0) {
            LOGGER.info("We don't have any Developer. Create one first.");
        } else {
            LOGGER.info(allDevelopers.toString());
            LOGGER.info("Enter the ID of the Developer you want to update:");
            int id = scanner.nextInt();
            LOGGER.info("Enter First name:");
            String firstName = scanner.nextLine();
            firstName = scanner.nextLine(); //FIXME
            LOGGER.info("Enter Last name:");
            String lastName = scanner.nextLine();
            LOGGER.info("Enter specialty:");
            String specialtyName = scanner.nextLine();
            Specialty specialty = new Specialty();
            specialty.setSpecialtyName(specialtyName);
            List<Skill> skills = new ArrayList<>();
            LOGGER.info("Enter skills, type quit for exit");
            while (true) {
                String skillName = scanner.nextLine();
                if ("quit".equals(skillName)) {
                    break;
                }
                Skill skill = new Skill();
                skill.setSkillName(skillName);
                skills.add(skill);
            }
            Developer result = Developer.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .specialty(specialty)
                    .skills(skills)
                    .status(Status.ACTIVE)
                    .build();
            developerService.updateDeveloper(result);
        }
    }

    private void deleteDevelopers() {
        List<Developer> allDevelopers = developerService.getAllDevelopers();
        if (allDevelopers.size() == 0) {
            LOGGER.info("We don't have any Developer. Create one first.");
        } else {
            LOGGER.info(allDevelopers.toString());
            LOGGER.info("Enter the ID of the Developer you want to delete:");
            int id = scanner.nextInt();
            developerService.deleteDeveloperById(id);
        }
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var devView = new DeveloperView(scanner);
            devView.showMenu();
        }
    }
}
