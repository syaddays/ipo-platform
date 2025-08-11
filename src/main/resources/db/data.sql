-- Sample data for IPOs
INSERT INTO ipos (company_name, issue_price, total_shares, open_date, close_date, status, description)
VALUES 
    ('Tech Innovations Inc.', 120.50, 5000000, CURRENT_DATE + 10, CURRENT_DATE + 15, 'UPCOMING', 'A leading technology company specializing in AI solutions'),
    ('Green Energy Corp', 85.75, 3000000, CURRENT_DATE - 5, CURRENT_DATE + 5, 'ACTIVE', 'Renewable energy solutions provider with focus on solar and wind power'),
    ('HealthTech Solutions', 45.25, 2500000, CURRENT_DATE - 20, CURRENT_DATE - 10, 'CLOSED', 'Healthcare technology provider focused on patient management systems'),
    ('Global Retail Group', 65.00, 4000000, CURRENT_DATE + 20, CURRENT_DATE + 30, 'UPCOMING', 'International retail chain with presence in 30 countries'),
    ('Fintech Innovations', 95.50, 3500000, CURRENT_DATE - 2, CURRENT_DATE + 8, 'ACTIVE', 'Financial technology company providing payment solutions'); 