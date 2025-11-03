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
			System.out.println("3. 종료");
			System.out.print("선택: ");
			
			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine(); // 개행문자 제거
			} catch(InputMismatchException e) {
				System.out.println("숫자를 입력해주세요.");
				sc.nextLine(); // 잘못된 입력 버림
				continue; // 다시 반복
			}

			switch (choice) {
			case 1:
				controller.register();
				break;
			case 2:
				controller.login();
				break;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

	
}
