package Components.Middle;

import java.io.IOException;
import java.util.List;

import Components.Enum.Department;
import Components.Enum.Operation;
import Components.Enum.Year;
import Framework.CommonFilterImpl;

public class CourseModifyFilter extends CommonFilterImpl {
    private Year yearFilter;               // 학년 필터 (ALL, 2013, EXCEPT_YEAR_2013 등)
    private Department departmentFilter;   // 전공 필터 (ALL, CS, EXCEPT_CS 등)
    private Operation operation;           // 동작 모드 (ADD or DELETE)
    private List<String> requiredCourses;  // 추가 또는 삭제할 강좌 리스트

    public CourseModifyFilter(Year yearFilter, Department departmentFilter, Operation operation, List<String> requiredCourses) {
        this.yearFilter = yearFilter;
        this.departmentFilter = departmentFilter;
        this.operation = operation;
        this.requiredCourses = requiredCourses;
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byteRead;
        int numOfBlanks = 0; 
        int idx = 0;
        byte[] buffer = new byte[128];
        boolean isTargetYear = false;
        boolean isTargetDepartment = false;

        while (true) {
            while ((byteRead = in.read()) != -1 && byteRead != '\n') {
                buffer[idx++] = (byte) byteRead;

                if (byteRead == ' ') numOfBlanks++;

                // 학년 추출
                if (idx >= 4 && numOfBlanks == 0) {
                    String studentYear = new String(buffer, 0, 4).trim();
                    isTargetYear = yearFilter.matches(studentYear);
                    System.out.println("Student Year: " + studentYear + ", isTargetYear: " + isTargetYear);
                }

                // 전공 추출
                if (numOfBlanks == 3) {
                    int startIdx = idx;
                    while (startIdx > 0 && buffer[startIdx - 1] != ' ') {
                        startIdx--;
                    }
                    String studentDepartment = new String(buffer, startIdx, idx - startIdx).trim();
                    isTargetDepartment = departmentFilter.matches(studentDepartment);
                    System.out.println("Student Department: " + studentDepartment + ", isTargetDepartment: " + isTargetDepartment);
                }
            }

            if (byteRead == -1 && idx == 0) break;

            if (isTargetYear && isTargetDepartment) {
                String student = new String(buffer, 0, idx).trim();
                if (operation == Operation.ADD) {
                    for (String course : requiredCourses) {
                        if (!student.contains(course)) {
                            student += " " + course;
                        }
                    }
                } else if (operation == Operation.DELETE) {
                    for (String course : requiredCourses) {
                        student = student.replace(course, "").replaceAll(" +", " ").trim();
                    }
                }
                student += "\n";
                out.write(student.getBytes());
                out.flush();
            }

            idx = 0;
            numOfBlanks = 0;
            isTargetYear = false;
            isTargetDepartment = false;
        }

        return true;
    }

}
