package Third;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.*;

public class Database {
	
	private static String tableName = "third";
	private int maxPages = 0;
	private int maxRows = 0;
	private List<UnderGraduate> underGraduateList;
	private Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Database() {
		
		conn = JdbcUtils.getConnection();
		underGraduateList = new ArrayList<>();
	}
	
	public List<Integer> getMaxPages(String query, int numPerPage) {
		
		List<Integer> ans = new ArrayList<Integer>();
		
		try {
			if(query == null || "".equals(query))
				return null;
			
			String sql = "SELECT COUNT(*) total FROM " + tableName + 
					" WHERE id LIKE '%" + query + "%' OR name LIKE '%" + query + 
					"%' OR tel LIKE '%" + query + "%' OR qq LIKE '%" + query + "%' OR mail LIKE '%" + query + "%' ";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
				maxRows = rs.getInt(1);
			
			System.out.println("maxRows = " + maxRows);
	
			maxPages = (int)Math.ceil((double)maxRows/numPerPage);
			
			//rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ans.add(maxPages);
		ans.add(maxRows);
		return ans;
	}
	
	
	public List<UnderGraduate> searchOf(String query, int currentPage, int numPerPage) {
		
		String sql = "SELECT * FROM " + tableName + " WHERE id LIKE '%" + query + "%' OR name LIKE '%" + query + 
				"%' OR tel LIKE '%" + query + "%' OR qq LIKE '%" + query + "%' OR mail LIKE '%" + query + "%' " + 
				"LIMIT ?,? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (currentPage - 1) * numPerPage);
			ps.setInt(2, numPerPage);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String tel = rs.getString(3);
				String qq = rs.getString(4);
				String mail = rs.getString(5);
				
				UnderGraduate underGraduate = new UnderGraduate(id, name, tel, qq, mail);
				underGraduateList.add(underGraduate);
				
			}
			
			//rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return underGraduateList;
		
	} 
	
	public boolean add(UnderGraduate underGraduate) {
		
		int result = 0;
		String sql="insert into " + tableName + " values(?,?,?,?,?)";
		
		try {	
			ps=conn.prepareStatement(sql);
			ps.setString(1, underGraduate.getID());
			ps.setString(2, underGraduate.getName());
			ps.setString(3, underGraduate.getTel());
			ps.setString(4, underGraduate.getQQ());
			ps.setString(5, underGraduate.getMail());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result != 0) 
			return true;
		else
			return false;

	}
	
	public boolean delete(String id) {
		
		int result = 0;
		String sql="delete from " + tableName + " where id = ?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			//sql = sql.replace("'","");
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result != 0) 
			return true;
		else
			return false;
		
	}
	
	public void release () {
		JdbcUtils.release(conn, ps, rs);
	}

}
