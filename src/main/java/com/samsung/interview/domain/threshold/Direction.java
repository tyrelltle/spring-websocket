package com.samsung.interview.domain.threshold;

public enum Direction {
    ASC ("ASC"),
    DESC ("DESC"),
    NONE ("NONE");

    private final String name;

    private Direction(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
