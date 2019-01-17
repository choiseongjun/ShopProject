package go.Shop.com.Category.FreeBoard.Service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import go.Shop.com.Category.FreeBoard.Domain.BoardReplyVO;
import go.Shop.com.Category.FreeBoard.Domain.FileVO;
import go.Shop.com.Category.FreeBoard.Domain.boardVO;
import go.Shop.com.Configuration.SearchVO;
@Service
public class BoardService {
	@Autowired
	SqlSession sqlSession;
//	@Autowired
//    private DataSourceTransactionManager txManager;
	 
	public List<?> selectBoardList(SearchVO param) throws Exception {
        return sqlSession.selectList("board.FreeBoardList",param);
    }
	@Transactional
	public void insertBoard(boardVO param, List<FileVO> filelist, String[] fileno) throws Exception {

//	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        TransactionStatus status = txManager.getTransaction(def);
        
		try{
	    	if (param.getBrdno()==null || "".equals(param.getBrdno()))
	    		 sqlSession.insert("board.FreeBoardInsert", param);
	    	else sqlSession.update("board.updateBoard1", param);
	
	    	if (fileno != null) {
	    	    HashMap p = new HashMap();
	    	    p.put("fileno", fileno) ;
	    	    sqlSession.insert("board.deleteBoard1File", p);
	    	}
	    	
	    	for (FileVO f : filelist) {
	    		System.out.println("");
	    		f.setParentPK(param.getBrdno());
                sqlSession.insert("board.insertBoard1File", f);
	    	}
		} catch (Exception ex) {
			System.out.println("데이터 저장 오류: " + ex.toString());
		}	    	

		}

  
 
    public boardVO selectBoardOne(String param) throws Exception {
		return sqlSession.selectOne("board.selectBoard1One", param);
    }
    
    public void deleteBoardOne(String param) throws Exception {
		sqlSession.delete("board.deleteBoard1One", param);
    }
    public void updateBoard1Read(String param) throws Exception {
		sqlSession.insert("board.updateBoard1Read", param);
    }

	public Integer selectBoardCount(SearchVO param) {
		return sqlSession.selectOne("board.selectBoard1Count",param);

	}
	public List<?> selectBoard1FileList(String param) throws Exception {
		return sqlSession.selectList("board.selectBoard1FileList", param);
    }
	public void insertBoardReply(BoardReplyVO param) {
		   if (param.getReno()==null || "".equals(param.getReno())) {
	            sqlSession.insert("board.insertBoard1Reply", param);
	        } else {
	            sqlSession.insert("board.updateBoard1Reply", param);
	        }
	}
	public List<?> selectBoard1ReplyList(String param) {

        return sqlSession.selectList("board.selectBoard1ReplyList", param);

	}
	 public void deleteBoard5Reply(String param) {
	        sqlSession.delete("board.deleteBoard1Reply", param);
	    }    
}
