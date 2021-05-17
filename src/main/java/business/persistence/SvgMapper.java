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
        String viewBox = "0 0 1080 960";

        SVG svg = new SVG(0, 0, viewBox, 100, 100);

        svg.addRect(50, 50, 100, 100);
        svg.addLine(0, 0, 500, 500, false);

/*
        for(Material m : stkListe){
            //If spær
            if(m.getCategory() == 3){
                for(int i = 0; i < m.getQuantity(); i++)
                svg.addRect(0,0,m.getWidth(),m.getLength());

                //if rem
            } else if(m.getCategory() == 4){
                for(int i = 0; i < m.getQuantity(); i++)
                    svg.addRect(0,0,m.getWidth(),m.getLength());
                //if stolpe
            } else if(m.getCategory() == 5){
                for(int i = 0; i < m.getQuantity(); i++)
                    svg.addRect(0,0,m.getWidth(),m.getLength());
            }
        }
        */



        return svg.toString();

    }
}
