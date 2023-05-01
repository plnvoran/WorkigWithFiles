package ru.plnvoran.datamodel;

import java.util.List;

public class Person {
    private Integer id;
    private String name;
    private String description;
    private PersonalInfo personalInfo;
    private List<String> cities;
    private Boolean isGoodPerson;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public List<String> getCities() {
        return cities;
    }

    public Boolean getIsGoodPerson() {
        return isGoodPerson;
    }

}

