CREATE TABLE IF NOT EXISTS currency_rate (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             currency_code VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    close_rate DOUBLE NOT NULL,
    previous_close_rate DOUBLE,
    UNIQUE KEY `unique_currency_date` (`currency_code`, `date`)
    );