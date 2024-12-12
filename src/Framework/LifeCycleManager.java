/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.util.Arrays;

import Components.Middle.CourseFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
	public static void main(String[] args) {
	        try {
	            // Source and Sink Filters
	            CommonFilter sourceFilter = new SourceFilter("Students.txt");
	            CommonFilter sinkFilter = new SinkFilter("Output.txt");

	            // CS Filter Example
	            CommonFilter csFilter = new CourseFilter("EE", Arrays.asList("23456"));
	            sourceFilter.connectOutputTo(csFilter);
	            csFilter.connectOutputTo(sinkFilter);

	            Thread thread1 = new Thread(sourceFilter);
	            Thread thread2 = new Thread(csFilter);
	            Thread thread3 = new Thread(sinkFilter);

	            thread1.start();
	            thread2.start();
	            thread3.start();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
