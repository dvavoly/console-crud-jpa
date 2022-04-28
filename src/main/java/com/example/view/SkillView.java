package com.example.view;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.repository.impl.JpaSkillRepositoryImpl;
import com.example.service.SkillService;
import com.example.service.impl.SkillServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillView.class);
    private final Scanner scanner;

    private final SkillService skillService;

    public SkillView(Scanner scanner) {
        this.scanner = scanner;
        SkillRepository repository = new JpaSkillRepositoryImpl();
        skillService = new SkillServiceImpl(repository);
    }

    public void showMenu() {
        boolean showMenu = true;
        while (showMenu) {
            LOGGER.info("""
                                        
                    1.Display All skills
                    2.Create skill
                    3.Update skill
                    4.Delete skill
                    0.Exit
                    """);
            switch (scanner.nextInt()) {
                case 0 -> showMenu = false;
                case 1 -> displayAllSkills();
                case 2 -> createSkill();
                case 3 -> updateSkill();
                case 4 -> deleteSkill();
            }
        }
    }

    private void displayAllSkills() {
        List<Skill> allSkills = skillService.getAllSkills();
        if (allSkills.size() == 0) {
            LOGGER.info("We don't have any skill. Create one first.");
        } else {
            LOGGER.info(allSkills.toString());
        }
    }

    private void createSkill() {
        LOGGER.info("Enter skill");
        String skillName = scanner.nextLine();
        skillName = scanner.nextLine();
        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skillService.saveSkill(skill);
    }

    private void updateSkill() {
        List<Skill> allSkills = skillService.getAllSkills();
        if (allSkills.size() == 0) {
            LOGGER.info("We don't have any skill. Create one first.");
        } else {
            LOGGER.info(allSkills.toString());
            LOGGER.info("Enter the ID of the skill you want to update:");
            int id = scanner.nextInt();
            LOGGER.info("Enter the new skill name:");
            String name = scanner.nextLine(); // FIXME
            name = scanner.nextLine();
            Skill skill = new Skill(id, name);
            skillService.updateSkill(skill);

        }
    }

    private void deleteSkill() {
        List<Skill> allSkills = skillService.getAllSkills();
        if (allSkills.size() == 0) {
            LOGGER.info("We don't have any skill. Create one first.");
        } else {
            LOGGER.info(allSkills.toString());
            LOGGER.info("Enter the ID of the skill you want to delete:");
            int id = scanner.nextInt();
            skillService.deleteSkillById(id);
        }
    }
}
