INSERT INTO category (name, description) VALUES ('Electrónica', 'Productos de electrónica');
INSERT INTO category (name, description) VALUES ('Telefonía', 'Productos de telefonía');

INSERT INTO product (name, description, price, category_id) SELECT 'Nintendo Switch OLED', 'Nitendo Switch OLED Edition', 300, id FROM category WHERE name = 'Electrónica';
INSERT INTO product (name, description, price, category_id) SELECT 'Playstation 5', 'PS5 Ultimate Edition', 400, id FROM category WHERE name = 'Electrónica';
INSERT INTO product (name, description, price, category_id) SELECT 'iPhone 15 pro MAX', 'iPhone 14 1TB', 1500, id FROM category WHERE name = 'Telefonía';


INSERT INTO product_details (color, height, weight, product_id) SELECT 'black', 30, 100, id FROM product WHERE name = 'Nintendo Switch OLED';
INSERT INTO product_details (color, height, weight, product_id) SELECT 'white', 60, 200, id FROM product WHERE name = 'Playstation 5';
INSERT INTO product_details (color, height, weight, product_id) SELECT 'black', 40, 30, id FROM product WHERE name = 'iPhone 15 pro MAX';
