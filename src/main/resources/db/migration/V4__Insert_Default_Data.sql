-- V4: Insert default data

-- Default roles
INSERT INTO roles (name, description) VALUES
('ADMIN', 'System Administrator with full access'),
('MANAGER', 'Business Manager with management access'),
('OPERATOR', 'Operator with basic operational access');

-- Default admin user (password: admin123)
INSERT INTO users (username, password, full_name, email, phone, active, created_at, updated_at)
VALUES (
    'admin',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'System Administrator',
    'admin@waheedfruittrader.com',
    '+923001234567',
    TRUE,
    NOW(),
    NOW()
);

-- Assign ADMIN role to admin user
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.username = 'admin' AND r.name = 'ADMIN';

-- Default manager user (password: manager123)
INSERT INTO users (username, password, full_name, email, phone, active, created_at, updated_at)
VALUES (
    'manager',
    '$2a$10$X/uZMTq4dw4WxwKGZ2AcmO0PJy.kF2i4P5GZQmEJHOVOVVOdAYlrK',
    'Business Manager',
    'manager@waheedfruittrader.com',
    '+923001234568',
    TRUE,
    NOW(),
    NOW()
);

-- Assign MANAGER role to manager user
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r
WHERE u.username = 'manager' AND r.name = 'MANAGER';

-- Default inventory location
INSERT INTO inventory_locations (name, address, description, active, created_at)
VALUES ('Main Warehouse', 'Waheed Market, Main Bazaar', 'Primary storage location', TRUE, NOW());

-- Sample fruit data
INSERT INTO fruits (name, type, category, unit, purchase_price, selling_price, description, active, created_at, updated_at)
VALUES
('Mango', 'Tropical', 'Premium', 'KG', 150.00, 200.00, 'Fresh Sindhi Mango', TRUE, NOW(), NOW()),
('Apple', 'Temperate', 'Premium', 'KG', 180.00, 250.00, 'Fresh Imported Apple', TRUE, NOW(), NOW()),
('Banana', 'Tropical', 'Regular', 'Dozen', 80.00, 120.00, 'Fresh Local Banana', TRUE, NOW(), NOW()),
('Orange', 'Citrus', 'Regular', 'KG', 100.00, 150.00, 'Fresh Pakistani Orange', TRUE, NOW(), NOW()),
('Grapes', 'Temperate', 'Premium', 'KG', 200.00, 280.00, 'Fresh Imported Grapes', TRUE, NOW(), NOW()),
('Watermelon', 'Tropical', 'Regular', 'KG', 40.00, 60.00, 'Fresh Local Watermelon', TRUE, NOW(), NOW()),
('Strawberry', 'Berry', 'Premium', 'KG', 350.00, 500.00, 'Fresh Strawberry', TRUE, NOW(), NOW()),
('Pomegranate', 'Tropical', 'Premium', 'KG', 250.00, 350.00, 'Fresh Local Pomegranate', TRUE, NOW(), NOW());
