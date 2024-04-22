CREATE TABLE IF NOT EXISTS transaction_limit (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 limit_sum DOUBLE NOT NULL,
                                                 limit_datetime DATETIME NOT NULL,
                                                 limit_currency_shortname VARCHAR(255) NOT NULL,
    limit_exceeded BOOLEAN NOT NULL,
    account BIGINT NOT NULL,
    limit_left DOUBLE NOT NULL,
    UNIQUE KEY `unique_account` (`account`),
    FOREIGN KEY (account) REFERENCES transaction_limit(account)
    );