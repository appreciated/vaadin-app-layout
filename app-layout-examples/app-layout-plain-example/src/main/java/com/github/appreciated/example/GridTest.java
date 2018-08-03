package com.github.appreciated.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Route(value = "GridTest", layout = MainView.class)
public class GridTest extends VerticalLayout {
    List<Person> people = new ArrayList<>();

    public GridTest() {
        setSizeFull();
        Random random = new Random();
        for (int i = 0; i < 300; i++) {
            people.add(new Person(UUID.randomUUID().toString(), random.nextInt(), i));
        }
        setMargin(true);
        setSpacing(true);

        Grid<Person> grid = getGrid();
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        add(grid);
    }

    private Grid<Person> getGrid() {
        return getGrid(people.size());
    }

    private Grid<Person> getGrid(int size) {
        Grid<Person> grid = new Grid<>();
        grid.setItems(people.subList(0, size));
        grid.addColumn(Person::getName).setHeader("Name 1");
        grid.addColumn(Person::getName).setHeader("Name 2");
        grid.addColumn(Person::getName).setHeader("Name 3");
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
