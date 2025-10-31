package memberBoard;

import java.util.Scanner;

import memberBoard.controller.UserController;
import memberBoard.repository.UserRepositoryImpl;
import memberBoard.service.UserServiceImpl;

// 실행 진입점

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserRepositoryImpl repo = new UserRepositoryImpl();
		UserServiceImpl service = new UserServiceImpl(repo);
		UserController controller = new UserController(service);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println();
			System.out.println("=== 회원 관리 시스템 ===");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			System.out.print("선택 : ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 1) {
				System.out.print("아이디: ");
				String username = sc.nextLine();
				System.out.print("비밀번호: ");
				String password = sc.nextLine();
				System.out.print("이메일: ");
				String email = sc.nextLine();
				
				controller.register(username, password, email);
			}else if (choice == 2) {
				System.out.print("아이디: ");
				String username = sc.nextLine();
				System.out.print("비밀번호: ");
				String password = sc.nextLine();
				
				controller.login(username, password);
			}else {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}

}
