package com.company.utility;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.time.LocalDateTime;

public class CoachIdGenerator implements IdentifierGenerator {

    private static int count = 100;

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        count = count + 1;
        LocalDateTime now = LocalDateTime.now();
        String str = "LC" + now.getDayOfMonth() + now.getMonthValue() + now.getYear() + now.getHour() + now.getMinute() + count;
        return str;

    }
}
