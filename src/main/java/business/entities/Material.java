package business.entities;


public class Material {
    private final String name;
    private final double width;
    private double salesPrice;
    private double costPrice;
    private final int category;
    private final int material_id;
    private double length;
    private double height;
    private int quantity;


    public Material(int material_id, String name, double length, double width, double height, double price, double costPrice, int category) {
        this.material_id = material_id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.salesPrice = price;
        this.costPrice = costPrice;
        this.category = category;
    }

    public double getHeight() {
        return height;
    }

    public double getCostPrice() {
        return costPrice;
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

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public double getPrice() {
        return salesPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory() {
        return category;
    }
}
