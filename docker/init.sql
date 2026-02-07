-- =========================================
-- EXTENSÕES
-- =========================================
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =========================================
-- USUÁRIOS
-- =========================================
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

INSERT INTO users (username, password, role)
VALUES (
  'suporte1',
  '$2a$12$elJkrpNdA7Md6u5RMZKbNeog2Gv.rv.39/naL4KB6lCVwPGSeCQAC',
  'ADMIN'
)
ON CONFLICT (username) DO NOTHING;

-- =========================================
-- CATEGORIA
-- =========================================
CREATE TABLE IF NOT EXISTS categoria (
    id SERIAL PRIMARY KEY,
    nome_categoria VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO categoria (nome_categoria)
VALUES
('Roupas'),
('Calçados'),
('Alimentos')
ON CONFLICT (nome_categoria) DO NOTHING;

-- =========================================
-- PREÇOS
-- =========================================
CREATE TABLE IF NOT EXISTS precos (
    id BIGSERIAL PRIMARY KEY,
    valor NUMERIC(10,2) NOT NULL,
    desconto NUMERIC(10,2)
);

INSERT INTO precos (valor, desconto)
VALUES
(49.90, NULL),
(129.90, 20.00),
(199.90, NULL);

-- =========================================
-- PRODUTO
-- =========================================
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url TEXT,
    categoria_id INTEGER NOT NULL,
    preco_id BIGINT NOT NULL,
    tem_em_estoque BOOLEAN NOT NULL DEFAULT true,

    CONSTRAINT fk_product_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria(id),

    CONSTRAINT fk_product_preco
        FOREIGN KEY (preco_id)
        REFERENCES precos(id)
);

INSERT INTO product (name, image_url, categoria_id, preco_id, tem_em_estoque)
VALUES
(
  'Camiseta Azul',
  'https://f.fcdn.app/imgs/4a9915/www.indiewears.uy/iweauy/6f82/original/catalogo/C0300_421_1/2000-2000/camiseta-a-la-base-peso-completo-azul-royal.jpg',
  1,
  1,
  true
),
(
  'Calça Jeans',
  'https://www.hangar33.com.br/dw/image/v2/BFCG_PRD/on/demandware.static/-/Sites-masterCatalog_Lunelli/default/dwb6a0f78c/large/hangar33-1.75755-008878-C2.jpg',
  1,
  2,
  false
),
(
  'Tênis Branco',
  'https://static.netshoes.com.br/produtos/tenis-branco-feminino-casual-estilo-shoes/14/4X9-0039-014/4X9-0039-014_zoom1.jpg',
  2,
  3,
  true
);
