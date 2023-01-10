CREATE TABLE Person (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name varchar(100) NOT NULL,
    username varchar(255) NOT NULL UNIQUE,
    password varchar(100),
    role varchar(100),
    phone text NOT NULL,
    email varchar(255) NOT NULL,
    date_of_birth DATE NOT NULL
);

CREATE TABLE Product (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    brand varchar(100) NOT NULL,
    product_name varchar(255) NOT NULL,
    year_of_release int NOT NULL,
    price int NOT NULL,
    path_to_img varchar(255)
);

CREATE TABLE Orders (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at timestamp,
    person_id int REFERENCES Person(id) ON DELETE CASCADE,
    product_id int REFERENCES Product(id) ON DELETE CASCADE
);

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Roadster', 2008, 200000, 'gal_img/roads_1.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Model S', 2012, 41799, 'gal_img/model_S_1.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Model X', 2015, 72144, 'gal_img/model_X_4.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Model 3', 2016, 40399, 'gal_img/model_3_1.jpeg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Model Y', 2020, 49990, 'gal_img/model_Y_1.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Semi', 2019, 180000, 'gal_img/semi_1.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Tesla', 'Cybertruck', 2021, 39900, 'gal_img/cyber_2.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('Ford', 'Mustang Mach-E', 2020, 69999, 'gal_img/mustang_mach_e1.jpg');

INSERT INTO product(brand, product_name, year_of_release, price, path_to_img)
VALUES ('BMW', 'iX', 2021, 85000, 'gal_img/bmw1.jpg');