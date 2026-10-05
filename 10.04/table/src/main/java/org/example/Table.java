package org.example;

public class Table {

    private int width, length, height, currentHeight;
    private boolean isAdjustable;
    private String color;
    private int numberOfLegs;

    public Table(int width, int length, int height, String color, int numberOfLegs) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.currentHeight = height;
        this.isAdjustable = false;
        this.color = color;
        this.numberOfLegs = numberOfLegs;
    }

    public Table(int width, int length, int height, int currentHeight, String color, int numberOfLegs) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.currentHeight = currentHeight;
        this.isAdjustable = true;
        this.color = color;
        this.numberOfLegs = numberOfLegs;
    }

    public void setHeight(int newHeight) {
        if (!isAdjustable) {
            throw new IllegalStateException("Table is not adjustable");
        }
        if (newHeight < 0 || newHeight > 200) {
            throw new IllegalArgumentException("Height must be between 0 and 200");
        }

        currentHeight = newHeight;
    }

    public int area() {
        return width * length;
    }

    public int getCapacity() {
        return getPerimeter() / 60;
    }

    public void repaint(String newColor) {
        color = newColor;
    }

    public boolean isStable() {
        return numberOfLegs >= 3;
    }

    public boolean isFoldable() {
        return isAdjustable && numberOfLegs >= 4;
    }

    public int getPerimeter() {
        return 2 * (width + length);
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public boolean isAdjustable() {
        return isAdjustable;
    }

    public String getColor() {
        return color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
}
