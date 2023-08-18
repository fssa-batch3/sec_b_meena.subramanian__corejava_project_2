CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO users (first_name, last_name, password, email)
VALUES
    ('Meena', 'Subu', '1234567890', 'meenu.doe@example.com'),
    ('Vasu', 'Subu', '9876543210', 'vasu.smith@example.com');
    SELECT * FROM users;

INSERT INTO categories (name)
VALUES 
    ('Cotton Saree');
 SELECT * FROM categories;   

INSERT INTO products (product_name, description, category_id, price)
VALUES 
    ('Elegant Sari', 'A beautiful silk sari with intricate embroidery.', 1, 199.99),
    ('Traditional Dothi', 'A comfortable and stylish cotton dothi for traditional occasions.', 1, 49.99),
    ('Designer Sari', 'A designer sari with a modern twist, perfect for special events.', 1, 299.99);

SELECT * FROM products;
