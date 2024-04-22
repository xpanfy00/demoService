CREATE TABLE IF NOT EXISTS transaction (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           account_from BIGINT NOT NULL,
                                           account_to BIGINT NOT NULL,
                                           currency_shortname VARCHAR(255) NOT NULL,
    sum DOUBLE NOT NULL,
    expense_category VARCHAR(255),
    datetime DATETIME NOT NULL,
    FOREIGN KEY (account_from) REFERENCES transaction_limit(account),
    CONSTRAINT fk_transaction_account_from FOREIGN KEY (account_from) REFERENCES transaction_limit(account),
    CONSTRAINT fk_transaction_account_to FOREIGN KEY (account_to) REFERENCES transaction_limit(account)
    );