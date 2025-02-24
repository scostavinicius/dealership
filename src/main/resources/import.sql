INSERT INTO tb_user (id, name, email, password, role) VALUES (1, 'Admin Manager', 'admin@dealership.com', '123456', 'MANAGER');
INSERT INTO tb_user (id, name, email, password, role) VALUES (2, 'John Doe', 'john.doe@example.com', 'abcdef', 'USER');
INSERT INTO tb_user (id, name, email, password, role) VALUES (3, 'Jane Smith', 'jane.smith@example.com', 'pass123', 'USER');

INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Toyota', 'Corolla', 2022, 120000.00, 'CAR');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Honda', 'Civic', 2021, 115000.00, 'CAR');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Ford', 'F-150', 2023, 250000.00, 'TRUCK');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Harley-Davidson', 'Sportster', 2020, 80000.00, 'MOTORCYCLE');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Chevrolet', 'Silverado', 2022, 230000.00, 'TRUCK');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Yamaha', 'MT-07', 2021, 45000.00, 'MOTORCYCLE');

INSERT INTO tb_dealership (name, address, manager_id) VALUES ('AutoStar', 'Av. Paulista, 1000 - São Paulo, SP', 1);
INSERT INTO tb_dealership (name, address, manager_id) VALUES ('CarMax', 'Rua XV de Novembro, 500 - Curitiba, PR', 2);
INSERT INTO tb_dealership (name, address, manager_id) VALUES ('Top Motors', 'Av. Atlântica, 250 - Rio de Janeiro, RJ', 3);
--INSERT INTO tb_dealership (name, address, manager_id) VALUES ('MotoCenter', 'Rua das Motos, 88 - Belo Horizonte, MG', 4);
