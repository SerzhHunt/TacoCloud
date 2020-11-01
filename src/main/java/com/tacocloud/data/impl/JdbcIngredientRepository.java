package com.tacocloud.data.impl;

import com.tacocloud.data.IngredientRepository;
import com.tacocloud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        String SQL = "SELECT id, name, type FROM Ingredient";
        return jdbcTemplate.query(SQL, this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        String SQL = "SELECT id, name, tipe FROM Ingredient WHERE id=?";
        return jdbcTemplate.queryForObject(SQL, this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String SQL = "INSERT INTO Ingredient (id, name, type) values(?,?,?)";
        ingredient.getId();
        ingredient.getName();
        ingredient.getType().toString();
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }
}
