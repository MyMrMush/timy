package de.dhbw.ase.timy.domain.values;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Color {
    private int red;
    private int green;
    private int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color() {
        this(255, 255, 255);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return red == color.red && green == color.green && blue == color.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    public static Color fromString(String color) {
        if (color == null) {
            return null;
        }
        if (color.length() != 7) {
            throw new IllegalArgumentException("Color must be in the format #RRGGBB");
        }
        if (color.charAt(0) != '#') {
            throw new IllegalArgumentException("Color must start with #");
        }
        return new Color(
                Integer.parseInt(color.substring(1, 3), 16),
                Integer.parseInt(color.substring(3, 5), 16),
                Integer.parseInt(color.substring(5, 7), 16)
        );
    }
}
