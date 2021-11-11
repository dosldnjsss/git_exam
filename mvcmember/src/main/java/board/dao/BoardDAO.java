package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;


public class BoardDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); 
	private Connection conn = null; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	
	private DataSource ds;
	
	private static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized(BoardDAO.class){
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();//Context: 인터페이스 (다른 클래스로 생성)
			ds= (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");//네이밍 서비스이름을 쓰면 그것이 가진 데이터를 다 데이터소스에 넘겨주라 
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//public void write(BoardDTO boardDTO) {
	public void write(Map<String, String> map) {
		String sql = "insert into board(seq,id,name,email,subject,content,ref,logtime) values(seq_board.nextval,?,?,?,?,?,seq_board.nextval,sysdate)";
		//String sql = "insert into board(seq,id,name,email,subject,content,ref) values(seq_board.nextval,?,?,?,?,?,seq_board.currval)";
		try {
			conn= ds.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1,boardDTO.getId());
//			pstmt.setString(2,boardDTO.getName());
//			pstmt.setString(3,boardDTO.getEmail());
//			pstmt.setString(4,boardDTO.getSubject());
//			pstmt.setString(5,boardDTO.getContent());

			
			pstmt.setString(1,map.get("id"));
			pstmt.setString(2,map.get("name"));
			pstmt.setString(3,map.get("email"));
			pstmt.setString(4,map.get("subject"));
			pstmt.setString(5,map.get("content"));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public ArrayList<BoardDTO> getBoardList(int startNum, int endNum){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		
		String sql = "select * "
				   + "from(select rownum rn, tt.* "
				   + "from(select * from board order by ref desc, step asc)tt) " 
				   + "where rn>=? and rn<=?";
		
		
		try {
			conn= ds.getConnection();//생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startNum);
			pstmt.setInt(2,endNum);
			
			rs =  pstmt.executeQuery();//실행
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				//boardDTO.setLogtime(rs.getDate("logtime"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime")));
				
				
				list.add(boardDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
		} finally {
		
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
	
		}
		
		
		return list;
	}

	public int getTotalA() {
		int totalA =0;
		
		String sql="select count(*) from board";
		
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);//생성
			
			rs= pstmt.executeQuery();
			
			rs.next();//포지션 맞추기
			totalA= rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}	
		return totalA;
	}
	public ArrayList<BoardDTO> getGuestbookContent(int seq){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		//getConnection();

		
		String sql ="select * from board where seq=?";
	
		
		try {
			conn=ds.getConnection();//커넥션은 데이터 소스로부터 생긴다
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,seq);

			
			rs = pstmt.executeQuery();//실행
			
			while(rs.next()) { //rs.next() - 위치를 맞춘다 -T/F
				BoardDTO boardDTO = new BoardDTO();

				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				//boardDTO.setLogtime(rs.getDate("logtime"));
				boardDTO.setLogtime(sdf.format(rs.getDate("logtime")));
				
				
				list.add(boardDTO);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
		}finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		
		return list;
	}
}
