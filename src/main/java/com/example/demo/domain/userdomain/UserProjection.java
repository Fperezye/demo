package com.example.demo.domain.userdomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserProjection {

    public UUID getId();

    public String getName();

    public BigDecimal getPrice();
}