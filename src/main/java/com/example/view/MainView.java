package com.example.view;

import com.example.util.HibernateUtil;
import com.example.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

    public void showMainMenu() {
        try (var scanner = new Scanner(System.in)) {

            var specialtyView = new SpecialtyView(scanner);
            var skillView = new SkillView(scanner);
            var developerView = new DeveloperView(scanner);

            boolean showMenu = true;
            LOGGER.info(Messages.GREETINGS.toString());
            while (showMenu) {
                LOGGER.info(Messages.MAIN_MENU.toString());
                switch (scanner.nextInt()) {
                    case 0 -> showMenu = false;
                    case 1 -> developerView.displayAllDevelopers();
                    case 2 -> developerView.createDevelopers();
                    case 3 -> developerView.showMenu();
                    case 4 -> skillView.showMenu();
                    case 5 -> specialtyView.showMenu();
                }
            }
        } catch (InputMismatchException exception) {
            LOGGER.info(Messages.BAD_INPUT.toString());
        } finally {
            HibernateUtil.shutdownSessionFactory();
        }
    }
}