package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.services.SVG;

import java.util.List;

public class SvgMapper {


    //This draws the entuire carport

    //TODO: Skal modtage en ordre
    public String drawCarport(Order order) {

        String viewBox = "0 0 " + order.getCarport().getLength() * 1.5 + " " + order.getCarport().getWidth() * 1.5;
        SVG svg = new SVG(0, 0, viewBox, 100, 100);
        /*
        String viewBox = "0 0 1080 960";



        svg.addRect(50, 50, 100, 100);
        svg.addLine(0, 0, 500, 500, false);
*/

        svg.addRect(0, 0, order.getCarport().getWidth(), order.getCarport().getLength());

        for (Material m : order.getStkListe()) {

            //if rem
            if (m.getCategory() == 4) {
                if (m.getQuantity() == 2) {
                    svg.addRect(0, (int) (order.getCarport().getWidth() * 0.1), m.getWidth(), m.getLength());
                    svg.addRect(0, (int) (order.getCarport().getWidth() * 0.9), m.getWidth(), m.getLength());
                } else if (m.getQuantity() > 2) {
                    svg.addRect(0, (int) (order.getCarport().getWidth() * 0.1), m.getWidth(), m.getLength());
                    svg.addRect(0, (int) (order.getCarport().getWidth() * 0.9), m.getWidth(), m.getLength());
                    svg.addRect(0, (int) (order.getCarport().getWidth() * 0.5), m.getWidth(), m.getLength());
                }
            }

            //If spær
            if (m.getCategory() == 3) {
                svg.addRect(0, 0, order.getCarport().getWidth(), m.getWidth());
                svg.addRect((int) (order.getCarport().getLength() - m.getWidth()), 0, order.getCarport().getWidth(), m.getWidth());

                double space = order.getCarport().getLength() / m.getQuantity()-1 ;
                double x = space;


                for (int i = 0; i < m.getQuantity()-1; i++) {
                    svg.addRect((int)(x), 0, order.getCarport().getWidth(), m.getWidth());
                    x += space + m.getWidth();
                }

                //if stolpe
            } else if (m.getCategory() == 5) {
                for (int i = 0; i < m.getQuantity(); i++) {
                    // svg.addRect(0,0,m.getWidth(),m.getLength());
                }
            }
        }
        return svg.toString();

    }
}
