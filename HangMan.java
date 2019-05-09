import java.util.Scanner;

public class HangMan {

	static int failCount = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] stringArr = { "apple", "car", "match", "zoo", "language", "java", "fun", "love", "fail", "computer" }; // 선택지 지정

		String findChar; // 사용자가 입력하는 문자
		String word = ""; // *이 포함된 문자열
		int choice = (int) (stringArr.length * Math.random());

		for (int i = 0; i < stringArr[choice].length(); i++) { // stringArr 에서 선택된 단어의 수 만큼 word 에 * 를 채워 기본 세팅
			word = word + "*";
		}

		System.out.println("행맨 게임을 시작합니다.");
		while (true) { // 무한 루프
			System.out.println("==============================================");
			System.out.print("알파벳을 입력해주세요(대문자와 소문자를 구분하지않습니다) : ");
			findChar = sc.nextLine();
			if (findChar.length() > 1) { // 한글자보다 많이 입력시
				System.out.println("입력이 한 글자보다 많습니다 다시 입력하세요.");
				continue; // continue 가 실행되어 맨 앞으로 돌아감
			}

			if (mainGame(findChar.toLowerCase(), stringArr[choice], word).equalsIgnoreCase(word)) // 메소드에서 나온 단어와 기존의 단어가 같을 시 문자가
																					// 바뀌지않은 즉 * 표시가 사용자가 입력한 문자로 바뀌지않았기 때문에 맞추지
																					// 못했다 판단해 실패 카운트를 올림
				failCount++;

			word = mainGame(findChar.toLowerCase(), stringArr[choice], word); // 메소드 리턴값( ex) *** > z** )을 word 에 저장

			System.out.println(word);
			System.out.println("실패 횟수 : " + failCount);
			if (word.equals(stringArr[choice])) {
				System.out.printf("선택된 단어는 %s 로 성공입니다. HangMan 을 종료합니다.", stringArr[choice]);
				System.exit(-1); // 프로그램을 종료시킴
			}

			if (failCount == 5) {
				System.out.println("5 번 이상 실패했습니다. HangMan을 종료합니다.");
				System.exit(-1); // 프로그램을 종료시킴
			}
		}
	}

	public static String mainGame(String findChar, String compareWord, String word) {
		// 사용자가 입력한 문자, 완전한 단어, 진행상황을 알려주는 문자열을 받음. 
		// 사용자가 입력한 문자와 완전한 단어들을 비교하여 일치하는 문자가 있을 시 그 문자의 위치를 받아 word 문자에서 같은 위치의 * 와 바꾸어줌
		char[] word01 = new char[word.length()];	// 비교 후 해당 인덱스의 문자 값을 바꾸기 위해 배열 선언
		String returnWord = "";
		for (int i = 0; i < word.length(); i++) {
			word01[i] = word.charAt(i);				// 문자열을 문자로 하나하나 나누어 배열에 저장
		}

		for (int i = 0; i < word.length(); i++) {	// charAt 로 문자열의 문자를 하나하나 비교해주다가 같을 시 해당 인덱스의 문자 * 이 저장되어있는 것을 사용자가 입력한 문자로 바꾸어 저장
			for (int j = 0; j < findChar.length(); j++) {
				if (compareWord.charAt(i) == findChar.charAt(j))
					word01[i] = findChar.charAt(j);
			}
		}

		for (int i = 0; i < word01.length; i++) {	// 문자로 나눈 배열들을 다시 합쳐 문자열로 저장
			returnWord = returnWord + word01[i];
		}

		return returnWord;							// 현재상황이 저장된 문자열을 반환
	}

}
