--
-- Estrutura da tabela "users"
-- Tabela com a entidade do usuário, para liberação de acesso na aplicação.
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  UNIQUE (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Usuários da API';

ALTER TABLE `users` 
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users` 
  MODIFY `id` int NOT NULL AUTO_INCREMENT;