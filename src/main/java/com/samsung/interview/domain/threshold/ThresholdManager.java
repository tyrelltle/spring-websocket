package com.samsung.interview.domain.threshold;


import com.samsung.interview.domain.temperature.AbstractTemperature;

public class ThresholdManager {
    Threshold threshold;
    AbstractTemperature previousTemperature;
    Boolean ignoreFlucIfMatch =true;
    Boolean everReached=false;

    public ThresholdManager(Threshold threshold) {
        this.threshold = threshold;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    public AbstractTemperature getPreviousTemperature() {
        return previousTemperature;
    }

    public void setPreviousTemperature(AbstractTemperature previousTemperature) {
        this.previousTemperature = previousTemperature;
    }


    public Boolean isOutOfFluctuation(Double value){
        if(this.previousTemperature==null){
            return true;
        }
        return Math.abs(value-this.previousTemperature.normalizedValue())>this.threshold.Fluctuation;
    }


    public boolean reach(AbstractTemperature temperature){
        boolean match_value=false;
        boolean match_direction=false;
        if(temperature.normalizedValue().equals(this.threshold.getValue())){
            if(!this.everReached){
                this.everReached=true;
                match_value=true;
                this.ignoreFlucIfMatch= false;
            }else if(this.ignoreFlucIfMatch ||this.isOutOfFluctuation(temperature.normalizedValue())){
                match_value=true;
                this.ignoreFlucIfMatch =false;
            }
        }else if(this.isOutOfFluctuation(temperature.normalizedValue())){
            this.ignoreFlucIfMatch=true;
        }

        if(this.previousTemperature==null||this.threshold.getDirection()== Direction.NONE){
            match_direction=true;
        }else{
            switch (this.threshold.getDirection()){
                case ASC:
                    match_direction= this.previousTemperature.normalizedValue()<this.threshold.getValue();
                    break;
                case DESC:
                    match_direction= this.previousTemperature.normalizedValue()>this.threshold.getValue();
                    break;
                default://this code wont be executed
                    match_direction= false;
            }
        }

        this.previousTemperature=temperature;
        return match_direction&&match_value;
    }
}
