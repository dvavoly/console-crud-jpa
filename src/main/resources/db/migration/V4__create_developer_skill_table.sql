CREATE TABLE developer_skill (
    developer_id INT NOT NULL REFERENCES developer(id),
    skills_id INT NOT NULL REFERENCES skill(id)
)