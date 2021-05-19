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
        System.out.println("Rem: " + rem.getQuantity());
        System.out.println("Rem længde: " + rem.getLength());
        if (rem.getQuantity() == 2) {
            svg.addRect(0,  (order.getCarport().getWidth() * 0.1), rem.getWidth(), order.getCarport().getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.9), rem.getWidth(), order.getCarport().getLength());
        } else if (rem.getQuantity() > 2) {
            svg.addRect(0,  (order.getCarport().getWidth() * 0.1), rem.getWidth(), order.getCarport().getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.5), rem.getWidth(), order.getCarport().getLength());
            svg.addRect(0,  (order.getCarport().getWidth() * 0.9), rem.getWidth(), order.getCarport().getLength());
        }

        //draw first
        svg.addRect(0,0,order.getCarport().getWidth(), spær.getWidth());

        //Draw rest of Spær
        double space = (order.getCarport().getLength()-spær.getWidth()) / (spær.getQuantity()-1);

        double x = 0;
        for (int i = 0; i < spær.getQuantity()-1; i++) {
                svg.addRect(space+x, 0, order.getCarport().getWidth(), spær.getWidth());
                x += space;
        }

        //if stolpe


        System.out.println("Stolper: " + stolpe.getQuantity());
        double lengthBetweenPosts = 0;
        if(rem.getQuantity() == 2 && order.getCarport().getLength() < 390) {
            for (int i = 0; i < stolpe.getQuantity() / rem.getQuantity(); i++) {
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                lengthBetweenPosts += order.getCarport().getLength() - 90;
            }
        }else if(rem.getQuantity() == 2 && order.getCarport().getLength() >= 390 && order.getCarport().getLength() < 690){
                for(int i = 0; i < stolpe.getQuantity()/ rem.getQuantity(); i++){
                    svg.addRect((45 + lengthBetweenPosts),(order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                    svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                    lengthBetweenPosts += (order.getCarport().getLength()-90)/2;
                }
        }else if(rem.getQuantity() == 2 && order.getCarport().getLength() >= 690){
            for(int i = 0; i < stolpe.getQuantity()/ rem.getQuantity(); i++){
                svg.addRect((45 + lengthBetweenPosts),(order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                lengthBetweenPosts += (order.getCarport().getLength()-90)/3;
            }
        }else if(rem.getQuantity() == 3 && order.getCarport().getLength() < 390) {
            for (int i = 0; i < stolpe.getQuantity() / rem.getQuantity(); i++) {
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.5), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                lengthBetweenPosts += order.getCarport().getLength() - 90;
            }
        } else if(rem.getQuantity() == 3 && order.getCarport().getLength() < 690) {
            for (int i = 0; i < stolpe.getQuantity() / rem.getQuantity(); i++) {
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.5), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                lengthBetweenPosts += (order.getCarport().getLength() - 90) / 2;
            }
        }else if(rem.getQuantity() == 3 && order.getCarport().getLength() >= 690) {
            for (int i = 0; i < stolpe.getQuantity() / rem.getQuantity(); i++) {
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.1), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.5), stolpe.getWidth(), stolpe.getHeight());
                svg.addRect((45 + lengthBetweenPosts), (order.getCarport().getWidth() * 0.9 - rem.getWidth()), stolpe.getWidth(), stolpe.getHeight());
                lengthBetweenPosts += (order.getCarport().getLength() - 90) / 3;
            }
        }


        if(order.getShed().getLength() > 0){
            svg.addLine(order.getCarport().getLength()-order.getShed().getLength(),order.getCarport().getWidth() * 0.9, order.getCarport().getLength()-order.getShed().getLength(),order.getCarport().getWidth() * 0.9 - order.getShed().getWidth(),false);
        }

        return svg.toString();


    }
}

