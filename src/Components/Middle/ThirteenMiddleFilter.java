package Components.Middle;

import java.io.IOException;

import Framework.CommonFilter;
import Framework.CommonFilterImpl;

public class ThirteenMiddleFilter extends CommonFilterImpl implements CommonFilter {

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int checkCSBlank = 4; // CS 여부 확인을 위한 공백 개수
        int numOfBlank = 0;   // 현재까지 만난 공백 수
        int idx = 0;          // 버퍼 인덱스
        byte[] buffer = new byte[128]; // 한 줄 데이터를 저장할 버퍼
        boolean isThirteen = false;    // 2013학번 여부
        boolean isCS = false;          // CS 전공 여부
        int byte_read = 0;             // 읽은 바이트

        while (true) {
            // 한 줄 읽기
            while (byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if (byte_read == ' ') 
                    numOfBlank++; // 공백 수 증가
                if (byte_read != -1) 
                    buffer[idx++] = (byte) byte_read;

                // 2013 여부 확인
                if (idx >= 4 && buffer[0] == '2' && buffer[1] == '0' && buffer[2] == '1' && buffer[3] == '3') {
                    isThirteen = true; // 2013학번인 경우 설정
                }

                // CS 여부 확인
                if (numOfBlank == checkCSBlank && buffer[idx - 3] == 'C' && buffer[idx - 2] == 'S') {
                    isCS = true; // CS 전공인 경우 설정
                }
            }

            // 출력 조건: 2013학번이고 CS 전공이 아닌 경우
            if (isThirteen && !isCS) {
                String student = new String(buffer, 0, idx).trim();

                // "17651", "17652" 과목 제거
                student = student.replace("17651", "").replace("17652", "");
                student = student.replaceAll(" +", " ").trim(); // 불필요한 공백 제거
                student += "\n"; // 개행 문자 추가

                // 출력 스트림에 쓰기
                byte[] sBuffer = student.getBytes();
                out.write(sBuffer);
            }

            // 스트림 종료 조건
            if (byte_read == -1)
                return true;

            // 초기화: 한 줄 처리 후 상태 초기화
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
            isThirteen = false; // 다음 줄 처리를 위해 초기화
            isCS = false;       // 다음 줄 처리를 위해 초기화
        }
    }
}
