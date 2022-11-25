CREATE TABLE if not exists bmerp_store
(
    id          VARCHAR(100) COMMENT 'StoreID'   NOT NULL,
    name        VARCHAR(100) COMMENT 'StoreName' NULL,
    platform VARCHAR(100) COMMENT 'Store_platform' NULL,
    createTime datetime                         NULL,
    updateTime datetime                         NULL,
    CONSTRAINT pk_bmerp_store PRIMARY KEY (id)
);
CREATE TABLE if not exists bmerp_account
(
    id         INT          NOT NULL,
    real_name  VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    phone      VARCHAR(255) NULL,
    department VARCHAR(255) NULL,
    create_time datetime NULL,
    update_time datetime NULL,
    CONSTRAINT pk_bmerp_account PRIMARY KEY (id)
);
