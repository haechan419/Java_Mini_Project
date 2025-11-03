package memberBoard.controller;

import java.util.List;
import java.util.Scanner;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.service.BoardService;
import memberBoard.service.UserService;

// 회원가입/로그인

public class UserController {

	private final UserService userService;
	private final Scanner sc = new Scanner(System.in);
	private final BoardController boardController;

	public UserController(UserService userService, BoardController boardController) {
		super();
		this.userService = userService;
		this.boardController = boardController;
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
			String phone;
	        while (true) {
	            System.out.print("연락처 (010-0000-0000 형식): ");
	            phone = sc.nextLine();
	            if (isValidPhone(phone)) {
	                break;
	            } else {
	                System.out.println("잘못된 전화번호 형식입니다. 다시 입력해주세요.");
	            }
	        }
	        String email;
	        while (true) {
	            System.out.print("이메일 (예: aa@aaaa.aaa): ");
	            email = sc.nextLine();
	            if (isValidEmail(email)) {
	                break;
	            } else {
	                System.out.println("잘못된 이메일 형식입니다. 다시 입력해주세요.");
	            }
	        }

			UserDTO userDTO = new UserDTO(username, password, name, phone, email);
			userService.register(userDTO);
			System.out.println("[회원가입 완료]");
		} catch (UserException e) {
			System.out.println("[회원가입 실패] " + e.getMessage());
		}

	}
	
	// 전화번호 형식 검사 메서드
	private boolean isValidPhone(String phone) {
	    // 정규식: 010-0000-0000 형태만 허용
	    String regex = "^010-\\d{4}-\\d{4}$";
	    return phone.matches(regex);
	}
	
	// 이메일 형식 검사 메서드
	private boolean isValidEmail(String email) {
	    String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
	    return email.matches(regex);
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
			System.out.println("4. 게시판으로 이동");
			System.out.println("5. 로그아웃");
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
				// 게시판 메뉴로 이동
				boardController.showBoardMenu(user);
				break;
			case 5:
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
			System.out.println("3. 게시글 관리");
			System.out.println("4. 로그아웃");
			System.out.print("선택: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				// 전체 회원 조회
				showAllUsers();
				break;
			case 2:
				System.out.print("삭제할 회원 아이디: ");
				String username = sc.nextLine();
				try {
					userService.deleteUser(username);
					System.out.println("[삭제 완료]");
				} catch (UserException e) {
					System.out.println("[삭제 실패] " + e.getMessage());
				}
				break;
			case 3:
				manageBoardsByAdmin();
				break;
			case 4:
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
		for (User u : users) {
			System.out.println(u);
		}
	}

	private void manageBoardsByAdmin() {
		try {
			List<BoardDTO> boards = boardController.getBoardService().getAllBoards();
			System.out.println("=== 전체 게시글 목록 ===");
			for (BoardDTO b : boards) {
				System.out.println(b); // toString 오버라이딩 되어 있어야 함
			}

			System.out.print("삭제할 게시글 ID 입력 (취소는 0): ");
			int boardId = sc.nextInt();
			sc.nextLine();

			if (boardId != 0) {
				boardController.getBoardService().deleteBoard(boardId);
				System.out.println("[게시글 삭제 완료]");
			} else {
				System.out.println("게시글 삭제 취소");
			}
		} catch (Exception e) {
			System.out.println("게시글 관리 중 오류 발생: " + e.getMessage());
		}
	}
	
	// 아이디 찾기
	public void findUsername() {
	    System.out.print("이름: ");
	    String name = sc.nextLine();
	    System.out.print("이메일: ");
	    String email = sc.nextLine();

	    try {
	        String username = userService.findUsernameByNameAndEmail(name, email);
	        System.out.println("회원님의 아이디는 '" + username + "' 입니다.");
	    } catch (UserException e) {
	        System.out.println("[아이디 찾기 실패] " + e.getMessage());
	    }
	}

	// 비밀번호 재설정
	public void resetPassword() {
	    System.out.print("아이디: ");
	    String username = sc.nextLine();
	    System.out.print("이름: ");
	    String name = sc.nextLine();
	    System.out.print("이메일: ");
	    String email = sc.nextLine();

	    try {
	        userService.resetPassword(username, name, email);
	    } catch (UserException e) {
	        System.out.println("[비밀번호 재설정 실패] " + e.getMessage());
	    }
	}

}
