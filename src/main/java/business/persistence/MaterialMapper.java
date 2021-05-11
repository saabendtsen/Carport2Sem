package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
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


                    //todo nye colonnenavne
                    double salesPrice = rs.getDouble("price");
                    double costPrice = rs.getDouble("price");

                    materials.add(new Material(name, length, width, salesPrice, costPrice));

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return materials;

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public List<Material> calcMaterialList(Order order) throws UserException {

        stkliste.clear();

        //Beregn rem længde
        remCalc(order);
        spærCalc(order);
        //beklædningCalc(order);

        return stkliste;


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
                    stolpeCalc(order,3);

                } else {
                    m.setQuantity(2);
                    stkliste.add(m);
                    stolpeCalc(order,2);

                }
            }
        }
    }

    public void stolpeCalc(Order order, int remCount) throws UserException {
        int counter = 0;
        //Beregn stolper pr rem
        List<Material> stolpeList = getMaterialByCategoryId(5);

        if (order.getShed() == null) {
            counter += 2;
            if (order.getCarport().getLength() - 90 >= 600) {
                counter += 2;
            } else if (order.getCarport().getLength() - 90 >= 300) {
                counter += 1;
            }

        } else {
            //There is a shed
            counter += 3;
            if ((order.getCarport().getLength() - 45 - order.getShed().getLength()) > 300) {
                counter += 1;
            }
        }
            counter *= remCount;
            stolpeList.get(0).setQuantity(counter);
            stkliste.add(stolpeList.get(0));

    }

}





