INSERT INTO client(name, national_id) VALUES ('jon', '12345678Z');
INSERT INTO client(name, national_id) VALUES ('pepito', '55555555A');
INSERT INTO client(name, national_id) VALUES ('perico', '55555555B');

INSERT INTO contract(reference, client_id, credit, version) VALUES ('ref-1',1, 5000, 1);
INSERT INTO contract(reference, client_id, credit, version) VALUES ('ref-2',2, 4000, 1);

INSERT INTO receipt(amount, contract_id) VALUES (3000, 1);
INSERT INTO receipt(amount, contract_id) VALUES (300, 1);
INSERT INTO receipt(amount, contract_id) VALUES (500, 1);

INSERT INTO receipt(amount, contract_id) VALUES (100, 2);
INSERT INTO receipt(amount, contract_id) VALUES (300, 2);
INSERT INTO receipt(amount, contract_id) VALUES (4000, 2);