package com.wander.corona.tracker.service.impl;


import com.wander.corona.tracker.config.PropertySource;
import com.wander.corona.tracker.dto.CoronaDTO;
import com.wander.corona.tracker.service.CoronaVirusService;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusServiceImpl implements CoronaVirusService {

    @Autowired
    private PropertySource propertySource;

    @Override
    public List<CoronaDTO> getCoronaCasesDetails()  {
        List<CoronaDTO> coronaCasesData = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader csvBodyReader = new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
            for (CSVRecord record : records) {
                CoronaDTO locationStat = new CoronaDTO();
                locationStat.setState(record.get("Province/State"));
                locationStat.setCountry(record.get("Country/Region"));
                int latestCases = Integer.parseInt(record.get(record.size() - 1));
                int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
                locationStat.setTodayTotalCases(latestCases);
                locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
                coronaCasesData.add(locationStat);
            }
        } catch (Exception e) {
            System.out.println("Error while preparing the data" + e);
        }
        return coronaCasesData;
    }
}