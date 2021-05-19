package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.services.SVG;

import java.util.List;

public class SvgMapper {


    //This draws the entuire carport

    //TODO: Skal modtage en ordre
    public String drawCarport(Order order) {

        String viewBox = "0 0 " + order.getCarport().getLength() + 100 + " " + order.getCarport().getWidth() + 100;
        SVG svg = new SVG(0, 0, viewBox, 100, 100);
        /*
        String viewBox = "0 0 1080 960";



        svg.addRect(50, 50, 100, 100);
        svg.addLine(0, 0, 500, 500, false);
*/

        Material rem = null;
        Material spær = null;
        Material stolpe = null;


        for (Material m : order.getStkListe()) {
            if (m.getCategory() == 4) {
                rem = m;
            } else if (m.getCategory() == 3) {
                spær = m;
            } else if (m.getCategory() == 5) {
                stolpe = m;
            }
        }

       // svg.addRect(0, 0, order.getCarport().getWidth(), order.getCarport().getLength());


        //Draw rem
        if (rem.getQuantity() == 2) {
            svg.addRect(0,  (order.getCarport().getWidth() * 0.1), rem.getWidth(), order.getCarport().getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.9), rem.getWidth(), order.getCarport().getLength());
        } else if (rem.getQuantity() > 2) {
            svg.addRect(0,  (order.getCarport().getWidth() * 0.1), rem.getWidth(), rem.getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.9), rem.getWidth(), rem.getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.5), rem.getWidth(), rem.getLength());
        }

        //Draw spær
        //Draw end spær
        svg.addRect((int) (order.getCarport().getLength() - spær.getWidth()), 0, order.getCarport().getWidth(), spær.getWidth());

        //Draw rest of Spær
        double space = order.getCarport().getLength() / spær.getQuantity();
        double x = 0;
        System.out.println(spær.getQuantity());
        for (int i = 0; i < spær.getQuantity(); i++) {
            if(i == spær.getQuantity()-1){
                svg.addRect(order.getCarport().getLength()- spær.getWidth(), 0, order.getCarport().getWidth(), spær.getWidth());
                i++;
            } else {

                svg.addRect((x), 0, order.getCarport().getWidth(), spær.getWidth());
                x += space + spær.getWidth();
            }
        }

        //if stolpe

        System.out.println(stolpe.getQuantity());
        if (stolpe.getQuantity() == 4) {
            svg.addRect((order.getCarport().getLength() * 0.25),(order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
            svg.addRect( (order.getCarport().getLength() * 0.75), (order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
            svg.addRect( (order.getCarport().getLength() * 0.25), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
            svg.addRect( (order.getCarport().getLength() * 0.75), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
        } else if (stolpe.getQuantity()  == 6 ){

        } else if (stolpe.getQuantity() == 8 && rem.getQuantity() == 2 ){
            //Foreste stolpe
            svg.addRect(order.getCarport().getLength() - 45,(order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
            //Bageste stolpe
            svg.addRect(45, order.getCarport().getWidth() * 0.1, stolpe.getWidth(), stolpe.getHeight());
            //midt sektion
            svg.addRect(((order.getCarport().getLength() - 90)/3) + 45, order.getCarport().getWidth() * 0.1, stolpe.getWidth(), stolpe.getHeight());
            svg.addRect((((order.getCarport().getLength() - 90)/3)*2) + 45, order.getCarport().getWidth() * 0.1, stolpe.getWidth(), stolpe.getHeight());

            //Foreste stolpe
            svg.addRect(order.getCarport().getLength() - 45,order.getCarport().getWidth() * 0.9 - rem.getWidth(), stolpe.getWidth(), stolpe.getHeight());
            //Bageste stolpe
            svg.addRect(45, order.getCarport().getWidth() * 0.9 - rem.getWidth(), stolpe.getWidth(), stolpe.getHeight());
            //midt sektion
            svg.addRect(((order.getCarport().getLength() - 90)/3) + 45, (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
            svg.addRect((((order.getCarport().getLength() - 90)/3)*2) + 45, (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
        }
        return svg.toString();

    }
}

