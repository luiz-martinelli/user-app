CREATE TABLE usuarios (
  USU_ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  USU_NOME VARCHAR(255),
  USU_EMAIL VARCHAR(100),
  USU_SENHA VARCHAR(255),
  CONSTRAINT UK_USU_EMAIL UNIQUE (USU_EMAIL)
);