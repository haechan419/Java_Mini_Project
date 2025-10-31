package memberBoard;

import java.util.Scanner;

import memberBoard.controller.UserController;
import memberBoard.repository.UserRepositoryImpl;
import memberBoard.service.UserServiceImpl;
import memberBoard.view.UserView;

// 실행 진입점

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserRepositoryImpl repo = new UserRepositoryImpl();
		UserServiceImpl service = new UserServiceImpl(repo);
		UserController controller = new UserController(service);
		UserView view = new UserView(controller);
		
		//UserView
		view.showMenu();
		}
	}


