package com.samsung.interview.domain.temperature;


import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;

public class TemperatureFactory {
    /**
     * @param str  formate should be number space C/F.  ex: 30 C,  20 F
     * @return
     */
    public static AbstractTemperature getTemperatureFromString(String str) throws InvalidTemperatureFormate {
        if(str==null){
            return null;
        }

        String split[]=str.split(" ");
        if(split.length<2){
            throw new InvalidTemperatureFormate("invalid input temperature string formate. correct formate is number space C/F");
        }

        Double value;
        try{
            value = Double.parseDouble(split[0]);
        }catch (Exception e){
            throw new InvalidTemperatureFormate("invalid input temperature value",e);
        }

        if(split[1].equals("C")){
            return new Celsius(value);
        }else if(split[1].equals("F")){
            return new Fahrenheit(value);
        }else{
            throw new InvalidTemperatureFormate("invalid input temperature mode "+split[1]);
        }
    }
}
