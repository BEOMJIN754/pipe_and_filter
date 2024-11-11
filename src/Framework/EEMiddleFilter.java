package Framework;


import java.io.IOException;

public class EEMiddleFilter extends CommonFilterImpl {
	@Override
	public boolean specificComputationForFilter() throws IOException {
		int checkBlank = 4;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[128];
		boolean isEE = false;
		int byte_read = 0;

		while (true) {
			// check "CS" on byte_read from student information
			while (byte_read != '\n' && byte_read != -1) {
				byte_read = in.read();
				if (byte_read == ' ')
					numOfBlank++;
				if (byte_read != -1)
					buffer[idx++] = (byte) byte_read;
				if (numOfBlank == checkBlank && buffer[idx - 3] == 'E' && buffer[idx - 2] == 'E')
					isEE = true;
			}
			if (isEE == true) {
				String student = new String(buffer, 0, idx).trim();

				if (!student.contains("23456")) {
					student += " 23456";
				}
				
				student += "\n";
					
				System.out.println("Test: "+ student);
				
				byte[] sBuffer = student.getBytes();
				out.write(sBuffer);
				isEE = false;
				
			}
			
			if (byte_read == -1)
				return true;
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}
}

