package memberBoard.view;

//============================================
//MessageView.java - 메시지 출력
//============================================

import java.util.List;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.entity.User;

public class MessageView {
 
 private static final String LINE = "=".repeat(50);
 private static final String DASH = "-".repeat(50);
 
 // 환영 메시지
 public void printWelcome() {
     System.out.println(LINE);
     System.out.println("회원 관리 시스템에 오신 것을 환영합니다!");
     System.out.println(LINE);
 }
 
 // 헤더 출력
 public void printHeader(String title) {
     System.out.println();
     System.out.println(LINE);
     System.out.println("  " + title);
     System.out.println(LINE);
 }
 
 // 성공 메시지
 public void printSuccess(String message) {
     System.out.println("[✓] " + message);
 }
 
 public void printSuccess(String title, String message) {
     System.out.println("[✓] " + title);
     System.out.println("    " + message);
 }
 
 // 오류 메시지
 public void printError(String message) {
     System.out.println("[✗] " + message);
 }
 
 public void printError(String title, String message) {
     System.out.println("[✗] " + title + ": " + message);
 }
 
 // 정보 메시지
 public void printInfo(String message) {
     System.out.println("[i] " + message);
 }
 
 // 사용자 정보 출력
 public void printUserInfo(User user) {
     System.out.println(DASH);
     System.out.println("아이디: " + user.getUsername());
     System.out.println("이름: " + user.getName());
     System.out.println("연락처: " + user.getPhone());
     System.out.println("이메일: " + user.getEmail());
     System.out.println("권한: " + user.getRole().getDescription());
     System.out.println("상태: " + (user.isActive() ? "활성" : "비활성"));
     System.out.println("가입일: " + user.getCreatedAt());
     System.out.println("수정일: " + user.getUpdatedAt());
     System.out.println(DASH);
 }
 
 // 사용자 목록 출력
 public void printUserList(List<User> users) {
     if (users.isEmpty()) {
         System.out.println("등록된 회원이 없습니다.");
         return;
     }
     
     System.out.println(DASH);
     System.out.printf("%-5s %-15s %-10s %-20s %-10s\n", 
         "ID", "아이디", "이름", "이메일", "권한");
     System.out.println(DASH);
     
     for (User user : users) {
         System.out.printf("%-5d %-15s %-10s %-20s %-10s\n",
             user.getId(),
             user.getUsername(),
             user.getName(),
             user.getEmail(),
             user.getRole().getDescription()
         );
     }
     System.out.println(DASH);
     System.out.println("총 " + users.size() + "명");
 }
 
 // 게시글 목록 출력
 public void printBoardList(List<BoardDTO> boards) {
     if (boards.isEmpty()) {
         System.out.println("게시글이 없습니다.");
         return;
     }
     
     System.out.println(DASH);
     System.out.printf("%-5s %-30s %-10s %-20s\n", 
         "ID", "제목", "작성자ID", "작성일");
     System.out.println(DASH);
     
     for (BoardDTO board : boards) {
         String title = board.getTitle();
         if (title.length() > 30) {
             title = title.substring(0, 27) + "...";
         }
         System.out.printf("%-5d %-30s %-10d %-20s\n",
             board.getId(),
             title,
             board.getUserId(),
             board.getCreatedAt()
         );
     }
     System.out.println(DASH);
     System.out.println("총 " + boards.size() + "개");
 }
 
 // 게시글 상세 출력
 public void printBoardDetail(BoardDTO board) {
     System.out.println(DASH);
     System.out.println("게시글 ID: " + board.getId());
     System.out.println("제목: " + board.getTitle());
     System.out.println("작성자 ID: " + board.getUserId());
     System.out.println("작성일: " + board.getCreatedAt());
     System.out.println("수정일: " + board.getUpdatedAt());
     System.out.println(DASH);
     System.out.println("내용:");
     System.out.println(board.getContent());
     System.out.println(DASH);
 }
}