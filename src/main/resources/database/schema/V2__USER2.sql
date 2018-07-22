DROP TABLE IF EXISTS user;
CREATE TABLE user2 (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  user_name VARCHAR(40),
  name VARCHAR(20),
  age INT(3),
  balance DECIMAL(10, 2),
  PRIMARY KEY(id)
);