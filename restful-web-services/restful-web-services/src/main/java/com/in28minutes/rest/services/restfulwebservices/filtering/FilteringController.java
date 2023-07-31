package com.in28minutes.rest.services.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filter")
    public MappingJacksonValue filtering(){
        SomeBean someBean = new SomeBean("Tom", "Dick", "Harry");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;

        //return new someBean("Tom", "Dick", "Harry");
    }
    @GetMapping("/filteringList")
    //public List<SomeBean> filteringList(){
    public MappingJacksonValue filteringList(){
        //SomeBean[] someBean = new SomeBean[]{new SomeBean("Prak","Praj","Prak"),new SomeBean("Prak2","Praj3","Prak4"),
          //      new SomeBean("Prak5","Praj6","Prak7")};
        List<SomeBean> list= Arrays.asList(new SomeBean("Prak","Praj","Prak"),
                new SomeBean("Prak2","Praj3","Prak4"),
                new SomeBean("Prak5","Praj6","Prak7"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
      /* return Arrays.asList(new SomeBean("Prak","Praj","Prak"),
                new SomeBean("Prak2","Praj3","Prak4"),
                new SomeBean("Prak5","Praj6","Prak7"));*/
    }
}
