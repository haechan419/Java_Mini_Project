package memberBoard.controller;

import java.util.List;
import java.util.Scanner;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.entity.User;
import memberBoard.service.BoardService;

// 게시글 CRUD

public class BoardController {
	private final BoardService boardService;
	private final Scanner sc = new Scanner(System.in);

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 게시판 메뉴 (로그인한 User 전달)
	public void showBoardMenu(User user) {
		while (true) {
			System.out.println("\n=== 게시판 메뉴 ===");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 내 게시글 목록");
			System.out.println("3. 게시글 수정");
			System.out.println("4. 게시글 삭제");
			System.out.println("5. 전체 게시글 조회");
			System.out.println("6. 종료");
			System.out.print("선택: ");

			int choice = sc.nextInt();
			sc.nextLine();

			try {
				switch (choice) {
				case 1:
					createBoard(user);
					break;
				case 2:
					listMyBoards(user);
					break;
				case 3:
					updateBoard(user);
					break;
				case 4:
					deleteBoard(user);
					break;
				case 5:
					listAllBoards();
					break;
				case 6:
					System.out.println("게시판을 종료합니다.");
					return;
				default:
					System.out.println("잘못된 선택입니다.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생: " + e.getMessage());
			}
		}
	}

	private void createBoard(User user) throws Exception {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        BoardDTO dto = new BoardDTO(title, content, user.getId());
        boardService.createBoard(dto);
        System.out.println("[게시글 작성 완료]");
    }

    private void listMyBoards(User user) throws Exception {
        List<BoardDTO> boards = boardService.getBoardsByUserId(user.getId());
        System.out.println("=== 내 게시글 목록 ===");
        for (BoardDTO b : boards) {
            System.out.println(b);
        }
    }

    private void updateBoard(User user) throws Exception {
        System.out.print("수정할 게시글 ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        BoardDTO dto = boardService.getBoardById(id);
        if (dto == null || dto.getUserId() != user.getId()) {
            System.out.println("해당 게시글이 없거나 권한이 없습니다.");
            return;
        }

        System.out.print("새 제목(수정하지 않으려면 Enter): ");
        String title = sc.nextLine();
        System.out.print("새 내용(수정하지 않으려면 Enter): ");
        String content = sc.nextLine();

        if (!title.isEmpty())
            dto.setTitle(title);
        if (!content.isEmpty())
            dto.setContent(content);

        boardService.updateBoard(dto);
        System.out.println("[게시글 수정 완료]");
    }

    private void deleteBoard(User user) throws Exception {
        System.out.print("삭제할 게시글 ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        BoardDTO dto = boardService.getBoardById(id);
        if (dto == null || dto.getUserId() != user.getId()) {
            System.out.println("해당 게시글이 없거나 권한이 없습니다.");
            return;
        }

        boardService.deleteBoard(id);
        System.out.println("[게시글 삭제 완료]");
    }

    private void listAllBoards() throws Exception {
        List<BoardDTO> boards = boardService.getAllBoards();
        System.out.println("=== 전체 게시글 목록 ===");
        for (BoardDTO b : boards) {
            System.out.println(b);
        }
    }
}
