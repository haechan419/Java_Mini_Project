// ============================================
// UserServiceImpl.java - 구현체
// ============================================
package memberBoard.service;

import java.util.List;
import java.util.Optional;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.repository.UserRepository;
import memberBoard.security.PasswordUtil;
import memberBoard.util.PasswordGenerator;
import memberBoard.validator.UserValidator;

public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordUtil passwordUtil;
    private final PasswordGenerator passwordGenerator;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userValidator = new UserValidator();
        this.passwordUtil = new PasswordUtil();
        this.passwordGenerator = new PasswordGenerator();
    }
    
    @Override
    public void register(UserDTO userDTO) throws UserException {
        // 입력 유효성 검증
        userValidator.validateRegistration(userDTO);
        
        // 중복 사용자 확인
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserException("이미 존재하는 사용자입니다.");
        }
        
        // 비밀번호 암호화
        String hashedPassword = passwordUtil.hashPassword(userDTO.getPassword());
        
        // User 엔티티 생성 및 저장
        User user = new User(
            0,
            userDTO.getUsername(),
            hashedPassword,
            userDTO.getName(),
            userDTO.getPhone(),
            userDTO.getEmail()
        );
        
        userRepository.save(user);
    }
    
    @Override
    public User login(String username, String password) throws UserException {
        // 사용자 조회
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UserException("존재하지 않는 사용자입니다.");
        }
        
        User user = userOpt.get();
        
        // 계정 활성화 확인
        if (!user.isActive()) {
            throw new UserException("비활성화된 계정입니다.");
        }
        
        // 비밀번호 검증
        if (!passwordUtil.checkPassword(password, user.getPassword())) {
            throw new UserException("비밀번호가 일치하지 않습니다.");
        }
        
        return user;
    }
    
    @Override
    public void updateUser(String username, String password, String phone, String email) 
            throws UserException {
        // 사용자 조회
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UserException("존재하지 않는 사용자입니다.");
        }
        
        User user = userOpt.get();
        
        // 비밀번호 변경 시 암호화
        String hashedPassword = null;
        if (password != null && !password.isEmpty()) {
            userValidator.validatePassword(password);
            hashedPassword = passwordUtil.hashPassword(password);
        }
        
        // 전화번호 유효성 검증
        if (phone != null && !phone.isEmpty()) {
            userValidator.validatePhone(phone);
        }
        
        // 이메일 유효성 검증
        if (email != null && !email.isEmpty()) {
            userValidator.validateEmail(email);
        }
        
        // 사용자 정보 업데이트
        user.updateProfile(hashedPassword, phone, email);
        userRepository.update(user);
    }
    
    @Override
    public void deleteUser(String username) throws UserException {
        // 사용자 존재 확인
        if (!userRepository.existsByUsername(username)) {
            throw new UserException("삭제할 사용자가 존재하지 않습니다.");
        }
        
        userRepository.delete(username);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public String findUsernameByNameAndEmail(String name, String email) 
            throws UserException {
        Optional<User> userOpt = userRepository.findByNameAndEmail(name, email);
        
        if (!userOpt.isPresent()) {
            throw new UserException("일치하는 사용자 정보가 없습니다.");
        }
        
        return userOpt.get().getUsername();
    }
    
    @Override
    public void resetPassword(String username, String name, String email) 
            throws UserException {
        // 사용자 조회
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UserException("존재하지 않는 사용자입니다.");
        }
        
        User user = userOpt.get();
        
        // 사용자 정보 확인
        if (!user.getName().equalsIgnoreCase(name) || 
            !user.getEmail().equalsIgnoreCase(email)) {
            throw new UserException("사용자 정보가 일치하지 않습니다.");
        }
        
        // 임시 비밀번호 생성 및 설정
        String tempPassword = passwordGenerator.generate();
        String hashedPassword = passwordUtil.hashPassword(tempPassword);
        
        user.setPassword(hashedPassword);
        userRepository.update(user);
        
        System.out.println("[임시 비밀번호 발급] 임시 비밀번호: " + tempPassword);
        System.out.println("로그인 후 반드시 비밀번호를 변경해주세요.");
    }
}