package com.br.wes.pay.infrastructure.database;

import com.br.wes.pay.application.contracts.TransactionGateway;
import com.br.wes.pay.domain.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionJdbcRepository implements TransactionGateway {

    private final JdbcTemplate jdbcTemplate;

    public TransactionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO TRANSACTIONS (payer, payee, amount, created_at) VALUES (?, ?, ?, ?)";
        var keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, transaction.payer());
            ps.setLong(2, transaction.payee());
            ps.setBigDecimal(3, transaction.amount());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(transaction.createdAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        Long id = (key != null) ? key.longValue() : null;

        return new Transaction(
                id,
                transaction.payer(),
                transaction.payee(),
                transaction.amount(),
                transaction.createdAt()
        );
    }

    public Optional<Transaction> findById(Integer id) {
        String sql = "SELECT * FROM TRANSACTIONS WHERE id = ?";
        return jdbcTemplate.query(con -> {
            var ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        }, rs -> {
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        });
    }

    public List<Transaction> listTransactions(String payer, String payee) {
        String sql = "SELECT * FROM TRANSACTIONS WHERE payer = ? AND payee = ?";
        return jdbcTemplate.query(con -> {
            var ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(payer));
            ps.setInt(2, Integer.parseInt(payee));
            return ps;
        }, (rs, rowNum) -> mapRow(rs));
    }

    private Transaction mapRow(ResultSet rs) throws SQLException {

        return new Transaction(
                rs.getLong("id"),
                rs.getLong("payer"),
                rs.getLong("payee"),
                rs.getBigDecimal("amount"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}