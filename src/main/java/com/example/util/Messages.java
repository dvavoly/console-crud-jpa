package com.example.util;

public enum Messages {

    GREETINGS("Hello,\nIn this program, you can create or view a Developer with skills and specialty."),
    MAIN_MENU("""
            Choose an option:
            1:Display available Developers?
            2:Create a new Developer?
            3:CRUD operations on the Developer?
            4:CRUD operations on the skill?
            5:CRUD operations on the specialty?
            0:Exit.
            Select an option:"""),
    BAD_INPUT("Bad input."),
    ID_CANNOT_BE_NULL("Id cannot be null"),
    SPECIALTY_CANNOT_BE_NULL_OR_EMPTY("Specialty cannot be null or empty name"),
    SKILL_CANNOT_BE_NULL_OR_EMPTY("Skill cannot be null or empty name");
    private final String value;

    Messages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}