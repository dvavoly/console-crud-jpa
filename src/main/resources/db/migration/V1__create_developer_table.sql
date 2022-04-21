CREATE TABLE developer (
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    status INT,
    specialty_id INT
)