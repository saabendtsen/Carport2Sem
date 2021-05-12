package business.persistence;

import business.entities.Order;
import business.services.SVG;

public class SvgMapper {


    //This draws the entuire carport
    public String drawCarport(Order order){

        String viewBow = "0 0 " + order.getCarport().getWidth() + " " + order.getCarport().getLength();

        SVG svg = new SVG(0,0,viewBow,100,100);

        return svg.toString();

    }
}
