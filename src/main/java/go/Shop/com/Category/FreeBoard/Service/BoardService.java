package go.Shop.com.Category.FreeBoard.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go.Shop.com.Category.FreeBoard.Domain.PageVO;
import go.Shop.com.Category.FreeBoard.Domain.boardVO;
import go.Shop.com.Configuration.SearchVO;
@Service
public class BoardService {
	@Autowired
	SqlSession sqlSession;

	public List<?> selectBoardList(SearchVO param) throws Exception {
        return sqlSession.selectList("board.FreeBoardList",param);
    }

	public void insertBoard(boardVO param) throws Exception {
	    if (param.getBrdno()==null || "".equals(param.getBrdno()))

        sqlSession.insert("board.FreeBoardInsert", param);
	    else sqlSession.update("board.updateBoard1", param);

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
}
