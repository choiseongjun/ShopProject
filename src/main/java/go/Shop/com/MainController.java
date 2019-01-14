package go.Shop.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main() {
		return "Main";
	}
	@RequestMapping("/go.do")
    public String value() {
    	System.out.println("로그로그로그");
    	return null;
    }
	@RequestMapping("/go")
	public String go() {
		return "User/go";
	}
}
