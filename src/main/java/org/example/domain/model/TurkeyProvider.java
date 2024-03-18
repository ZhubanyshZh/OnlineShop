package org.example.domain.model;

import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.domain.customInterface.JeansInterface;
import org.example.domain.customInterface.ShoesInterface;
import org.example.domain.customInterface.TshirtInterface;
import org.example.domain.model.abst.Provider;

public class TurkeyProvider extends Provider {
    @Override
    public ShoesInterface orderShoes() { return new TurkeyShoes(); }

    @Override
    public JeansInterface orderJeans() {
        return new TurkeyJeans();
    }

    @Override
    public TshirtInterface orderTshirt() {
        return new TurkeyTshirt();
    }
}
