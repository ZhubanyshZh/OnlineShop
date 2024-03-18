package org.example.domain.model;

import org.example.domain.customInterface.JeansInterface;
import org.example.domain.customInterface.TshirtInterface;
import org.example.domain.model.abst.Provider;
import org.example.domain.customInterface.ShoesInterface;

public class ChinaProvider extends Provider {
    @Override
    public ShoesInterface orderShoes() {
        return new ChinaShoes();
    }

    @Override
    public JeansInterface orderJeans() {
        return new ChinaJeans();
    }

    @Override
    public TshirtInterface orderTshirt() {
        return new ChinaTshirt();
    }
}
