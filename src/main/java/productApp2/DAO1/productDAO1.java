package productApp2.DAO1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import productApp2.DTO.productDTO;

public class productDAO1 {

	public boolean saveProduct(productDTO p) throws SQLException
	{
		boolean result=false;
		Connection con = connFact.getConnection();
		String sql="insert into product_info (Pname,Price,PQuantity) values (?,?,?)";
		PreparedStatement pep = con.prepareStatement(sql);
		pep.setString(1, p.getProductName());
		pep.setInt(2, p.getProductPrice());
		pep.setInt(3, p.getProductQuantity());
		int count = pep.executeUpdate();
		if(count >0)
		{
			result=true;
		}
		
		
		return result;
	}
	
	
	//this method taking id or all records
	public List<productDTO> getProducts(Integer id) throws SQLException  {
		
		
		List<productDTO> products =new ArrayList<>();
		
		StringBuffer sql=new StringBuffer("select * from product_info");
		
		Connection con = connFact.getConnection();
		if(id !=null)
		{
			sql.append(" where PID=?");
			
		}
		PreparedStatement pep = con.prepareStatement(sql.toString());	
		
		if(id!=null)
		{
			pep.setInt(1, id);
		}
		ResultSet res = pep.executeQuery();
		while(res.next())
		{
			productDTO p=new productDTO();
		    p.setProductID(res.getInt(1));
		    p.setProductName(res.getString(2));
		    p.setProductPrice(res.getInt(3));
		    p.setProductQuantity(res.getInt(4));
		    products.add(p);
		}
		
		return products;
		
		
		
		
		
	}
	
	
}
