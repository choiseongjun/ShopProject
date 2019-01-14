package go.Shop.com.User;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import go.Shop.com.User.Domain.MemberDTO;
import go.Shop.com.User.Service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {	
		
		System.out.println("로그로그로그로그");
		String filename="-";
		if(!dto.getFile1().isEmpty()) {
			filename=dto.getFile1().getOriginalFilename();
		try {
			String path="C:\\Users\\ucssystem\\git\\SpringBoard\\src\\main\\webapp\\resources\\assets\\images\\ProFilePicture\\";
			new File(path).mkdir();
			dto.getFile1().transferTo(new File(path+filename));
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		dto.setProfileimage(filename);
		memberService.insertMember(dto);
		return "redirect:/";
	}
	@RequestMapping("member/login_check.do")
	public ModelAndView login_check(
			MemberDTO dto, HttpSession session,
			ModelAndView mav,MemberDTO param,
			HttpServletRequest request,HttpServletResponse response
			) throws IOException {
		
		request.getSession().setAttribute("TAATLoginId", param.getUserid());
		
//		SesssionEventListener listener = new SesssionEventListener();
//        request.getSession().setAttribute(param.getUserid(), listener);  
		
        MemberDTO user = memberService.loginCheck(dto);
        if(user == null) {//로그인 시도하려는 아이디가 존재하지 않으면..
        
/*        	response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
			out.flush();
			out.close();*/
        	mav.addObject("message","error");
			mav.setViewName("User/Login");
        }else {//로그인 시도하려는 아이디가 존재하면..
		
      //암호화되지 않은 비밀번호(클라이언트에서 넘어온 값)
		String userPasswd = dto.getPasswd();
		//암호화된비밀번호(db에 저장되어진 값)
		String userHashedPasswd = user.getPasswd();
		//비밀번호 일치 검사, 일치하면  true
		boolean loginResult = passwordEncoder.matches(userPasswd, userHashedPasswd);
		
		//비밀번호 일치 시(로그인성공)
		if(loginResult) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", user.getName());
			session.setAttribute("profileImage", user.getProfileimage());
			System.out.println(user.getProfileimage()+"(*&^%$#$%&*(*&^*%$#$*$*%$%^&11111111111111111111");
			
			String savePage = (String)session.getAttribute("savePage");
			
			if(savePage!=null) {
				mav.setViewName("redirect:/"+savePage);
				session.setAttribute("savePage", null);
				return mav;
			}
			mav.setViewName("redirect:/");
		}else {
			mav.addObject("message","error");
			mav.setViewName("User/login");
			/*response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
			out.flush();
			*/
		}
          /*  session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", admin);*/
        }
		return mav;	
	}
	@RequestMapping("member/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	//임시비밀번호 생성기
	public String getNewPw() {
		String pw = "";
		String tokenArray[] = {
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "O", "P", "R", "S", "T", "U", "X",
				"Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9"
		};
		for (int i = 0; i < 10; i++) {
			int idx = (int) (Math.random() * tokenArray.length);
			pw += (tokenArray[idx]);
		}
		return pw;
	}
}
