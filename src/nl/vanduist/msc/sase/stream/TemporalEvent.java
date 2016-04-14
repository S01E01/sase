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

import edu.umass.cs.sase.stream.Event;

/**
 * Created by tomvanduist
 * https://github.com/tomvanduist
 */
abstract class TemporalEvent implements Event {
    /**
     * Event id
     */
    private int id;

    /**
     * Event timestamp
     */
    private int timestamp;

    /**
     * Event type
     */
    private String eventType;


    /**
     * Constructor
     */
    public TemporalEvent(int id, int timestamp, String eventType) {
        this.id = id;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int Id) {
        this.id = Id;
    }

    @Override
    public int getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getEventType() {
        return this.eventType;
    }

    @Override
    public String toString() {
        return "ID = "+ id + "\tTimestamp = " + timestamp;
    }

    /**
     * @return the cloned event
     */
    @Override
    public TemporalEvent clone() {
        try {
            return (TemporalEvent)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }

    @Override
    public int getAttributeByName(String attributeName) {
        if(attributeName.equalsIgnoreCase("id")) {
            return this.id;
        } else if(attributeName.equalsIgnoreCase("timestamp")) {
            return this.timestamp;
        }

        return 0;
    }

    @Override
    public double getAttributeByNameDouble(String attributeName) {
        return 0;
    }

    @Override
    public String getAttributeByNameString(String attributeName) {
        return null;
    }

    @Override
    public int getAttributeValueType(String attributeName) {
        return 0;
    }
}
