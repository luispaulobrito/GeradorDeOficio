-- Criar a tabela password_reset_token
CREATE TABLE password_reset_token (
      id TEXT PRIMARY KEY UNIQUE NOT NULL,
      user_id TEXT,
      token VARCHAR(255) NOT NULL,
      expiry_date TIMESTAMP,
      utilized BOOLEAN NOT NULL,
      FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Criar um índice para o campo token para melhorar a performance de busca
CREATE INDEX idx_token ON password_reset_token (token);
