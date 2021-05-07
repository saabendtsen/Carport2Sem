package business.persistence;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {

    private final Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }


    public List<Material> getMaterialByCategoryId(int materialCategory_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material JOIN material_has_material_category mhmc ON material.material_id = mhmc.material_id WHERE material_category_id = ?";
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



    public void calcMaterialList(){

    }

}
