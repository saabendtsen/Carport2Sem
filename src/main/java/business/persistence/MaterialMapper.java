package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialMapper {

    private final Database database;
    private List<Material> stkliste = new ArrayList<>();


    public MaterialMapper(Database database) throws UserException {
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
                    String name = rs.getString("name");
                    double length = rs.getDouble("length");
                    double width = rs.getDouble("width");
                    double price = rs.getDouble("price");

                    materials.add(new Material(name, length, width, price));
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return materials;

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public void calcMaterialList(Order order) throws UserException {

        stkliste.clear();

        //Beregn rem længde
        remCalc(order);
        spærCalc(order);



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

                int counter = (int) (order.getCarport().getLength() / 55);
                for (int i = 0; i < counter; i++) {
                    stkliste.add(m);
                }
            }
        }

    }

    public void remCalc(Order order) throws UserException {
        List<Material> remList = getMaterialByCategoryId(2);
        double rest = 0;

        for (Material m : remList) {
            if (m.getLength() < order.getCarport().getLength()) {
                continue;
            } else {
                rest = m.getLength() - order.getCarport().getLength();
                m.setLength(m.getLength() - rest);
                if (order.getCarport().getWidth() > 500) {
                    for (int i = 0; i < 3; i++) {
                        stkliste.add(m);
                        stolpeCalc(order);
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        stkliste.add(m);
                        stolpeCalc(order);
                    }
                }
            }
        }
    }

    public void stolpeCalc(Order order) throws UserException {
        int counter=0;
        //Beregn stolper pr rem
        List<Material> stolpeList = getMaterialByCategoryId(4);

        if (order.getShed() == null) {
            counter += 2;
            if(order.getCarport().getLength() - 90 >= 600){
                counter += 2;
            } else if (order.getCarport().getLength() - 90 >= 300){
                counter += 1;
            }

        } else {
            //There is a shed
            counter += 3;
            if((order.getCarport().getLength() - 45 - order.getShed().getLength()) > 300){
                counter += 1;
            }
        }
        for (int i = 0; i < counter; i++) {
            stkliste.add(stolpeList.get(0));
        }
    }

}





