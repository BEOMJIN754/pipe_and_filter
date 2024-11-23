package Components.Middle;

import java.io.IOException;

import Framework.CommonFilter;
import Framework.CommonFilterImpl;

public class ThirteenMiddleFilter extends CommonFilterImpl implements CommonFilter {

	@Override
	public boolean specificComputationForFilter() throws IOException {
		int checkCSBlank = 4;
		int numOfBlank = 0;
		int idx = 0;
		byte[] buffer = new byte[128];
		boolean isThirteen = false;
		boolean is2013CS = false;
		int byte_read = 0;

		while (true) {
			while (byte_read != '\n' && byte_read != -1) {
				byte_read = in.read();
				if (byte_read == ' ')
					numOfBlank++;
				if (byte_read != -1)
					buffer[idx++] = (byte) byte_read;
				if (idx >= 4 && buffer[0] == '2' && buffer[1] == '0' && buffer[2] == '1' && buffer[3] == '3') {
					if(numOfBlank == checkCSBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S')
	                    is2013CS = true;
					else isThirteen = true;
				}
			}
			if(is2013CS == true) {
                for(int i = 0; i<idx; i++) 
                    out.write((char)buffer[i]);
                is2013CS = false;
            }
			if(isThirteen == true) {
				String student = new String(buffer, 0, idx).trim();

				student = student.replace("17651", "");
				student = student.replace("17652", "");
				
				student += "\n";
				
				byte[] sBuffer = student.getBytes();
				out.write(sBuffer);
				isThirteen = false;
				
            }
			
			
			if (byte_read == -1)
				return true;
			idx = 0;
			numOfBlank = 0;
			byte_read = '\0';
		}
	}

}
