/*
 * Copyright (c) 2016, Regents of the University of Amsterdam 
 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:

 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 * 		and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 * 		and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *   * Neither the name of the University of Massachusetts Amherst nor the names of its contributors 
 * 		may be used to endorse or promote products derived from this software without specific prior written 
 * 		permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package nl.vanduist.msc.sase.stream;

import edu.umass.cs.sase.stream.StockEvent;

/**
 * Created by tomvanduist
 * https://github.com/tomvanduist
 */
public class TemperatureEvent extends TemporalEvent {
    /**
     * Temperature attribute
     */
    private double val;

    /**
     * Area attribute
     */
    private int area;


    /**
     * Constructor
     */
    public TemperatureEvent(int id, int timestamp, int area, double val) {
        super(id, timestamp, "temperature");

        this.area = area;
        this.val = val;
    }

    public double getTemperature() {
        return this.val;
    }

    public int getArea() {
        return this.area;
    }

    @Override
    public String toString() {
        return super.toString() + "\tArea = " + getArea() + "\tTemperature = " + getTemperature();
    }

    @Override
    public TemperatureEvent clone() {
        try {
            return (TemperatureEvent)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getAttributeByName(String attributeName) {
        int value = super.getAttributeByName(attributeName);

        if (value != 0) {
            return value;
        }

        if (attributeName.equalsIgnoreCase("area")) {
            return getArea();
        }

        return 0;
    }

    @Override
    public double getAttributeByNameDouble(String attributeName) {
        if (attributeName.equalsIgnoreCase("temperature")) {
            return getTemperature();
        }

        return 0;
    }
}
