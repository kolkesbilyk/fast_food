CREATE TABLE IF NOT EXISTS back.config(
    name varchar(16) NOT NULL PRIMARY KEY,
    value jsonb NOT NULL,
    description varchar(256)
);

INSERT INTO back.config(name, value)
VALUES ('auth', '{
  "api_secret_key": "123456",
  "ui_secret_key": "654321"
}' :: jsonb)
ON CONFLICT (name) DO NOTHING;