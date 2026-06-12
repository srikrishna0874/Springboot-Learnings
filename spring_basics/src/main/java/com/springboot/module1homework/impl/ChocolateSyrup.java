package com.codingshuttle.module1homework.impl;

import com.codingshuttle.module1homework.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocolateSyrup")
public class ChocolateSyrup implements Syrup {
    @Override
    public String getSyrupType() {
        return "Chocolate";
    }
}
