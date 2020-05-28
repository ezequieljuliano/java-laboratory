insert into papel (pap_id, pap_nome) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', 'Editor');
insert into papel (pap_id, pap_nome) values ('9b80567b-5667-45c9-8e62-258d7d5323a9', 'Convidado');

insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('24a1c79b-681a-4104-928a-630211c3ca2d', 'USUARIO', 'VISUALIZAR', 'Usuário - Visualizar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('f5065a44-ca76-4744-9cd2-18331a59f5d8', 'USUARIO', 'INSERIR', 'Usuário - Inserir');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('47bacfb9-79ac-4a7e-8c67-4dbcfdf9d1de', 'USUARIO', 'EDITAR', 'Usuário - Editar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('474012c7-0605-4cfd-b284-70dbff5cf155', 'USUARIO', 'EXCLUIR', 'Usuário - Excluir');

insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('62f07c64-8cd6-4cc4-9629-2c382f19e3db', 'PAPEL', 'VISUALIZAR', 'Papel - Visualizar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('da3beea3-6f4c-43bb-9dbf-03e1022eac0d', 'PAPEL', 'INSERIR', 'Papel - Inserir');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('ef7ec497-cd37-4994-8cb7-430886a6b1cd', 'PAPEL', 'EDITAR', 'Papel - Editar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('c15e316a-714f-4685-8fe6-367772950de3', 'PAPEL', 'EXCLUIR', 'Papel - Excluir');

insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('1cc20688-246f-4a97-b308-131877aa9b3e', 'CATEGORIA', 'VISUALIZAR', 'Categoria - Visualizar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('f5eeb899-2f32-43c9-b0f6-47ac278de1dd', 'CATEGORIA', 'INSERIR', 'Categoria - Inserir');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('aaba3712-74af-4b9d-b86e-73535b9be852', 'CATEGORIA', 'EDITAR', 'Categoria - Editar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('79b3b441-9f38-439b-af1b-fe1b1a547aca', 'CATEGORIA', 'EXCLUIR', 'Categoria - Excuir');

insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('a07b05c2-e4ef-41e8-9880-22107f3bb779', 'BOOKMARK', 'VISUALIZAR', 'Bookmark - Visualizar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('2fcd5a16-619d-4cf7-8f7d-01d25b48ef27', 'BOOKMARK', 'INSERIR', 'Bookmark - Inserir');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('d691ff13-3852-42cd-ad45-b31f30a8b89c', 'BOOKMARK', 'EDITAR', 'Bookmark - Editar');
insert into permissao (per_id, per_recurso, per_acao, per_descricao) values ('5b6c6cd6-15cc-47f4-90e8-0305118caf9f', 'BOOKMARK', 'EXCLUIR', 'Bookmark - Excluir');

insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', '1cc20688-246f-4a97-b308-131877aa9b3e');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', 'f5eeb899-2f32-43c9-b0f6-47ac278de1dd');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', 'aaba3712-74af-4b9d-b86e-73535b9be852');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', '79b3b441-9f38-439b-af1b-fe1b1a547aca');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', 'a07b05c2-e4ef-41e8-9880-22107f3bb779');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', '2fcd5a16-619d-4cf7-8f7d-01d25b48ef27');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', 'd691ff13-3852-42cd-ad45-b31f30a8b89c');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('b8c452d1-2444-49ba-bf59-ee4bbc25d30e', '5b6c6cd6-15cc-47f4-90e8-0305118caf9f');

insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('9b80567b-5667-45c9-8e62-258d7d5323a9', '1cc20688-246f-4a97-b308-131877aa9b3e');
insert into papel_permissao (ppe_pap_id, ppe_per_id) values ('9b80567b-5667-45c9-8e62-258d7d5323a9', 'a07b05c2-e4ef-41e8-9880-22107f3bb779');

insert into usuario (usu_id, usu_username, usu_password, usu_nome, usu_email, usu_ativo, usu_administrador) values ('3b7c3df4-9fa9-4d4a-9493-b7d18191da2f', 'editor', '$2y$10$7zKF6RfQvxc/Y0tUCIvisuRn9tpOVlfAtsgp0tyq28kuFLAnLV7vO', 'Editor', 'editor@bookmark.com.br', true, false);
insert into usuario (usu_id, usu_username, usu_password, usu_nome, usu_email, usu_ativo, usu_administrador) values ('ae4cf32b-6c4e-41f6-8446-7e3fd9686ef7', 'convidado', '$2y$10$60swCl1.vs7YZ0wFVQX2Au/FDvfRg5zQfE2l6OoG9J/GlcFCv6Gd2', 'Convidado', 'convidado@bookmark.com.br', true, false);
insert into usuario (usu_id, usu_username, usu_password, usu_nome, usu_email, usu_ativo, usu_administrador) values ('548cbeb0-e4e2-4d98-ad17-7635c693298d', 'admin', '$2y$10$/mw51ZPgweO4gm7Wsx8g6OsRc5xzhCrEIbglyaeA.jKbixYGPZcEW', 'Administrador', 'admin@bookmark.com.br', true, true);

insert into usuario_papel (usp_usu_id, usp_pap_id) values ('3b7c3df4-9fa9-4d4a-9493-b7d18191da2f', 'b8c452d1-2444-49ba-bf59-ee4bbc25d30e');
insert into usuario_papel (usp_usu_id, usp_pap_id) values ('ae4cf32b-6c4e-41f6-8446-7e3fd9686ef7', '9b80567b-5667-45c9-8e62-258d7d5323a9');

insert into categoria (cat_id, cat_nome) values ('3c5bdb9d-0729-494c-a87c-19f373472a85', 'Java');
insert into categoria (cat_id, cat_nome) values ('1f7e9323-ae0d-42e9-abb2-1f2b93a5eab8', 'JavaServer Faces (JSF)');
insert into categoria (cat_id, cat_nome) values ('92b51c09-7b65-4281-bcfc-21a118b736d6', 'Spring');
insert into categoria (cat_id, cat_nome) values ('31b80a37-d6e6-47e9-b86c-4de464524118', 'Banco de Dados');

insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('5e2862e6-93bd-4faf-bab2-79b074d11084', 'OpenJDK', 'https://openjdk.java.net/', '3c5bdb9d-0729-494c-a87c-19f373472a85');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('d503a8c0-23b9-4075-b560-bfbe2cf74754', 'AdoptOpenJDK', 'https://adoptopenjdk.net/', '3c5bdb9d-0729-494c-a87c-19f373472a85');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('89264a7e-6f53-435b-88ee-79e0ab4111fb', 'Zulu Community', 'https://www.azul.com/downloads', '3c5bdb9d-0729-494c-a87c-19f373472a85');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('4a8912a4-90da-4929-9fd1-38d7d1685486', 'JoinFaces', 'http://joinfaces.org/', '1f7e9323-ae0d-42e9-abb2-1f2b93a5eab8');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('744d02f1-9ac0-4cf1-b20e-38042b8233a9', 'PrimeFaces', 'https://www.primefaces.org/', '1f7e9323-ae0d-42e9-abb2-1f2b93a5eab8');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('a5661b9f-597e-4a3f-b98f-5686a55471be', 'Mojarra', 'https://javaserverfaces.github.io/', '1f7e9323-ae0d-42e9-abb2-1f2b93a5eab8');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('db7db16d-98c1-4531-a59d-58a8cf4a4967', 'MyFaces', 'https://myfaces.apache.org/', '1f7e9323-ae0d-42e9-abb2-1f2b93a5eab8');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('3f2413e3-dbee-400c-8c59-111f47824f1e', 'Spring IO', 'https://spring.io/', '92b51c09-7b65-4281-bcfc-21a118b736d6');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('f1d1a55a-0337-4486-8c1f-4e4c206f5be6', 'Spring Initializr', 'https://start.spring.io/', '92b51c09-7b65-4281-bcfc-21a118b736d6');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('177e1060-bd18-4542-9b15-de18e72739bb', 'PostgreSQL', 'https://www.postgresql.org/', '31b80a37-d6e6-47e9-b86c-4de464524118');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('80af06dc-ff25-439a-9f08-92b8b81927bc', 'MySQL', 'https://www.mysql.com/', '31b80a37-d6e6-47e9-b86c-4de464524118');
insert into bookmark (boo_id, boo_descricao, boo_link, boo_cat_id) values ('939e77df-621f-4155-9219-77cf3de8bd4d', 'Oracle Database', 'https://www.oracle.com/br/database/', '31b80a37-d6e6-47e9-b86c-4de464524118');