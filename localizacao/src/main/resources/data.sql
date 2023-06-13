create table tb_cidade(
  id_cidade bigint not null primary key,
  ds_nome varchar(50) not null,
  nu_qtdhabitantes bigint
);

insert
  into tb_cidade (id_cidade, ds_nome, nu_qtdhabitantes)
values
   (1, 'São Paulo', 111111)
  ,(2, 'Rio de Janeiro', 222222)
  ,(3, 'Fortaleza', 333333)
  ,(4, 'Salvador', 444444)
  ,(5, 'Belo Horizonte', 555555)
  ,(6, 'Porto Alegre', 666666)
  ,(7, 'Porto Velho', 777777)
  ,(8, 'Palmas', 888888)
  ,(9, 'Recife', 999999)
  ,(10, 'Natal', 100000)
  ,(11, 'Brasília', 900000)
  ;