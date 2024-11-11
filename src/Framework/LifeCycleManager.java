/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.EEMiddleFilter;
import Components.Middle.MiddleFilter;
import Components.Middle.ThirteenMiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SinkFilter("CSOutput.txt");
            CommonFilter filter3 = new MiddleFilter();
            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter2);
           
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
           
            thread1.start();
            thread2.start();
            thread3.start();
            ///////////////////////////////////////////////////////////////////////
            CommonFilter eeSourceFilter = new SourceFilter("Students.txt");
            CommonFilter eeSinkFilter = new SinkFilter("EEOutput.txt");
            CommonFilter eeMiddleFilter = new EEMiddleFilter();
            
            eeSourceFilter.connectOutputTo(eeMiddleFilter);
            eeMiddleFilter.connectOutputTo(eeSinkFilter);
            
            Thread EESourceThread1 = new Thread(eeSourceFilter);
            Thread EESinkThread2 = new Thread(eeSinkFilter);
            Thread EEMiddleThread3 = new Thread(eeMiddleFilter);
            
            EESourceThread1.start();
            EESinkThread2.start();
            EEMiddleThread3.start();
            ///////////////////////////////////////////////////////////////////////
            CommonFilter SourceFilter13 = new SourceFilter("Students.txt");
            CommonFilter SinkFilter13 = new SinkFilter("2013Output.txt");
            CommonFilter MiddleFilter13 = new ThirteenMiddleFilter();
            
            SourceFilter13.connectOutputTo(MiddleFilter13);
            MiddleFilter13.connectOutputTo(SinkFilter13);
            
            Thread thirteenSourceThread = new Thread(SourceFilter13);
            Thread thirteenSinkThread = new Thread(SinkFilter13);
            Thread thirteenMiddlethread = new Thread(MiddleFilter13);
            
            thirteenSourceThread.start();
            thirteenSinkThread.start();
            thirteenMiddlethread.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
