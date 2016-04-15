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
package nl.vanduist.msc.sase.UI;

import edu.umass.cs.sase.engine.ConfigFlags;
import edu.umass.cs.sase.engine.EngineController;
import edu.umass.cs.sase.engine.Profiling;
import edu.umass.cs.sase.stream.ParseStockStreamConfig;
import edu.umass.cs.sase.stream.Stream;
import net.sourceforge.jeval.EvaluationException;
import nl.vanduist.msc.sase.stream.StreamConfig;
import nl.vanduist.msc.sase.stream.TemperatureStreamConfig;

import java.io.IOException;

/**
 * Created by tomvanduist
 * https://github.com/tomvanduist
 */
public class CommandLineUI extends edu.umass.cs.sase.UI.CommandLineUI {

    static final private String kQueryFileLocation = "test.query";
    static final private String kStreamConfigLocation = "test.stream";


    public static void main(String args[]) throws CloneNotSupportedException, EvaluationException, IOException {
        // Get option arguments
        String queryFileLocation    = getQueryFileLocation(args);
        String streamConfigFile     = getStreamConfigLocation(args);
        boolean printDebugInfo      = getPrintDebugInfo(args);
        String engineType           = getEngineType(args);


        ConfigFlags.printResults = printDebugInfo;
        ParseStockStreamConfig.parseStockEventConfig(streamConfigFile);


        EngineController myEngineController = new EngineController(engineType);
        myEngineController.setNfa(queryFileLocation);


        long totalRunTime = 0;
        long totalEventsProcessed = 0;

        // repeat multiple times for a constant performance
        for (int i = 0; i < 20; i ++) {
            System.gc();
            System.out.println("\nRepeat No." + (i+1) +" is started...");


            Stream stream = new TemperatureStreamConfig().generate();

            myEngineController.initializeEngine();
            myEngineController.setInput(stream);
            myEngineController.runEngine();

            System.out.println("\nProfiling results for repeat No." + (i+1) +" are as follows:");
            Profiling.printProfiling();


            totalRunTime += Profiling.totalRunTime;
            totalEventsProcessed += Profiling.numberOfEvents;
        }


        if(totalRunTime > 0){
            long throughput1 = totalEventsProcessed* 1000000000/totalRunTime ;
            System.out.println("\n\n\nTOTAL Throughput: " + throughput1 + " events/second");
        }
    }

    private static String getQueryFileLocation(String args[]) {
        if(args.length > 0) {
            return args[0];
        }
        return kQueryFileLocation;
    }

    private static String getStreamConfigLocation(String args[]) {
        if(args.length > 1) {
            return args[1];
        }
        return kStreamConfigLocation;
    }

    private static Boolean getPrintDebugInfo(String args[]) {
        if(args.length > 2) {
            if(Integer.parseInt(args[2])== 0){
                return false;
            }
        }
        return true;
    }

    private static String getEngineType(String args[]) {
        if(args.length > 3) {
            return args[3];
        }
        return null;
    }
}
