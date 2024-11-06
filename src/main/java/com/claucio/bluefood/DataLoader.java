package com.claucio.bluefood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            // Executa o arquivo data.sql manualmente
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
            System.out.println("Arquivo data.sql executado com sucesso");

        }
    }
}
