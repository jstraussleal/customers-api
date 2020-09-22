CREATE TABLE IF NOT EXISTS s_customers.customer (
  id_customer SERIAL NOT NULL,
  nm_customer VARCHAR(255) NOT NULL,
  cd_doc_cpf VARCHAR(11) NOT NULL,
  dt_birth DATE NOT NULL,
  created_at TIMESTAMP with time zone NOT NULL,
  updated_at TIMESTAMP with time zone NULL,
  PRIMARY KEY (id_customer)
);

CREATE UNIQUE INDEX iu_customer__cd_doc_cpf ON s_customers.customer (cd_doc_cpf ASC);

