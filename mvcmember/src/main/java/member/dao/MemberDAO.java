package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;
import member.bean.MemberDTO;
import member.bin.ZipcodeDTO;

public class MemberDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private Connection conn = null; 
	private PreparedStatement pstmt; 
	private ResultSet rs;

	private DataSource ds;
	
	private static MemberDAO instance = null; //초기화되는 시점 1번 

	//싱글톤 
	public static MemberDAO getInstance() { //인스턴스 - 메모리생성 
		if(instance == null) {//한번도 생성된 적 없느냐. 맨 처음이냐
			synchronized(MemberDAO.class) {//동기화처리 - 스레드중에 한개만 처리 
				instance = new MemberDAO();//생성 - 1번
			}
		}
		return instance;
	}

	//driver loading
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");//커넥션 pool의 이름
			//object형이라서 casting걸어준다. 톰캣의 경우 java:comp/env 붙여준다.
		} catch (NamingException e) {
			e.printStackTrace();
		}
}

	public void write(MemberDTO memberDTO) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
	
		
		try {	
			conn = ds.getConnection();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			
			pstmt.executeUpdate();//실행
			
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
	public MemberDTO login(String id, String pwd) {
	//public String[] login(String id, String pwd) {
		//String[] arr= new String[2];
		MemberDTO memberDTO = null;
		
		String sql ="select * from member where id=? and pwd=?";
		
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs= pstmt.executeQuery();//실행
			
			
			if(rs.next()) {
				
				
//				arr[0]= rs.getString("name");//아무것도 없으면 null값 쥐고 간다. 
//				arr[1]  = rs.getString("email1")+"@" +rs.getString("email2") ;
				
				
				 memberDTO = new MemberDTO();
				 memberDTO.setName(rs.getString("name"));
				 memberDTO.setId(rs.getString("id"));
				 memberDTO.setPwd(rs.getString("pwd"));
				 memberDTO.setGender(rs.getString("gender"));
				 memberDTO.setEmail1(rs.getString("email1"));
				 memberDTO.setEmail2(rs.getString("email2"));
				 memberDTO.setTel1(rs.getString("tel1"));
				 memberDTO.setTel2(rs.getString("tel2"));
				 memberDTO.setTel3(rs.getString("tel3"));
				 memberDTO.setZipcode(rs.getString("zipcode"));
				 memberDTO.setAddr1(rs.getString("addr1"));
				 memberDTO.setAddr2(rs.getString("addr2"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
	}
		return memberDTO;
	}

	public boolean isCheckId(String id) {
		boolean exist = false; //안써도 기본 false
		
		String sql ="select * from member where id=?";

		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs= pstmt.executeQuery();//실행
			
			if(rs.next()){//rs.next() -> 데이터가 있으면 T (아이디 사용 불가능), 데이터 없으면 F (아이디 사용 가능)
				exist = true; 
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}

	public List<ZipcodeDTO> getZipcodeList(String sido, String singungu, String roadname) {
		List<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
	
		String sql="select * from newzipcode where sido like ? and sigungu like ? and roadname like ?";
		
		try {
			conn=ds.getConnection();//connection pool 반환 - close()
			
			pstmt = conn.prepareStatement(sql);//생성
			pstmt.setString(1, "%"+sido+"%");
			pstmt.setString(2, "%"+singungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			
			rs=pstmt.executeQuery();//결과를 rs에 넣어라.
			
			while(rs.next()) {//있으면 true
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();//while 돌떄마다 생성

		        zipcodeDTO.setZipcode(rs.getString("zipcode"));
		        zipcodeDTO.setSido(rs.getString("sido"));
		        zipcodeDTO.setSigungu(rs.getString("sigungu"));
		        zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
		        zipcodeDTO.setRi(rs.getString("ri"));
		        zipcodeDTO.setRoadname(rs.getString("roadname"));
		        zipcodeDTO.setBuildingname(rs.getString("buildingname"));
		         
		        list.add(zipcodeDTO);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list=null; //이걸 써줘야지 nullpointException을 발생시킨다.
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
	
	public MemberDTO modify(){
		

		
		String sql ="select * from member";
	
		
		try {
			conn=ds.getConnection();//커넥션은 데이터 소스로부터 생긴다
			pstmt = conn. prepareStatement(sql);

			
			rs = pstmt.executeQuery();//실행
			
			while(rs.next()) { //rs.next() - 위치를 맞춘다 -T/F
				MemberDTO memberDTO = new MemberDTO();

				 memberDTO.setName(rs.getString("name"));
				 memberDTO.setId(rs.getString("id"));
				 memberDTO.setPwd(rs.getString("pwd"));
				 memberDTO.setGender(rs.getString("gender"));
				 memberDTO.setEmail1(rs.getString("email1"));
				 memberDTO.setEmail2(rs.getString("email2"));
				 memberDTO.setTel1(rs.getString("tel1"));
				 memberDTO.setTel2(rs.getString("tel2"));
				 memberDTO.setTel3(rs.getString("tel3"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return null;
		
	}
	
}

