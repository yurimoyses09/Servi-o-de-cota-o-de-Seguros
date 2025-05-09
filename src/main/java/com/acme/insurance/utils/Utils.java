package com.acme.insurance.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

@Component
public class Utils {
    public BigDecimal SumCoverageValue(Collection<BigDecimal> values){

        BigDecimal total = BigDecimal.ZERO;

        for (BigDecimal value : values){
            total = total.add(value);
        }
        return total;
    }
}
