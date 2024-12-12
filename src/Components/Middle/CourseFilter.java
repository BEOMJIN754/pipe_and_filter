package Components.Middle;

import java.io.IOException;
import java.util.List;
import Framework.CommonFilterImpl;

public class CourseFilter extends CommonFilterImpl {
	private String department; // CS or EE
	private List<String> requiredCourses; // Required courses list

	public CourseFilter(String department, List<String> requiredCourses) {
		this.department = department;
		this.requiredCourses = requiredCourses;
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int byteRead = -1;
		byte[] buffer = new byte[128]; // Buffer for a single line
		int bytesRead = 0;
		boolean isTargetDepartment = false;
		int blankCount = 0; // To track spaces (used for identifying department)

		while (true) {
			byteRead = in.read();
			if (byteRead == -1 && bytesRead == 0)
				break; // End of stream
			if (byteRead == '\n' || byteRead == -1) { // End of a line
				if (isTargetDepartment) {
					String line = new String(buffer, 0, bytesRead).trim();

					// Add missing courses
					for (String course : requiredCourses) {
						if (!line.contains(course)) {
							line += " " + course;
						}
					}

					line += "\n";
					out.write(line.getBytes());
					out.flush();
				}

				buffer = new byte[128];
				bytesRead = 0;
				isTargetDepartment = false;
				blankCount = 0;
			} else {
				buffer[bytesRead++] = (byte) byteRead;

				// Check for spaces to identify the department (4th token starts after 3 spaces)
				if (byteRead == ' ')
					blankCount++;
				if (blankCount == 3) {
					// Compare department (e.g., 'C', 'S' for CS)
					if (bytesRead >= 2 && buffer[bytesRead - 2] == department.charAt(0)
							&& buffer[bytesRead - 1] == department.charAt(1)) {
						isTargetDepartment = true;
					}
				}
			}
		}

		return true;
	}
}
