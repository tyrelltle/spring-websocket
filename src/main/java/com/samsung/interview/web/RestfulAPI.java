package com.samsung.interview.web;

import com.samsung.interview.domain.Thermometer;
import com.samsung.interview.domain.tempsource.TextTemperatureSource;
import com.samsung.interview.domain.threshold.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class RestfulAPI {
    @Autowired
    Thermometer thermometer;

    @RequestMapping(value = "/subscribers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody SubscriberDTO subscriberDTO) throws URISyntaxException {
        try {
            thermometer.addSubscriber(subscriberDTO.getName(),subscriberDTO.getThresholds());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @RequestMapping(value = "/subscribers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriberThresholdDTO>> getSubscriberAndReachedThres() throws URISyntaxException {
        try {
            return new ResponseEntity<>(thermometer.getSubscriberReachedThreshold(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/subscribers/{name}/thresholds",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Threshold>> getSubscrberThresholds(@PathVariable("name") String name) throws URISyntaxException {
        try {
            return new ResponseEntity<>(thermometer.getSubscriberThresholds(name),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/subscribers/{name}/thresholds",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSubscrberThreshold(@PathVariable("name") String name, @Valid @RequestBody Threshold dto) throws URISyntaxException {
        try {
            thermometer.addSubscriberThres(name,dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/temperature",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewTemperature(@Valid @RequestBody List<TemperatureInputDTO> dtos) throws URISyntaxException {
        try {
            List<String> mapped=dtos.stream().map(d->d.getName()).collect(Collectors.toList());
            TextTemperatureSource textTemperatureSource=new TextTemperatureSource(mapped);

            thermometer.processNewTemperature(textTemperatureSource);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }
}
