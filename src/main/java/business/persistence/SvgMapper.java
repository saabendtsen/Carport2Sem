package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.services.SVG;

import java.util.List;

public class SvgMapper {


    //This draws the entuire carport

    //TODO: Skal modtage en ordre
    public String drawCarport() {

        //String viewBox = "0 0 " + stkListe.getCarport().getWidth() + " " + order.getCarport().getLength();
        String viewBox = "0 0 " + 1080 + " " + 960;

        SVG svg = new SVG(0, 0, viewBox, 100, 100);

        svg.addRect(0, 0, 100, 100);
        svg.addLine(0, 0, 1000, 800, false);

/*
        for(Material m : stkListe){
            if(m.getCategory() == 3){
                svg.addRect(0,0,m.getWidth(),m.getLength());
            }
        }
*/

        return svg.toString();

    }
}
