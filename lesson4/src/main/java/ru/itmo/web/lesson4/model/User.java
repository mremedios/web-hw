package ru.itmo.web.lesson4.model;

public class User {

    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");

        private final String colorName;

        Color(String colorName) {
            this.colorName = colorName;
        }

        @Override
        public String toString() {
            return colorName;
        }
    }



    private final long id;
    private final String handle;
    private final String name;
    private final Color color;

    public User(long id, String handle, String name, Color color) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }
    public String getColor() {
        return color.toString();
    }
}
