-- =========================================
-- EXTENSÃO (UUID)
-- =========================================
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- =========================================
-- USUÁRIOS (ADMIN)
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
    id BIGSERIAL PRIMARY KEY,
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
(199.90, NULL)
ON CONFLICT DO NOTHING;

-- =========================================
-- PRODUTOS
-- =========================================
CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    variacao VARCHAR(100) NOT NULL,
    image_url TEXT,
    categoria_id BIGINT NOT NULL,
    preco_id BIGINT NOT NULL,
    quantidade_em_estoque INTEGER NOT NULL CHECK (quantidade_em_estoque >= 0),

    CONSTRAINT fk_product_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES categoria(id),

    CONSTRAINT fk_product_preco
        FOREIGN KEY (preco_id)
        REFERENCES precos(id)
);

INSERT INTO product (
    name,
    variacao,
    image_url,
    categoria_id,
    preco_id,
    quantidade_em_estoque
)
VALUES
(
  'Camiseta Azul',
  'P',
  'https://f.fcdn.app/imgs/4a9915/www.indiewears.uy/iweauy/6f82/original/catalogo/C0300_421_1/2000-2000/camiseta-a-la-base-peso-completo-azul-royal.jpg',
  1,
  1,
  15
),
(
  'Camiseta Azul',
  'M',
  'https://f.fcdn.app/imgs/4a9915/www.indiewears.uy/iweauy/6f82/original/catalogo/C0300_421_1/2000-2000/camiseta-a-la-base-peso-completo-azul-royal.jpg',
  1,
  1,
  8
),
(
  'Camiseta Azul',
  'G',
  'https://f.fcdn.app/imgs/4a9915/www.indiewears.uy/iweauy/6f82/original/catalogo/C0300_421_1/2000-2000/camiseta-a-la-base-peso-completo-azul-royal.jpg',
  1,
  1,
  3
),
(
  'Tênis Branco',
  '42',
  'https://static.netshoes.com.br/produtos/tenis-branco-feminino-casual-estilo-shoes/14/4X9-0039-014/4X9-0039-014_zoom1.jpg',
  2,
  3,
  5
)
ON CONFLICT DO NOTHING;

-- =========================================
-- PEDIDOS
-- =========================================
CREATE TABLE IF NOT EXISTS pedido (
    id BIGSERIAL PRIMARY KEY,
    criado TIMESTAMP NOT NULL,
    cliente VARCHAR(255) NOT NULL,
    telefone VARCHAR(30),
    endereco TEXT NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    complemento TEXT,
    forma_de_pagamento VARCHAR(50) NOT NULL
);

-- =========================================
-- PEDIDO_ITEM
-- =========================================
CREATE TABLE IF NOT EXISTS pedido_item (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,

    CONSTRAINT fk_pedido_item_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES pedido(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_pedido_item_produto
        FOREIGN KEY (produto_id)
        REFERENCES product(id)
);
