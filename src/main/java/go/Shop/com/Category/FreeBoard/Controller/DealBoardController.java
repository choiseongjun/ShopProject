package go.Shop.com.Category.FreeBoard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DealBoardController {

	@RequestMapping("Category/DealBoardList.do")
	public String aa() {
		System.out.println("경로탐...");
		return "Category/DealBoard"; 
	}
	@RequestMapping("Category/DealBoardDetail.do")
	public String bb() {
		System.out.println("디테일 경로...");
		return "Category/DealBoardDetail"; 
	}
}
