package memberBoard.view;

//============================================
//MainView.java - 메인 화면
//============================================

import memberBoard.controller.BoardController;
import memberBoard.controller.UserController;
import memberBoard.domain.entity.User;

public class MainView {
 
 private final UserController userController;
 private final BoardController boardController;
 private final InputHandler inputHandler;
 private final MessageView messageView;
 
 public MainView(UserController userController, BoardController boardController) {
     this.userController = userController;
     this.boardController = boardController;
     this.inputHandler = new InputHandler();
     this.messageView = new MessageView();
 }
 
 // 애플리케이션 시작
 public void start() {
     messageView.printWelcome();
     showMainMenu();
 }
 
 // 메인 메뉴
 private void showMainMenu() {
     while (true) {
         messageView.printHeader("회원 관리 시스템");
         System.out.println("1. 회원가입");
         System.out.println("2. 로그인");
         System.out.println("3. 아이디 찾기");
         System.out.println("4. 비밀번호 재설정");
         System.out.println("5. 종료");
         
         int choice = inputHandler.getMenuChoice(1, 5);
         
         switch (choice) {
             case 1:
                 userController.register();
                 break;
             case 2:
                 User user = userController.login();
                 if (user != null) {
                     if (user.isAdmin()) {
                         showAdminMenu(user);
                     } else {
                         showUserMenu(user);
                     }
                 }
                 break;
             case 3:
                 userController.findUsername();
                 break;
             case 4:
                 userController.resetPassword();
                 break;
             case 5:
                 messageView.printInfo("프로그램을 종료합니다.");
                 return;
         }
     }
 }
 
 // 일반 사용자 메뉴
 private void showUserMenu(User user) {
     while (true) {
         messageView.printHeader("회원 메뉴");
         System.out.println("1. 회원정보 보기");
         System.out.println("2. 회원정보 수정");
         System.out.println("3. 회원탈퇴");
         System.out.println("4. 게시판으로 이동");
         System.out.println("5. 로그아웃");
         
         int choice = inputHandler.getMenuChoice(1, 5);
         
         switch (choice) {
             case 1:
                 userController.viewProfile(user);
                 break;
             case 2:
                 userController.updateProfile(user);
                 break;
             case 3:
                 if (userController.deleteAccount(user)) {
                     return; // 탈퇴 성공 시 메인으로
                 }
                 break;
             case 4:
                 showBoardMenu(user);
                 break;
             case 5:
                 messageView.printInfo("로그아웃 되었습니다.");
                 return;
         }
     }
 }
 
 // 관리자 메뉴
 private void showAdminMenu(User admin) {
     while (true) {
         messageView.printHeader("관리자 메뉴");
         System.out.println("1. 전체 회원 목록 조회");
         System.out.println("2. 회원 삭제");
         System.out.println("3. 게시글 관리");
         System.out.println("4. 게시판으로 이동");
         System.out.println("5. 로그아웃");
         
         int choice = inputHandler.getMenuChoice(1, 5);
         
         switch (choice) {
             case 1:
                 userController.viewAllUsers();
                 break;
             case 2:
                 userController.deleteUserByAdmin();
                 break;
             case 3:
                 boardController.manageBoardsByAdmin();
                 break;
             case 4:
                 showBoardMenu(admin);
                 break;
             case 5:
                 messageView.printInfo("로그아웃합니다.");
                 return;
         }
     }
 }
 
 // 게시판 메뉴
 private void showBoardMenu(User user) {
     while (true) {
         messageView.printHeader("게시판 메뉴");
         System.out.println("1. 게시글 작성");
         System.out.println("2. 내 게시글 목록");
         System.out.println("3. 전체 게시글 목록");
         System.out.println("4. 게시글 상세 조회");
         System.out.println("5. 게시글 수정");
         System.out.println("6. 게시글 삭제");
         System.out.println("7. 이전 메뉴로");
         
         int choice = inputHandler.getMenuChoice(1, 7);
         
         switch (choice) {
             case 1:
                 boardController.createBoard(user);
                 break;
             case 2:
                 boardController.viewMyBoards(user);
                 break;
             case 3:
                 boardController.viewAllBoards();
                 break;
             case 4:
                 boardController.viewBoard();
                 break;
             case 5:
                 boardController.updateBoard(user);
                 break;
             case 6:
                 boardController.deleteBoard(user);
                 break;
             case 7:
                 return;
         }
     }
 }
}