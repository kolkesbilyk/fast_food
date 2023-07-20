INSERT INTO back.config as c(name, value)
VALUES ('order', '{
    "timeout": "5 minutes"
}' :: jsonb)
ON CONFLICT (name) DO NOTHING ;