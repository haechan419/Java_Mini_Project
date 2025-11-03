// ============================================
// UserController.java - 사용자 컨트롤러
// ============================================
package memberBoard.controller;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.service.UserService;
import memberBoard.view.InputHandler;
import memberBoard.view.MessageView;

public class UserController {
    
    private final UserService userService;
    private final InputHandler inputHandler;
    private final MessageView messageView;
    
    public UserController(UserService userService) {
        this.userService = userService;
        this.inputHandler = new InputHandler();
        this.messageView = new MessageView();
    }
    
    // 회원가입
    public void register() {
        try {
            messageView.printHeader("회원가입");
            
            String username = inputHandler.getString("아이디");
            String password = inputHandler.getPassword("비밀번호");
            String name = inputHandler.getString("이름");
            String phone = inputHandler.getPhone("연락처 (010-0000-0000)");
            String email = inputHandler.getEmail("이메일");
            
            UserDTO userDTO = new UserDTO(username, password, name, phone, email);
            userService.register(userDTO);
            
            messageView.printSuccess("회원가입 완료");
        } catch (UserException e) {
            messageView.printError("회원가입 실패", e.getMessage());
        }
    }
    
    // 로그인
    public User login() {
        try {
            messageView.printHeader("로그인");
            
            String username = inputHandler.getString("아이디");
            String password = inputHandler.getPassword("비밀번호");
            
            User user = userService.login(username, password);
            messageView.printSuccess("로그인 성공", user.getName() + "님 환영합니다!");
            
            return user;
        } catch (UserException e) {
            messageView.printError("로그인 실패", e.getMessage());
            return null;
        }
    }
    
    // 회원정보 조회
    public void viewProfile(User user) {
        messageView.printHeader("회원정보");
        messageView.printUserInfo(user);
    }
    
    // 회원정보 수정
    public void updateProfile(User user) {
        try {
            messageView.printHeader("회원정보 수정");
            
            String password = inputHandler.getOptionalPassword("새 비밀번호 (변경 안함: Enter)");
            String phone = inputHandler.getOptionalPhone("새 연락처 (변경 안함: Enter)");
            String email = inputHandler.getOptionalEmail("새 이메일 (변경 안함: Enter)");
            
            userService.updateUser(user.getUsername(), password, phone, email);
            messageView.printSuccess("회원정보 수정 완료");
        } catch (UserException e) {
            messageView.printError("회원정보 수정 실패", e.getMessage());
        }
    }
    
    // 회원탈퇴
    public boolean deleteAccount(User user) {
        try {
            messageView.printHeader("회원탈퇴");
            
            boolean confirm = inputHandler.getConfirmation("정말로 탈퇴하시겠습니까?");
            if (!confirm) {
                messageView.printInfo("회원탈퇴가 취소되었습니다.");
                return false;
            }
            
            userService.deleteUser(user.getUsername());
            messageView.printSuccess("회원탈퇴 완료");
            return true;
        } catch (UserException e) {
            messageView.printError("회원탈퇴 실패", e.getMessage());
            return false;
        }
    }
    
    // 아이디 찾기
    public void findUsername() {
        try {
            messageView.printHeader("아이디 찾기");
            
            String name = inputHandler.getString("이름");
            String email = inputHandler.getEmail("이메일");
            
            String username = userService.findUsernameByNameAndEmail(name, email);
            messageView.printSuccess("아이디 찾기 성공", 
                "회원님의 아이디는 '" + username + "' 입니다.");
        } catch (UserException e) {
            messageView.printError("아이디 찾기 실패", e.getMessage());
        }
    }
    
    // 비밀번호 재설정
    public void resetPassword() {
        try {
            messageView.printHeader("비밀번호 재설정");
            
            String username = inputHandler.getString("아이디");
            String name = inputHandler.getString("이름");
            String email = inputHandler.getEmail("이메일");
            
            userService.resetPassword(username, name, email);
            messageView.printSuccess("비밀번호 재설정 완료", 
                "임시 비밀번호가 발급되었습니다.");
        } catch (UserException e) {
            messageView.printError("비밀번호 재설정 실패", e.getMessage());
        }
    }
    
    // 관리자: 전체 회원 조회
    public void viewAllUsers() {
        messageView.printHeader("전체 회원 목록");
        messageView.printUserList(userService.getAllUsers());
    }
    
    // 관리자: 회원 삭제
    public void deleteUserByAdmin() {
        try {
            messageView.printHeader("회원 삭제");
            
            String username = inputHandler.getString("삭제할 회원 아이디");
            
            boolean confirm = inputHandler.getConfirmation(
                "'" + username + "' 회원을 삭제하시겠습니까?");
            if (!confirm) {
                messageView.printInfo("삭제가 취소되었습니다.");
                return;
            }
            
            userService.deleteUser(username);
            messageView.printSuccess("회원 삭제 완료");
        } catch (UserException e) {
            messageView.printError("회원 삭제 실패", e.getMessage());
        }
    }
}