package com.br.wes.pay.infrastructure.database;

import com.br.wes.pay.application.contracts.WalletGateway;
import com.br.wes.pay.domain.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class WalletJdbcRepository implements WalletGateway {

    private final JdbcTemplate jdbcTemplate;

    public WalletJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Wallet save(Wallet wallet) {
        String sql = "INSERT INTO WALLETS (FULL_NAME, CPF, EMAIL, PASSWORD, TYPE, BALANCE, VERSION) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, wallet.fullName());
            ps.setLong(2, wallet.cpf());
            ps.setString(3, wallet.email());
            ps.setString(4, wallet.password());
            ps.setInt(5, wallet.type());
            ps.setBigDecimal(6, wallet.balance());
            ps.setInt(7, 1); // VERSION
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        Long id = (key != null) ? key.longValue() : null;

        return new Wallet(
                id,
                wallet.fullName(),
                wallet.cpf(),
                wallet.email(),
                wallet.password(),
                wallet.type(),
                wallet.balance()
        );
    }

    public Wallet update(Wallet wallet) {
        String sql = "UPDATE WALLETS SET FULL_NAME = ?, CPF = ?, EMAIL = ?, PASSWORD = ?, TYPE = ?, BALANCE = ?, VERSION = ? WHERE ID = ?";
        jdbcTemplate.update(
                sql,
                wallet.fullName(),
                wallet.cpf(),
                wallet.email(),
                wallet.password(),
                wallet.type(),
                wallet.balance(),
                1, // valor fixo para VERSION, ajuste se necessário
                wallet.id() // ID no final
        );
        return wallet;
    }

    public Optional<Wallet> findById(Long id) {
        String sql = "SELECT * FROM WALLETS WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        });
    }

    private Wallet mapRow(ResultSet rs) throws SQLException {
        return new Wallet(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getLong("cpf"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("type"),
                rs.getBigDecimal("balance")
        );
    }

//    id, fullName, cpf, email, password, type, balance.subtract(value)
}