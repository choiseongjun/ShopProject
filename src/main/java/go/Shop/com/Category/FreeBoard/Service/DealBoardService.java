package go.Shop.com.Category.FreeBoard.Service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go.Shop.com.Category.FreeBoard.Domain.BoardReplyVO;
import go.Shop.com.Category.FreeBoard.Domain.FileVO;
import go.Shop.com.Category.FreeBoard.Domain.boardVO;
import go.Shop.com.Configuration.SearchVO;
@Service
public class DealBoardService {
	@Autowired
	SqlSession sqlSession;
	 private static final Logger logger = LoggerFactory.getLogger(DealBoardService.class);


//	@Autowired
//    private DataSourceTransactionManager txManager;
	 
	public List<?> selectBoardList(SearchVO param) throws Exception {
	
        return sqlSession.selectList("DealBoard.FreeBoardList",param);
    }
	public void insertBoard(boardVO param, List<FileVO> filelist, String[] fileno) throws Exception {

//	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = txManager.getTransaction(def);
        	System.out.println("파라미터란???"+param);
		try{
	    	if (param.getBrdno()==null ||"".equals(param.getBrdno()))
	    		 sqlSession.insert("DealBoard.FreeBoardInsert", param);
	    	else {sqlSession.update("DealBoard.updateBoard1", param);
	    	}
	    	if (fileno != null) {
	    	    HashMap p = new HashMap();
	    	    p.put("fileno", fileno) ;
	    	    sqlSession.insert("DealBoard.deleteBoard1File", p);
	    	}
	    	
	    	for (FileVO f : filelist) {
	    		System.out.println("");
	    		f.setParentPK(param.getBrdno());
                sqlSession.insert("DealBoard.insertBoard1File", f);
	    	}
		} catch (Exception ex) {
			System.out.println("데이터 저장 오류: " + ex.toString());
		}	    	

		}
 
  
 
    public boardVO selectBoardOne(String param) throws Exception {
		return sqlSession.selectOne("DealBoard.selectBoard1One", param);
    }
    
    public void deleteBoardOne(String param) throws Exception {
		sqlSession.delete("DealBoard.deleteBoard1One", param);
    }
    public void updateBoard1Read(String param) throws Exception {
		sqlSession.insert("DealBoard.updateBoard1Read", param);
    }

	public Integer selectBoardCount(SearchVO param) {
		return sqlSession.selectOne("DealBoard.selectBoard1Count",param);

	}
	public List<?> selectBoard1FileList(String param) throws Exception {
		return sqlSession.selectList("DealBoard.selectBoard1FileList", param);
    }
	public void insertBoardReply(BoardReplyVO param) {
		 if (param.getReno() == null || "".equals(param.getReno())) {
	            if (param.getReparent() != null) {
	                BoardReplyVO replyInfo = sqlSession.selectOne("selectBoard1ReplyParent", param.getReparent());
	                param.setRedepth(replyInfo.getRedepth());
	                param.setReorder(replyInfo.getReorder() + 1);
	                sqlSession.selectOne("updateBoard1ReplyOrder", replyInfo);
	            } else {
	                Integer reorder = sqlSession.selectOne("selectBoard1ReplyMaxOrder", param.getBrdno());
	                param.setReorder(reorder);
	            }
	            
	            sqlSession.insert("insertBoard1Reply", param);
	        } else {
	            sqlSession.insert("updateBoard1Reply", param);
	        }
	}
	public List<?> selectBoard1ReplyList(String param) {

        return sqlSession.selectList("DealBoard.selectBoard1ReplyList", param);

	}
	 public void deleteBoard5Reply(String param) {
	        sqlSession.delete("DealBoard.deleteBoard1Reply", param);
	    }    
}
