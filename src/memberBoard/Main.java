package memberBoard;

import memberBoard.controller.BoardController;
import memberBoard.controller.UserController;
import memberBoard.repository.BoardRepositoryImpl;
import memberBoard.repository.UserRepositoryImpl;
import memberBoard.service.BoardServiceImpl;
import memberBoard.service.UserServiceImpl;
import memberBoard.view.UserView;

public class Main {
	public static void main(String[] args) {
		UserRepositoryImpl userRepo = new UserRepositoryImpl();
		UserServiceImpl userService = new UserServiceImpl(userRepo);

		BoardRepositoryImpl boardRepo = new BoardRepositoryImpl();
		BoardServiceImpl boardService = new BoardServiceImpl(boardRepo);
		BoardController boardController = new BoardController(boardService);

		UserController userController = new UserController(userService, boardController);

		UserView view = new UserView(userController);
		view.showMenu();
	}
}
