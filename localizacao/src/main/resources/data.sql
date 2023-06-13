create table tb_cidade(
  id_cidade bigint not null primary key,
  ds_nome varchar(50) not null,
  nu_qtdhabitantes bigint
);

insert
  into tb_cidade (id_cidade, ds_nome, nu_qtdhabitantes)
values
   (1, 'São Paulo', 12396372)
  ,(2, 'Rio de Janeiro', 10000000)
  ,(3, 'Fortaleza', 2326578)
  ,(4, 'Salvador', 135264)
  ,(5, 'Belo Horizonte', 235659)
  ,(6, 'Porto Alegre', 69795323)
  ,(7, 'Porto Velho', 3665523)
  ,(8, 'Palmas', 111111)
  ,(9, 'Recife', 2222222)
  ,(10, 'Natal', 333333333)
  ,(11, 'Brasília', 444444444)
  ;