package Framework;

import Components.Middle.ThirteenMiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManagerThirteen {
	 public static void main(String[] args) {
	        try {           
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
