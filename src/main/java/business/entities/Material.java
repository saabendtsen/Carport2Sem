package business.entities;

public class Material {
    private int material_id;
    private final String name;
    private final double length;
    private final double width;
    private final double price;

    public Material(int material_id, String name, double length, double width, double price) {
        this.material_id = material_id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.price = price;
    }
    public Material(String name, double length, double width, double price) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.price = price;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return price;
    }
}
