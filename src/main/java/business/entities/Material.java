package business.entities;

import jdk.jfr.Category;

public class Material {
    private int material_id;
    private final String name;
    private double length;
    private final double width;
    private final double salesPrice;
    private final double costPrice;
    private int quantity;
    private int category;


    public Material(int material_id, String name, double length, double width, double price, double costPrice, int category) {
        this.material_id = material_id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.salesPrice = price;
        this.costPrice = costPrice;
        this.category = category;
    }
    public Material(String name, double length, double width, double price, double costPrice, int category) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.salesPrice = price;
        this.costPrice = costPrice;
        this.category = category;
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
        return salesPrice;
    }

    public void setLength(double length) {
        this.length = length;
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
