package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO extends DAO {
	
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Usuario usuario) throws Exception {
		boolean status = false;
		try {  
			String sql = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, usuario.getCodigo());
			st.setString(2, usuario.getLogin());
			st.setString(3, usuario.getSenha());
			st.setString(4, "" + usuario.getSexo());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Usuario get(int codigo) {
		Usuario usuario = null;
		
		try {
			String sql = "SELECT * FROM produto WHERE id= ?";
			PreparedStatement st = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.setInt(1, codigo);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Usuario> getOrderByLogin() {
		return get("login");		
	}
	
	
	public List<Usuario> getOrderBySexo() {
		return get("sexo");		
	}
	
	
	public List<Usuario> get(String orderBy) {	
	
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY ?"));
			PreparedStatement st = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.setString(1, orderBy);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();	           
	        while(rs.next()) {	            	
	        	Usuario u = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}


	public List<Usuario> getSexoMasculino() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			String sql = "SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'";
			PreparedStatement st = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();	           
	        while(rs.next()) {	            	
	        	Usuario u = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
			PreparedStatement st = conexao.prepareStatement(sql);
			System.out.println(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			String sql = "DELETE FROM usuario WHERE codigo = ?" ;
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setInt(1, codigo);
			System.out.println(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean autenticar(String login, String senha) {
		boolean resp = false;
		
		try {
			String sql = "SELECT * FROM usuario WHERE login LIKE ? AND senha LIKE ?";
			PreparedStatement st = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	
}