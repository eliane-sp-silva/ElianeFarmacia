drop database db_elianefarmacia;

use db_elianefarmacia;

select * from tb_categorias;
select * from tb_produtos;

INSERT INTO TB_CATEGORIAS(TIPO,DESCRICAO) VALUES 
("MEDICAMENTOS", "COM RECEITA E/OU TRATAMENTO EM CASA"),("VIDA SAUDÁVEL","FITOTERÁPICOS,PRE OU POS TREINO"),
("MAMÃE E BEBÊ", "FRALDAS, ACESSÓRIOS E LEITES"),("COSMÉTICOS","PRODUTOS, ACESSÓRIOS E TINTURAS"),
("HIGIENE PESSOAL","DESODORANTE, BUCAL E CUIDADOS ÍNTIMOS");

INSERT INTO TB_PRODUTOS(NOME,MARCA,INFO_ADD,PRECO,CATEGORIAS_ID) 
VALUES("INALADOR COMPRESSOR","OMRON","1 UNIDADE",208.99,1),
("PARACETAMOL","EUROFARMA","36 COMPRIMIDOS",36.74,1),
("SUPLEMENTO VITAMÍNICO","MEDLEY","100 CÁPSULAS",94.99,2),
("WHEY ZERO LACTOSE","PIRACANJUBA","250ML",89.99,2),
("FRALDA DESCARTÁVEL","PAMPERS","90 TIRAS",80.90,3),
("FÓRMULA INFANTIL","NAN","800G",55.00,3),
("CREME DE PENTEAR","SEDA","300ML",13.99,4),
("DESODORANTE","DOVE","150ML",15.90,5);