package memberBoard.view;

//============================================
//InputHandler.java - 입력 처리
//============================================

import java.util.InputMismatchException;
import java.util.Scanner;

import memberBoard.validator.InputValidator;

public class InputHandler {

	private final Scanner scanner;
	private final InputValidator validator;

	public InputHandler() {
		this.scanner = new Scanner(System.in);
		this.validator = new InputValidator();
	}

	// 문자열 입력
	public String getString(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine().trim();
	}

	// 선택적 문자열 입력 (빈 값 허용)
	public String getOptionalString(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine().trim();
		return input.isEmpty() ? null : input;
	}

	// 다중 행 입력
	public String getMultilineString(String prompt) {
		System.out.println(prompt + " (입력 완료: 빈 줄 입력):");
		StringBuilder content = new StringBuilder();

		while (true) {
			String line = scanner.nextLine();
			if (line.isEmpty())
				break;
			content.append(line).append("\n");
		}

		return content.toString().trim();
	}

	// 선택적 다중 행 입력
	public String getOptionalMultilineString(String prompt) {
		String input = getMultilineString(prompt);
		return input.isEmpty() ? null : input;
	}

	// 비밀번호 입력
	public String getPassword(String prompt) {
		return getString(prompt);
	}

	// 선택적 비밀번호 입력
	public String getOptionalPassword(String prompt) {
		return getOptionalString(prompt);
	}

	// 전화번호 입력
	public String getPhone(String prompt) {
		while (true) {
			String phone = getString(prompt);
			if (validator.isValidPhone(phone)) {
				return phone;
			}
			System.out.println("잘못된 전화번호 형식입니다. 다시 입력해주세요.");
		}
	}

	// 선택적 전화번호 입력
	public String getOptionalPhone(String prompt) {
		String phone = getOptionalString(prompt);
		if (phone == null)
			return null;

		if (!validator.isValidPhone(phone)) {
			System.out.println("잘못된 전화번호 형식입니다.");
			return null;
		}
		return phone;
	}

	// 이메일 입력
	public String getEmail(String prompt) {
		while (true) {
			String email = getString(prompt);
			if (validator.isValidEmail(email)) {
				return email;
			}
			System.out.println("잘못된 이메일 형식입니다. 다시 입력해주세요.");
		}
	}

	// 선택적 이메일 입력
	public String getOptionalEmail(String prompt) {
		String email = getOptionalString(prompt);
		if (email == null)
			return null;

		if (!validator.isValidEmail(email)) {
			System.out.println("잘못된 이메일 형식입니다.");
			return null;
		}
		return email;
	}

	// 정수 입력
	public int getInt(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + ": ");
				int value = scanner.nextInt();
				scanner.nextLine(); // 버퍼 비우기
				return value;
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				scanner.nextLine(); // 버퍼 비우기
			}
		}
	}

	// 메뉴 선택 입력
	public int getMenuChoice(int min, int max) {
		while (true) {
			try {
				System.out.print("선택: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // 버퍼 비우기

				if (choice >= min && choice <= max) {
					return choice;
				}
				System.out.println("잘못된 선택입니다. " + min + "-" + max + " 사이의 숫자를 입력해주세요.");
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				scanner.nextLine(); // 버퍼 비우기
			}
		}
	}

	// Y/N 확인
	public boolean getConfirmation(String prompt) {
		while (true) {
			System.out.print(prompt + " (Y/N): ");
			String input = scanner.nextLine().trim().toUpperCase();

			if (input.equals("Y") || input.equals("YES")) {
				return true;
			} else if (input.equals("N") || input.equals("NO")) {
				return false;
			}
			System.out.println("Y 또는 N을 입력해주세요.");
		}
	}

	public void close() {
		scanner.close();
	}
}