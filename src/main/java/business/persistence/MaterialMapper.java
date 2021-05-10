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

    public MaterialMapper(Database database) {
        this.database = database;
    }


    public List<Material> getMaterialByCategoryId(int materialCategory_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material JOIN material_has_material_category mhmc ON material.material_id = mhmc.material_id WHERE material_category_id = ? ORDER BY length";
            List<Material>  materials = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialCategory_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    double length = rs.getDouble("length");
                    double width = rs.getDouble("width");
                    double price = rs.getDouble("price");

                    materials.add(new Material(name,length,width,price));
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

        List<Material> stkliste = new ArrayList<>();

        //Beregn rem længde
        List<Material> materialList = getMaterialByCategoryId(2);
        double rest = 0;
        for(Material m : materialList){
            if (m.getLength()< order.getCarport().getLength()){
                continue;
            } else {
                rest = m.getLength() - order.getCarport().getLength();
                m.setLength(m.getLength()-rest);

                if(order.getCarport().getWidth() > 500){
                    stkliste.add(m);
                    stkliste.add(m);
                    stkliste.add(m);
                } else {
                    stkliste.add(m);
                    stkliste.add(m);
                }
            }
        }





        //Sort på længde, længst først.
        double rest = order.getCarport().getLength();
        remCalc(rest,materialList,stkliste);



    }
    public List<Material> remCalc (double rest, List<Material> materialList, List<Material> stkListe){

            for (int i = 0; i < materialList.size(); i++) {
                if (materialList.get(i).getLength() < rest) {
                    stkListe.add(materialList.get(i));
                    rest =- materialList.get(i).getLength();
                    remCalc(rest,materialList,stkListe);

                } else {
                    i++;

                }
            }
            return stkListe;
        }


        //Beregn antal spær + længde

        //Beregn antal stolper



        //Beregn antal beklædning til skur

        //


}
