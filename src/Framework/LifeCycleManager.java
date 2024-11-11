/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SinkFilter("CSOutput.txt");
            CommonFilter filter3 = new MiddleFilter();
            
            CommonFilter eeSourceFilter = new SourceFilter("Students.txt");
            CommonFilter eeSinkFilter = new SinkFilter("EEOutput.txt");
            CommonFilter eeMiddleFilter = new EEMiddleFilter();
            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter2);
            
            eeSourceFilter.connectOutputTo(eeMiddleFilter);
            eeMiddleFilter.connectOutputTo(eeSinkFilter);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            
            Thread EEthread1 = new Thread(eeSourceFilter);
            Thread EEthread2 = new Thread(eeSinkFilter);
            Thread EEthread3 = new Thread(eeMiddleFilter);
            
            thread1.start();
            thread2.start();
            thread3.start();
            
            EEthread1.start();
            EEthread2.start();
            EEthread3.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
