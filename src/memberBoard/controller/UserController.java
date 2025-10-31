package memberBoard.controller;

import java.util.List;
import java.util.Scanner;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.service.UserService;

// 콘솔 입력/출력 담당

public class UserController {

	private final UserService userService;
	private final Scanner sc = new Scanner(System.in);

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// 사용자 회원가입
	public void register() {
		try {
			System.out.print("아이디: ");
			String username = sc.nextLine();
			System.out.print("비밀번호: ");
			String password = sc.nextLine();
			System.out.print("이름: ");
			String name = sc.nextLine();
			System.out.print("연락처: ");
			String phone = sc.nextLine();
			System.out.print("이메일: ");
			String email = sc.nextLine();

			UserDTO userDTO = new UserDTO(username, password, name, phone, email);
			userService.register(userDTO);
			System.out.println("[회원가입 완료]");
		} catch (UserException e) {
			System.out.println("[회원가입 실패] " + e.getMessage());
		}

	}

	// 사용자 로그인
	public void login() {
		try {
			System.out.print("아이디: ");
			String username = sc.nextLine();
			System.out.print("비밀번호: ");
			String password = sc.nextLine();

			User user = userService.login(username, password);
			System.out.println("[로그인 성공] " + user.getName() + "님 환영합니다!");
			if (user.getRole().equalsIgnoreCase("ADMIN")) {
				// 관리자 전용 메뉴
				adminMenu();
			} else {
				// 일반 사용자 메뉴
				loginMenu(user);
			}
		} catch (UserException e) {
			System.out.println("[로그인 실패] " + e.getMessage());
		}
	}

	// 일반 사용자 메뉴
	private void loginMenu(User user) {
		while (true) {
			System.out.println();
			System.out.println("=== [회원 메뉴] ===");
			System.out.println("1. 회원정보 보기");
			System.out.println("2. 회원정보 수정");
			System.out.println("3. 회원탈퇴");
			System.out.println("4. 로그아웃");
			System.out.print("선택: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println(user);
				break;
			case 2:
				updateUser(user);
				break;
			case 3:
				deleteUser(user);
				// 삭제 후 자동 로그아웃
				return;
			case 4:
				System.out.println("로그아웃 되었습니다.");
				return;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

	// 관리자 전용 메뉴
	private void adminMenu() {
		while (true) {
			System.out.println();
			System.out.println("=== [관리자 메뉴] ===");
			System.out.println("1. 전체 회원 목록 조회");
			System.out.println("2. 회원 삭제");
			System.out.println("3. 로그아웃");
			System.out.print("선택: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				// 전체 회원 조회
				showAllUsers();
				break;
			case 2:
				System.out.println("삭제할 회원 아이디: ");
				String username = sc.nextLine();
				try {
					userService.deleteUser(username);
					System.out.println("[삭제 완료]");
				} catch (UserException e) {
					System.out.println("[삭제 실패] " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("로그아웃합니다.");
				return;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

	// 회원정보 수정
	private void updateUser(User user) {
		try {
			System.out.print("새 비밀번호(변경하지 않으려면 Enter): ");
			String password = sc.nextLine();
			System.out.print("새 연락처(변경하지 않으려면 Enter): ");
			String phone = sc.nextLine();
			System.out.print("새 이메일(변경하지 않으려면 Enter): ");
			String email = sc.nextLine();

			userService.updateUser(user.getUsername(), password, phone, email);
			System.out.println("[회원정보 수정 완료]");
		} catch (UserException e) {
			System.out.println("[회원정보 수정 실패] " + e.getMessage());
		}
	}

	// 회원탈퇴
	private void deleteUser(User user) {
		try {
			System.out.print("정말로 탈퇴하시겠습니까? (Y/N): ");
			String confirm = sc.nextLine();
			if (confirm.equalsIgnoreCase("Y")) {
				userService.deleteUser(user.getUsername());
				System.out.println("[회원탈퇴 완료] 계정이 삭제되었습니다.");
			} else {
				System.out.println("회원탈퇴가 취소되었습니다.");
			}
		} catch (UserException e) {
			System.out.println("[회원탈퇴 실패] " + e.getMessage());
		}
	}
	
	// 전체 회원 목록 조회(관리자용)
	private void showAllUsers() {
		List<User> users = userService.getAllUsers();
		System.out.println("=== 전체 회원 목록 ===");
		for(User u : users) {
			System.out.println(u);
		}
	}

}
