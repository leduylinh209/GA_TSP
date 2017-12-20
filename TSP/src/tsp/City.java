package tsp;

public class City {
    private int x;
    private int y;
    private int ident;

    public City(int ident, int x, int y) {
        this.x = x;
        this.y = y;
        this.ident = ident;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(City city) {
        int xD = Math.abs(getX() - city.getX());
        int yD = Math.abs(getY() - city.getY());
        double distance = Math.sqrt(xD * xD + yD * yD);
        return distance;
    }

    @Override
    public String toString() {
        return String.valueOf(getIdent());
    }
}
