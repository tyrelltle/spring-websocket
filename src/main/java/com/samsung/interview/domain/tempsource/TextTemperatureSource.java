package com.samsung.interview.domain.tempsource;


import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;
import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.temperature.TemperatureFactory;

import java.util.ArrayList;
import java.util.List;

public class TextTemperatureSource implements ITemperatureSource {
    List<String> lines=new ArrayList<>();
    int index=0;

    public TextTemperatureSource(List<String> lines){
        this.lines=lines;
    }

    public TextTemperatureSource() {
    }

    @Override
    public boolean hasNaxt() {
        return this.index<this.lines.size();
    }

    @Override
    public AbstractTemperature readNext() throws InvalidTemperatureFormate {
        if(!this.hasNaxt()){
            return null;
        }

        String nextline=this.lines.get(this.index);
        this.index++;
        return TemperatureFactory.getTemperatureFromString(nextline);
    }

    @Override
    public String toString() {
        return "TextTemperatureSource{" +
                "lines=" + lines +
                '}';
    }
}
