package business.services;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;
import business.persistence.OrderMapper;

import java.util.List;

public class MaterialFacade {

    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public List<Material> getMaterialByCategoryId(int materialCategory_id) throws UserException {
       return materialMapper.getMaterialByCategoryId(materialCategory_id);
    }

    public List<Material> calcMaterialList(Order order) throws UserException{
        return materialMapper.calcMaterialList(order);
    }

}
