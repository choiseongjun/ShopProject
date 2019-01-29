package go.Shop.com.Category.FreeBoard.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import go.Shop.com.Category.FreeBoard.Domain.BoardReplyVO;
import go.Shop.com.Category.FreeBoard.Domain.FileVO;
import go.Shop.com.Category.FreeBoard.Domain.boardVO;
import go.Shop.com.Category.FreeBoard.Service.BoardService;
import go.Shop.com.Configuration.FileUtil;
import go.Shop.com.Configuration.SearchVO;

@Controller
public class BoardController {
	@Autowired
    private BoardService boardSvc;
	

    @RequestMapping(value = "Category/FreeBoardList.do")
    public String boardList(SearchVO searchVO,ModelMap modelMap) throws Exception {
    	  
    	   if (searchVO.getBgno() == null) {
               searchVO.setBgno("1"); 
           }

    	searchVO.pageCalculate( boardSvc.selectBoardCount(searchVO) ); 

          List<?> listview   = boardSvc.selectBoardList(searchVO);

        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("searchVO", searchVO);

        return "Category/FreeBoard";
    }
    @RequestMapping(value = "Category/board1Form")
    public String boardForm(HttpServletRequest request, ModelMap modelMap) throws Exception {
    
    	String bgno = request.getParameter("bgno");
    	System.out.println("파라미터값+++++++="+bgno);
    	String brdno = request.getParameter("brdno");
        if (brdno!=null) {
        	boardVO boardInfo = boardSvc.selectBoardOne(brdno);
        	List<?> listview = boardSvc.selectBoard1FileList(brdno);
             
         	modelMap.addAttribute("boardInfo", boardInfo);
        	modelMap.addAttribute("listview", listview);
            bgno = boardInfo.getBgno();

        }
        modelMap.addAttribute("bgno", bgno);

        return "Category/FreeboardForm";
}

    
  
    @RequestMapping(value = "Category/FreeBoardSave")
    public String boardSave(HttpServletRequest request,@ModelAttribute boardVO boardInfo,HttpSession session) throws Exception {
	
    	String[] fileno = request.getParameterValues("fileno");
    	
	   FileUtil fs = new FileUtil();
	 
	   List<FileVO> filelist = fs.saveAllFiles(boardInfo.getUploadfile());

	   String writer=(String)session.getAttribute("userid");
    	boardInfo.setBrdwriter(writer);
    	boardSvc.insertBoard(boardInfo, filelist, fileno);
        return "redirect:/Category/FreeBoardList.do?bgno="+boardInfo.getBgno();
    }
    @RequestMapping(value = "Category/board1Read")
    public String boardRead(HttpServletRequest request, ModelMap modelMap) throws Exception {
            
            String brdno = request.getParameter("brdno");
            boardSvc.updateBoard1Read(brdno);
            boardVO boardInfo = boardSvc.selectBoardOne(brdno);
            List<?> listview = boardSvc.selectBoard1FileList(brdno);
            List<?> replylist = boardSvc.selectBoard1ReplyList(brdno);

            modelMap.addAttribute("boardInfo", boardInfo);
            modelMap.addAttribute("listview", listview);
            modelMap.addAttribute("replylist", replylist);

            return "Category/boardRead";
    }
    @RequestMapping(value = "Category/board1Update")
    public String boardUpdate(HttpServletRequest request, ModelMap modelMap) throws Exception {
            
            String brdno = request.getParameter("brdno");
          
            boardVO boardInfo = boardSvc.selectBoardOne(brdno);
            
            modelMap.addAttribute("boardInfo", boardInfo);
            
            return "Category/boardUpdate";
    }
    // 글 삭제
    @RequestMapping(value = "Category/board1Delete")
   	public String boardDelete(HttpServletRequest request) throws Exception {
    	
    	
    	String brdno = request.getParameter("brdno");
    	System.out.println("로그");
    	boardSvc.deleteBoardOne(brdno);
        
        return "redirect:/Category/FreeBoardList.do";
    }
    @RequestMapping(value = "Category/board1ReplySave")
    public String board5ReplySave(HttpServletRequest request,HttpSession session,BoardReplyVO boardReplyInfo,boardVO boardInfo){
    	String writer=(String)session.getAttribute("userid");
    	boardReplyInfo.setRewriter(writer);
        boardSvc.insertBoardReply(boardReplyInfo);
        System.out.println("댓글달았음.........");
        return "redirect:/Category/FreeBoardList.do?bgno="+boardInfo.getBgno();
    }




}
