-- Cria o banco de dados se não existir
CREATE DATABASE login;

\c login

-- Habilita extensão para gerar UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Cria a tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insere o usuário padrão
INSERT INTO users (username, password, role)
VALUES ('suporte1', '$2a$12$elJkrpNdA7Md6u5RMZKbNeog2Gv.rv.39/naL4KB6lCVwPGSeCQAC', 'ADMIN')
ON CONFLICT (username) DO NOTHING;

-----------------------------------------------------------
-- 🟩 TABELA DE PRODUTOS
-----------------------------------------------------------

CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    image_url TEXT
);

-- Produtos iniciais
INSERT INTO product (name, price, image_url)
VALUES
(
  'Camiseta Azul',
  49.90,
  'https://f.fcdn.app/imgs/4a9915/www.indiewears.uy/iweauy/6f82/original/catalogo/C0300_421_1/2000-2000/camiseta-a-la-base-peso-completo-azul-royal.jpg'
),
(
  'Calça Jeans',
  129.90,
  'https://www.hangar33.com.br/dw/image/v2/BFCG_PRD/on/demandware.static/-/Sites-masterCatalog_Lunelli/default/dwb6a0f78c/large/hangar33-1.75755-008878-C2.jpg'
),
(
  'Tênis Branco',
  199.90,
  'https://static.netshoes.com.br/produtos/tenis-branco-feminino-casual-estilo-shoes/14/4X9-0039-014/4X9-0039-014_zoom1.jpg'
);
