package com.github.appreciated.demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class View1 extends VerticalLayout {

    private ArrayList<Person> people = new ArrayList<>();

    public View1() {
        Random random = new Random();
        for (int i = 0; i < 300; i++) {
            people.add(new Person(UUID.randomUUID().toString(), random.nextInt(), i));
        }
        Grid<Person> grid = getGrid(30);
        grid.setSizeFull();
        add(grid);
        setSizeFull();
    }

    private Grid<Person> getGrid(int size) {
        Grid<Person> grid = new Grid<>();
       /* grid.clearSortOrder();
        grid.setItems(people.subList(0, size));
        grid.addColumn(Person::getName).setCaption("Name 1");
        grid.addColumn(Person::getName).setCaption("Name 2");
        grid.addColumn(Person::getName).setCaption("Name 3");
        grid.addColumn(Person::getName).setCaption("Name 4");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 1");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 2");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 3");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 4");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 5");
        grid.addColumn(Person::getBirthYear).setCaption("Year of birth 6");
        grid.addColumn(Person::getID).setCaption("ID");
        grid.addColumn(Person::getName).setCaption("Name 5");*/
        return grid;
    }

    class Person {
        private final String name;
        private final int id;
        private final int year;

        public Person(String name, int year, int id) {
            this.name = name;
            this.year = year;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getBirthYear() {
            return year;
        }

        public int getID() {
            return id;
        }
    }

}