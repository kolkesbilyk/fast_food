CREATE TABLE IF NOT EXISTS back.employer_roles(
    id              SERIAL PRIMARY KEY,
    role_id         INTEGER REFERENCES back.dir_roles,
    employer_id     INTEGER REFERENCES back.employer
)