package com.wander.corona.tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoronaDTO {

    private String state;
    private String country;
    private int todayTotalCases;
    private int diffFromPrevDay;
    private int totalPage;
}
