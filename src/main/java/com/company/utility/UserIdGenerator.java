package com.company.utility;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.time.LocalDateTime;


public class UserIdGenerator implements IdentifierGenerator {

    private static int count = 1000;

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        count = count + 1;
        LocalDateTime now = LocalDateTime.now();
        String str = "U" + now.getDayOfMonth() + now.getMonthValue() + now.getYear() + now.getHour() + now.getMinute() + count;
        return str;

    }
}

