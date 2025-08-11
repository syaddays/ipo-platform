-- Drop table if exists
DROP TABLE IF EXISTS ipos;

-- Create IPO table
CREATE TABLE ipos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    issue_price DECIMAL(10, 2) NOT NULL,
    total_shares BIGINT NOT NULL,
    open_date DATE NOT NULL,
    close_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci; 