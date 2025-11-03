package memberBoard.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import memberBoard.controller.UserController;

public class UserView {
	private final UserController controller;
	private final Scanner sc = new Scanner(System.in);

	public UserView(UserController controller) {
		this.controller = controller;
	}

	public void showMenu() {
		while (true) {
			System.out.println();
			System.out.println("=== [회원 관리 시스템] ===");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 아이디 찾기"); // 추가
			System.out.println("4. 비밀번호 재설정"); // 추가
			System.out.println("5. 종료");
			System.out.print("선택: ");

			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (choice) {
			case 1:
				controller.register();
				break;
			case 2:
				controller.login();
				break;
			case 3: // 아이디 찾기 메뉴
				controller.findUsername();
				break;
			case 4: // 비밀번호 재설정 메뉴
				controller.resetPassword();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}
}
