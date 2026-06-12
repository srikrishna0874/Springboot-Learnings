package com.codingshuttle.module1homework;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    private final Frosting frosting;
    private final Syrup syrup;

    public CakeBaker(@Qualifier("strawberryFrosting") Frosting frosting,
                     @Qualifier("chocolateSyrup") Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Cake baked using " + frosting.getFrostingType() + " and " + syrup.getSyrupType());
    }
}
