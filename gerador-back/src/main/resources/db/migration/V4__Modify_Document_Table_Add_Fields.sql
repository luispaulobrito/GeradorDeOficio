-- Alterar o nome do campo data para document_date
ALTER TABLE document RENAME COLUMN data TO document_date;

-- Adicionar o campo creation_date como NOT NULL e com o valor padrão CURRENT_TIMESTAMP
ALTER TABLE document ADD COLUMN creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Adicionar o campo user_id como chave estrangeira, garantindo que seja NOT NULL
ALTER TABLE document ADD COLUMN user_id TEXT NOT NULL REFERENCES users(id);
