package com.tacocloud.data.impl;

import com.tacocloud.data.TacoRepository;
import com.tacocloud.model.Ingredient;
import com.tacocloud.model.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        return null;
    }

    private long saveTacoInfo(Taco taco) {
        String SQL = "INSERT INTO Taco(name, createdAt) VALUES(?,?)";
        taco.setCreatedAt(new Date());
        PreparedStatementCreator statementCreator = new PreparedStatementCreatorFactory(
                SQL, Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(statementCreator, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        String SQL = "INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?,?)";
        jdbcTemplate.update(SQL, tacoId, ingredient.getId());
    }
}
