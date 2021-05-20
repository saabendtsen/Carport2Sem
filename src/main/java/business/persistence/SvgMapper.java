package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.services.SVG;

import java.util.List;

public class SvgMapper {


    //This draws the entuire carport

    //TODO: Skal modtage en ordre
    public String drawCarport(Order order) {

        String viewBox = "0 0 " + (order.getCarport().getLength()+100) + " " + (order.getCarport().getWidth()+125);
        SVG svg = new SVG(0, 0, viewBox, 100, 100);

//        String viewBox = "0 0 1080 960";

        Material rem = null;
        Material spær = null;
        Material stolpe = null;
        Material beklædning = null;

        for (Material m : order.getStkListe()) {
            if (m.getCategory() == 4) {
                rem = m;
            } else if (m.getCategory() == 3) {
                spær = m;
            } else if (m.getCategory() == 5) {
                stolpe = m;
            }
        }

        svg.addRect(0,0,order.getCarport().getWidth(),order.getCarport().getLength());

        if(order.getShed().getWidth() > 0){
            System.out.println("Hvor meget længden på carporten: " + order.getCarport().getLength());
            System.out.println("Hvor mange remme: " + rem.getQuantity());
            System.out.println("Hvor mange stolper der: " + stolpe.getQuantity());
            svg.addRectColor(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(),order.getShed().getWidth() + rem.getWidth(), order.getShed().getLength());
            svg.addRectColor(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(),stolpe.getHeight(),stolpe.getWidth());
            svg.addRectColor(order.getCarport().getLength() - 45 ,(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(), stolpe.getHeight(), stolpe.getWidth());
            svg.addRectColor(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9) -rem.getWidth() , stolpe.getWidth(), stolpe.getHeight());
        }

        //Draw rem
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

        //add lines and arrows
        String xMid = String.valueOf((order.getCarport().getLength() + 100)/2);
        String yMax = String.valueOf(order.getCarport().getWidth()+30);
        svg.arrowsMoreShizeTemplate();

        // carport width
        svg.addArrow((order.getCarport().getLength()+15),0,(order.getCarport().getLength()+15), order.getCarport().getWidth());
        svg.addText( order.getCarport().getLength()+50, order.getCarport().getWidth()/2, "small", "", order.getCarport().getWidth() + " cm");
        // carport length
        svg.addArrow(0,(order.getCarport().getWidth()+15), (order.getCarport().getLength()), (order.getCarport().getWidth()+15));
        svg.addText( 0, 0, "small", "translate("+ xMid +", " + yMax+")",order.getCarport().getLength() + " cm");

        //if stolpe

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

        return svg.toString();

    }
}

