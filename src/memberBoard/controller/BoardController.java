// ============================================
// BoardController.java - 게시글 컨트롤러
// ============================================
package memberBoard.controller;

import java.util.List;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.BoardException;
import memberBoard.service.BoardService;
import memberBoard.view.InputHandler;
import memberBoard.view.MessageView;

public class BoardController {
    
    private final BoardService boardService;
    private final InputHandler inputHandler;
    private final MessageView messageView;
    
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
        this.inputHandler = new InputHandler();
        this.messageView = new MessageView();
    }
    
    // 게시글 작성
    public void createBoard(User user) {
        try {
            messageView.printHeader("게시글 작성");
            
            String title = inputHandler.getString("제목");
            String content = inputHandler.getMultilineString("내용");
            
            BoardDTO dto = new BoardDTO(title, content, user.getId());
            boardService.createBoard(dto);
            
            messageView.printSuccess("게시글 작성 완료");
        } catch (BoardException e) {
            messageView.printError("게시글 작성 실패", e.getMessage());
        }
    }
    
    // 내 게시글 목록
    public void viewMyBoards(User user) {
        messageView.printHeader("내 게시글 목록");
        List<BoardDTO> boards = boardService.getBoardsByUserId(user.getId());
        messageView.printBoardList(boards);
    }
    
    // 전체 게시글 목록
    public void viewAllBoards() {
        messageView.printHeader("전체 게시글 목록");
        List<BoardDTO> boards = boardService.getAllBoards();
        messageView.printBoardList(boards);
    }
    
    // 게시글 상세 조회
    public void viewBoard() {
        try {
            int id = inputHandler.getInt("게시글 ID");
            
            BoardDTO board = boardService.getBoardById(id);
            messageView.printBoardDetail(board);
        } catch (BoardException e) {
            messageView.printError("게시글 조회 실패", e.getMessage());
        }
    }
    
    // 게시글 수정
    public void updateBoard(User user) {
        try {
            messageView.printHeader("게시글 수정");
            
            int id = inputHandler.getInt("수정할 게시글 ID");
            
            BoardDTO dto = boardService.getBoardById(id);
            
            // 권한 확인
            if (!user.isAdmin() && dto.getUserId() != user.getId()) {
                messageView.printError("본인 게시글만 수정할 수 있습니다.");
                return;
            }
            
            String title = inputHandler.getOptionalString(
                "새 제목 (변경 안함: Enter, 현재: " + dto.getTitle() + ")");
            String content = inputHandler.getOptionalMultilineString(
                "새 내용 (변경 안함: Enter)");
            
            if (title != null && !title.isEmpty()) {
                dto.setTitle(title);
            }
            if (content != null && !content.isEmpty()) {
                dto.setContent(content);
            }
            
            boardService.updateBoard(dto);
            messageView.printSuccess("게시글 수정 완료");
        } catch (BoardException e) {
            messageView.printError("게시글 수정 실패", e.getMessage());
        }
    }
    
    // 게시글 삭제
    public void deleteBoard(User user) {
        try {
            messageView.printHeader("게시글 삭제");
            
            int id = inputHandler.getInt("삭제할 게시글 ID");
            
            boolean confirm = inputHandler.getConfirmation("정말로 삭제하시겠습니까?");
            if (!confirm) {
                messageView.printInfo("삭제가 취소되었습니다.");
                return;
            }
            
            if (user.isAdmin()) {
                // 관리자는 모든 게시글 삭제 가능
                boardService.deleteBoard(id);
            } else {
                // 일반 사용자는 본인 게시글만 삭제 가능
                boardService.deleteBoardByUser(id, user.getId());
            }
            
            messageView.printSuccess("게시글 삭제 완료");
        } catch (BoardException e) {
            messageView.printError("게시글 삭제 실패", e.getMessage());
        }
    }
    
    // 관리자: 게시글 관리
    public void manageBoardsByAdmin() {
        try {
            messageView.printHeader("게시글 관리");
            
            List<BoardDTO> boards = boardService.getAllBoards();
            messageView.printBoardList(boards);
            
            int boardId = inputHandler.getInt("삭제할 게시글 ID (취소: 0)");
            
            if (boardId == 0) {
                messageView.printInfo("게시글 삭제 취소");
                return;
            }
            
            boolean confirm = inputHandler.getConfirmation("정말로 삭제하시겠습니까?");
            if (!confirm) {
                messageView.printInfo("삭제가 취소되었습니다.");
                return;
            }
            
            boardService.deleteBoard(boardId);
            messageView.printSuccess("게시글 삭제 완료");
        } catch (BoardException e) {
            messageView.printError("게시글 관리 실패", e.getMessage());
        }
    }
}