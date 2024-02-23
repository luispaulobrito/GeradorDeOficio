CREATE TABLE document (
      id TEXT PRIMARY KEY UNIQUE NOT NULL,
      type TEXT NOT NULL,
      data DATE NOT NULL,
      sender TEXT NOT NULL,
      sender_title TEXT NOT NULL,
      recipient TEXT NOT NULL,
      recipient_title TEXT NOT NULL,
      subject TEXT NOT NULL,
      text TEXT NOT NULL,
      document_number INT NOT NULL,
      document_year INT NOT NULL
);
