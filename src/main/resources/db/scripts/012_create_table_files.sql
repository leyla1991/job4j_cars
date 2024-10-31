create table files (
    id SERIAL PRIMARY  KEY,
    fileName VARCHAR(200),
    path VARCHAR NOT NULL unique,
    post_id int  NOT NULL REFERENCES auto_post(id)
);