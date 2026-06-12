package com.codingshuttle.module1homework.impl;

import com.codingshuttle.module1homework.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("strawberrySyrup")
public class StrawberrySyrup implements Syrup {
    @Override
    public String getSyrupType() {
        return "StrawberrySyrup";
    }
}
