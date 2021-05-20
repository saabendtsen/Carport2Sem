package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MaterialMapper {

    private final Database database;
    private final List<Material> stkliste = new ArrayList<>();


    public MaterialMapper(Database database) {
        this.database = database;
    }


    public List<Material> getMaterialByCategoryId(int materialCategory_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material JOIN material_has_material_category mhmc ON material.material_id = mhmc.material_id WHERE material_category_id = ? ORDER BY length";
            List<Material> materials = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialCategory_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("material_id");
                    String name = rs.getString("name");
                    double length = rs.getDouble("length");
                    double width = rs.getDouble("width");
                    double height = rs.getDouble("height");


                    //todo nye colonnenavne
                    double salesPrice = rs.getDouble("price");

                    //Kostpris Easymode. 30%
                    double costPrice = salesPrice * 0.7;

                    materials.add(new Material(id, name, length, width, height, salesPrice, costPrice, materialCategory_id));

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return materials;

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Material getMaterialByMaterialId(int material_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material\n" +
                    "JOIN material_has_material_category mh ON material.material_id = mh.material_id\n" +
                    "WHERE material.material_id=?";
            Material newMaterial = null;
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, material_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String name = rs.getString("name");
                    double length = rs.getDouble("length");
                    double width = rs.getDouble("width");
                    double height = rs.getDouble("height");
                    double salesPrice = rs.getDouble("price");

                    //Kostpris easymode
                    double costPrice = salesPrice * 0.7;
                    int materialCategory_id = rs.getInt("material_category_id");

                    newMaterial = new Material(material_id, name, length, width, height, salesPrice, costPrice, materialCategory_id);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return newMaterial;

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public List<Material> calcMaterialList(Order order) throws UserException {
        OrderFacade of = new OrderFacade(database);


        stkliste.clear();

        //Beregn rem længde
        remCalc(order);
        spærCalc(order);
        if (order.getShed().getClothing() != null) {
            beklædningCalc(order);
        }

        carportroofCalc(order);
        order.setStkListe(stkliste);
        //todo: insert stkliste i database på ordre

        of.insertIntoOrderHasMaterial(order);

        double salgsprice = 0;
        double costprice = 0;
        for (Material material : stkliste) {
            salgsprice += material.getPrice() * material.getQuantity();
            costprice += material.getCostPrice() * material.getQuantity();
        }

        BigDecimal costformat = new BigDecimal(costprice).setScale(2, RoundingMode.HALF_UP);
        BigDecimal saleformat = new BigDecimal(salgsprice).setScale(2, RoundingMode.HALF_UP);

        order.setSaleprice(saleformat.doubleValue());
        of.updateOrderSale(order, saleformat.doubleValue());

        order.setCostprice(costformat.doubleValue());
        of.updateOrderCost(order, costformat.doubleValue());

        return stkliste;
    }


    public void carportroofCalc(Order order) {

        //2cm overlap på hver side af tagpap

        //count length roof
        int counter = (int) Math.ceil(order.getCarport().getLength() / (order.getCarport().getRoof().getLength()) - 4);
        counter += (int) Math.ceil(order.getCarport().getWidth() / (order.getCarport().getRoof().getWidth()) - 4);
        order.getCarport().getRoof().setQuantity(counter);
        stkliste.add(order.getCarport().getRoof());
    }


    public void beklædningCalc(Order order) {

        //2cm overlap på hver side af beklædning
        int counter = (int) Math.ceil(order.getShed().getLength() / (order.getShed().getClothing().getWidth() - 4));
        counter += (int) Math.ceil(order.getShed().getWidth() / (order.getShed().getClothing().getWidth() - 4));

        counter *= 2;

        order.getShed().getClothing().setQuantity(counter);
        stkliste.add(order.getShed().getClothing());

    }

    public void spærCalc(Order order) throws UserException {
        //Beregn spær
        double rest = 0;
        List<Material> spærList = getMaterialByCategoryId(3);
        for (Material m : spærList) {
            if (m.getLength() < order.getCarport().getWidth()) {
                continue;
            } else {
                rest = m.getLength() - order.getCarport().getWidth();
                m.setLength(m.getLength() - rest);
                int counter = (int) ((order.getCarport().getLength() - m.getWidth()) / 55);
                m.setQuantity(counter);
                stkliste.add(m);
                break;
            }
        }

    }

    public void remCalc(Order order) throws UserException {
        List<Material> remList = getMaterialByCategoryId(4);
        double rest = 0;

        for (Material m : remList) {
            if (m.getLength() < order.getCarport().getLength()) {
                continue;
            } else {
                rest = m.getLength() - order.getCarport().getLength();
                m.setLength(m.getLength() - rest);
                if (order.getCarport().getWidth() > 500) {
                    m.setQuantity(3);
                    stkliste.add(m);
                    stolpeCalc(order, 3);
                    break;
                } else {
                    m.setQuantity(2);
                    stkliste.add(m);
                    stolpeCalc(order, 2);
                    break;
                }
            }
        }
    }

    public void stolpeCalc(Order order, int remCount) throws UserException {
        int counter = 0;
        //Beregn stolper pr rem
        List<Material> stolpeList = getMaterialByCategoryId(5);


        counter += 2;
        if (order.getCarport().getLength() - 90 >= 600) {
            counter += 2;
        } else if (order.getCarport().getLength() - 90 >= 300) {
            counter += 1;
        }
        counter *= remCount;

        if (order.getShed().getLength() > 0) {
            //There is a shed
            counter = 3;
        }


        stolpeList.get(0).setQuantity(counter);
        stkliste.add(stolpeList.get(0));
    }
}






