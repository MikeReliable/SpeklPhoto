package sample;

import java.util.Objects;

public class Point {

    double coordinateX;
    double coordinateY;
    double Exx;
    int number;

    public Point(double coordinateX, double coordinateY, double Exx, int number) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.Exx = Exx;
        this.number = number;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getExx() {
        return Exx;
    }

    public void setExx(double exx) {
        Exx = exx;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return coordinateX + " " + coordinateY + " " + Exx + " " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.coordinateX, coordinateX) == 0 && Double.compare(point.coordinateY, coordinateY) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }
}
