package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("SELECT ID, NAME, TYPE FROM INGREDIENT", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("SELECT ID, NAME, TYPE FROM INGREDIENT", this::mapRowToIngredient);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
			 throws SQLException {
			 return new Ingredient(
			 rs.getString("id"),
			 rs.getString("name"),
			 Ingredient.Type.valueOf(rs.getString("type")));
			}

}
