INSERT INTO back.employer(id, first_name, last_name, login, password, email, author)
VALUES (2, 'default', 'default', 'demo',
        '9e7c0facd1a94fa9fe8cdf817bfe6644eb66a786e20a6346effa04ec4819c239cd959f12c8ea5feb8893f4be3dde51dc382096b9819c425d06777ef65fa1c02b',
        'default', 1)
ON CONFLICT DO NOTHING ;

INSERT INTO back.dir_roles(name, description)
VALUES ('DEFAULT', 'звичайний користувач');

INSERT INTO back.employer_roles(role_id, employer_id)
VALUES (5, 2);