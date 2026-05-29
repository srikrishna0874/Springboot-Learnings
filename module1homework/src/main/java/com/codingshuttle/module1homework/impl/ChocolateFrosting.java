package com.codingshuttle.module1homework.impl;

import com.codingshuttle.module1homework.Frosting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocolateFrosting")
public class ChocolateFrosting implements Frosting {

    @Override
    public String getFrostingType() {
        return "ChocolateFrosting";
    }
}
