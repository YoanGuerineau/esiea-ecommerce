INSERT INTO products (name, description, cost) VALUES  
  ('First product', 'This is the first product', 500),
  ('Second product', 'This is the second product', 250),
  ('Third product', 'This is the third product', 1550);

INSERT INTO categories (name) VALUES
  ('smartphone'),
  ('tablet'),
  ('laptop'),
  ('apple');

INSERT INTO category_product (category_id, product_id) VALUES
  (1,1),
  (2,2),
  (3,3),
  (4,1),
  (4,2),
  (4,3);

INSERT INTO users (username, password) VALUES
  ('user', '$2y$10$Nst16RHUD/YnnSsR0kv8vuZTr1x.RMFj02Zufn6mk6Y8sP0FhNGO2');