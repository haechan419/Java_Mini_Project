// ============================================
// Main.java - 의존성 주입 및 실행
// ============================================
package memberBoard;

import memberBoard.controller.BoardController;
import memberBoard.controller.UserController;
import memberBoard.repository.BoardRepository;
import memberBoard.repository.BoardRepositoryImpl;
import memberBoard.repository.UserRepository;
import memberBoard.repository.UserRepositoryImpl;
import memberBoard.service.BoardService;
import memberBoard.service.BoardServiceImpl;
import memberBoard.service.UserService;
import memberBoard.service.UserServiceImpl;
import memberBoard.view.MainView;

public class Main {
    public static void main(String[] args) {
        // Repository 계층
        UserRepository userRepo = new UserRepositoryImpl();
        BoardRepository boardRepo = new BoardRepositoryImpl();
        
        // Service 계층
        UserService userService = new UserServiceImpl(userRepo);
        BoardService boardService = new BoardServiceImpl(boardRepo);
        
        // Controller 계층
        UserController userController = new UserController(userService);
        BoardController boardController = new BoardController(boardService);
        
        // View 계층
        MainView mainView = new MainView(userController, boardController);
        
        // 애플리케이션 실행
        mainView.start();
    }
}