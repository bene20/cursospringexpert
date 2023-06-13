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
  ;