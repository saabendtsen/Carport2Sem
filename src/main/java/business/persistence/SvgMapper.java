package business.persistence;

import business.entities.Material;
import business.entities.Order;
import business.services.SVG;

import java.util.List;

public class SvgMapper {


    //This draws the entuire carport

    //TODO: Skal modtage en ordre
    public String drawCarport(Order order) {

        String viewBox = "-100 -100 " + (order.getCarport().getLength()+200) + " " + (order.getCarport().getWidth()+225);
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
            svg.addRectStroke(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(),order.getShed().getWidth() + rem.getWidth(), order.getShed().getLength());
            svg.addRectStroke(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(),stolpe.getHeight(),stolpe.getWidth());
            svg.addRectStroke(order.getCarport().getLength() - 45 ,(order.getCarport().getWidth() * 0.9 ) - order.getShed().getWidth(), stolpe.getHeight(), stolpe.getWidth());
            svg.addRectStroke(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),(order.getCarport().getWidth() * 0.9) -rem.getWidth() , stolpe.getWidth(), stolpe.getHeight());
            svg.addArrow(order.getCarport().getLength() + 10, order.getCarport().getWidth() * 0.9 ,order.getCarport().getLength() + 10, order.getCarport().getWidth() * 0.9 - order.getShed().getWidth());
            svg.addText(order.getCarport().getLength() + 30, (order.getCarport().getWidth() * 0.9)-(order.getShed().getWidth()/2),"small","",order.getShed().getWidth() + "");
            svg.addArrow(order.getCarport().getLength() - order.getShed().getLength()-45+stolpe.getWidth(),order.getCarport().getWidth()+10,order.getCarport().getLength()-45+stolpe.getWidth(),order.getCarport().getWidth()+10);
            svg.addText(order.getCarport().getLength() - (order.getShed().getLength()/2)-45+stolpe.getWidth(),order.getCarport().getWidth()+30,"small","",order.getShed().getLength() + "");
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

        double space = (order.getCarport().getLength()-spær.getWidth()) / (spær.getQuantity()-1);
        double x = 0;
        for (int i = 0; i < spær.getQuantity(); i++) {
            svg.addRect(x, 0, order.getCarport().getWidth(), spær.getWidth());
            x += space;
        }
        x = 0;
        int spaceInt = (int)space;
        for (int i = 0; i < spær.getQuantity()-1; i++) {
            svg.addArrow(x+spær.getWidth()/2,-10,x+space+spær.getWidth()/2,-10);
            svg.addText(space/2+x,-20,"small","",spaceInt + "");
            x += space;
        }
        //add lines and arrows
        svg.arrowsMoreShizeTemplate();

        // carport width
        svg.addArrow(-10,0,(-10), order.getCarport().getWidth());
        svg.addText( -30, order.getCarport().getWidth()/2, "small", "", order.getCarport().getWidth() + "");
        // carport length
        svg.addArrow(0,(order.getCarport().getWidth()+40), (order.getCarport().getLength()), (order.getCarport().getWidth()+40));
        svg.addText( order.getCarport().getLength()/2, order.getCarport().getWidth()+55, "small", "",order.getCarport().getLength() + "");

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
        svg.addRectStroke(order.getCarport().getLength() - 45,order.getCarport().getWidth() * 0.9  - order.getShed().getWidth(), stolpe.getWidth(),stolpe.getHeight());


        return svg.toString();

    }
}

