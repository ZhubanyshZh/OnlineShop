package org.example.domain.model.abst;

import org.example.domain.customInterface.JeansInterface;
import org.example.domain.customInterface.ShoesInterface;
import org.example.domain.customInterface.TshirtInterface;
import org.example.domain.model.*;

public abstract class Provider {
    protected String email;

    public Provider(String email) {
        this.email = email;
    }

    public Provider() {
    }

    public String getEmail() {
        return email;
    }

    public abstract ShoesInterface orderShoes();

    public abstract JeansInterface orderJeans();

    public abstract TshirtInterface orderTshirt();















//    public static void main(String[] args) {
//        Provider Turkey = new TurkeyProvider();
//        ShoesInterface turkeyShoes = Turkey.orderShoes();
//        turkeyShoes.orderToShoes("link", "zhubanysh.zharylkassynov@narxoz.kz");
//
//        Provider China = new ChinaProvider();
//        JeansInterface ChinaJeans = China.orderJeans();
//        ChinaJeans.orderToJeans("link", "bekassyl.serik@narxoz.kz");
//    }

}
