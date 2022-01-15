INSERT INTO USUARIOS(nome, email, senha) VALUES('Pedro', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Felipe', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Fábio', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Julia', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Victor', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Junior', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Hebert', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Matheus', 'email@email.com', '123456');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Eduardo', 'email@email.com', '123456');

INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-04','Geladeira',3000.0,1,NULL,'FECHADO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-04','Casa',250000.0,2,NULL,'ABERTO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-04','Celular',900.0,3,NULL,'FECHADO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-04','forno',2000.0,4,NULL,'ABERTO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-04','Forno',2000.0,5,NULL,'ABERTO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-05','Monitor',1450.0,3,NULL,'ABERTO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2021-12-05','TV',5250.0,1,NULL,'ABERTO');
INSERT INTO PRODUTOS(data_de_leilao, nome, valor_inicial, dono_do_produto_id, descricao, negociacao_do_produto) VALUES ('2022-01-15','mouse',170.0,1,'Mouse funcionando perfeitamente','ABERTO');

INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Pedro','2021-12-04',NULL,4000.0,1,2);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Felipe','2021-12-04',NULL,300000.0,2,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2021-12-04','Pago o frete e qualquer possivel taxa!',1400.0,3,4);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Julia','2021-12-04',NULL,2460.0,4,1);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Julia','2021-12-04',NULL,2460.0,5,5);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Pedro','2021-12-11','Pago o frete.',10000.0,1,1);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2021-12-22','Pago o frete.',8000.0,7,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2022-01-03','Pago o frete.',10000.0,7,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2022-01-03','Pago o frete.',12000.0,7,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2022-01-04','Pago o frete.',14000.0,7,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2022-01-04','Pago o frete e qualquer possivel taxa!',13400.0,7,3);
INSERT INTO PROPOSTAS(autor, data_da_proposta, mensagem, valor, produto_id, usuario_id) VALUES ('Fábio','2022-01-04','Pago o frete e qualquer possivel taxa!',19500.0,7,3);

