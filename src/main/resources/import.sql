INSERT INTO tb_user (name, email, password, role) VALUES ('Carlos Silva', 'carlos@email.com', '123456', 'MANAGER');
INSERT INTO tb_user (name, email, password, role) VALUES ('Ana Souza', 'ana@email.com', '123456', 'MANAGER');
INSERT INTO tb_user (name, email, password, role) VALUES ('Marcos Lima', 'marcos@email.com', '123456', 'MANAGER');
INSERT INTO tb_user (name, email, password, role) VALUES ('Fernanda Rocha', 'fernanda@email.com', '123456', 'MANAGER');
INSERT INTO tb_user (name, email, password, role) VALUES ('Ricardo Pereira', 'ricardo@email.com', '123456', 'USER');
INSERT INTO tb_user (name, email, password, role) VALUES ('Felipe Macedo', 'felipe@email.com', '123456', 'USER');

INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Toyota', 'Corolla', 2022, 120000.00, 'CAR');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Honda', 'Civic', 2021, 115000.00, 'CAR');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Ford', 'F-150', 2023, 250000.00, 'TRUCK');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Harley-Davidson', 'Sportster', 2020, 80000.00, 'MOTORCYCLE');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Chevrolet', 'Silverado', 2022, 230000.00, 'TRUCK');
INSERT INTO tb_vehicle (brand, model, vehicle_year, price, type) VALUES ('Yamaha', 'MT-07', 2021, 45000.00, 'MOTORCYCLE');

INSERT INTO tb_dealership (name, address, manager_id) VALUES ('AutoStar', 'Av. Paulista, 1000 - São Paulo, SP', 1);
INSERT INTO tb_dealership (name, address, manager_id) VALUES ('CarMax', 'Rua XV de Novembro, 500 - Curitiba, PR', 2);
INSERT INTO tb_dealership (name, address, manager_id) VALUES ('MotoCenter', 'Rua das Motos, 88 - Belo Horizonte, MG', 3);
INSERT INTO tb_dealership (name, address, manager_id) VALUES ('Top Motors', 'Av. Atlântica, 250 - Rio de Janeiro, RJ', 4);

INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (1, 3, 2);
INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (1, 5, 3);
INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (2, 1, 2);
INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (2, 2, 3);
INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (3, 4, 2);
INSERT INTO tb_inventory (dealership_id, vehicle_id, quantity) VALUES (3, 6, 3);

INSERT INTO tb_sale (customer_id, dealership_id, vehicle_id, sale_date) VALUES (1, 1, 5, '2025-03-27');
INSERT INTO tb_sale (customer_id, dealership_id, vehicle_id, sale_date) VALUES (2, 2, 1, '2025-03-28');
INSERT INTO tb_sale (customer_id, dealership_id, vehicle_id, sale_date) VALUES (3, 3, 4, '2025-03-29');
