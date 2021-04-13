package com.wander.corona.tracker.service;

import com.wander.corona.tracker.dto.CoronaDTO;

import java.io.IOException;
import java.util.List;

public interface CoronaVirusService {

    List<CoronaDTO> getCoronaCasesDetails() ;
}
