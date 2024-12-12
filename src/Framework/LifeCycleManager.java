package Framework;

import java.util.Arrays;

import Components.Enum.Department;
import Components.Enum.Operation;
import Components.Enum.Year;
import Components.Middle.CourseModifyFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter sourceFilter = new SourceFilter("Students.txt");
            CommonFilter sinkFilter = new SinkFilter("Output.txt");

            // 모든 전공에서 CS 제외, 2013 제외, ADD 모드
            CommonFilter courseModifyFilter = new CourseModifyFilter(
                Year.ALL, Department.CS, Operation.ADD, Arrays.asList("12345","23456")
            );

            sourceFilter.connectOutputTo(courseModifyFilter);
            courseModifyFilter.connectOutputTo(sinkFilter);

            Thread sourceThread = new Thread(sourceFilter, "SourceFilter");
            Thread modifyThread = new Thread(courseModifyFilter, "CourseModifyFilter");
            Thread sinkThread = new Thread(sinkFilter, "SinkFilter");

            sourceThread.start();
            modifyThread.start();
            sinkThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
