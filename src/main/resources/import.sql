INSERT INTO category (name, description, created_by, modified_by, created_date, modified_date) VALUES ('Electrónica', 'Productos de electrónica', 'admin', 'admin', now(), now());
INSERT INTO category (name, description, created_by, modified_by, created_date, modified_date) VALUES ('Telefonía', 'Productos de telefonía',  'admin', 'admin', now(), now());

INSERT INTO product (name, code, description, price, category_id, created_by, modified_by, created_date, modified_date) SELECT 'Nintendo Switch OLED', 1000, 'Nintendo Switch OLED Edition', 300, id, 'admin', 'admin', now(), now() FROM category WHERE name = 'Electrónica';
INSERT INTO product (name, code, description, price, category_id, created_by, modified_by, created_date, modified_date) SELECT 'Playstation 5', 1001, 'PS5 Ultimate Edition', 400, id, 'admin', 'admin', now(), now() FROM category WHERE name = 'Electrónica';
INSERT INTO product (name, code, description, price, category_id, created_by, modified_by, created_date, modified_date) SELECT 'iPhone 15 pro MAX', 1002, 'iPhone 14 1TB', 1500, id, 'admin', 'admin', now(), now() FROM category WHERE name = 'Telefonía';


INSERT INTO product_details (color, height, weight, product_id) SELECT 'black', 30, 100, id FROM product WHERE name = 'Nintendo Switch OLED';
INSERT INTO product_details (color, height, weight, product_id) SELECT 'white', 60, 200, id FROM product WHERE name = 'Playstation 5';
INSERT INTO product_details (color, height, weight, product_id) SELECT 'black', 40, 30, id FROM product WHERE name = 'iPhone 15 pro MAX';
