CREATE TABLE character_schema.character_class (
                                                  id INT IDENTITY(1,1) PRIMARY KEY,
                                                  name VARCHAR(255),
                                                  description TEXT
);

CREATE TABLE character_schema.users (
                                        id INT PRIMARY KEY,
                                        role VARCHAR(255)
);

CREATE TABLE character_schema.character (
                                            id INT IDENTITY(1,1) PRIMARY KEY,
                                            name VARCHAR(255),
                                            health INT,
                                            mana INT,
                                            base_strength INT,
                                            base_agility INT,
                                            base_intelligence INT,
                                            base_faith INT,
                                            character_class_id INT,
                                            FOREIGN KEY (character_class_id) REFERENCES character_schema.character_class(id),
                                            created_by INT
);


CREATE TABLE character_schema.item (
                                       id INT IDENTITY(1,1) PRIMARY KEY,
                                       name VARCHAR(255),
                                       description TEXT,
                                       bonus_strength INT,
                                       bonus_agility INT,
                                       bonus_intelligence INT,
                                       bonus_faith INT,
                                       character_id INT
);


CREATE TABLE character_schema.character_item (
                                                 character_id INT,
                                                 item_id INT,
                                                 PRIMARY KEY (character_id, item_id),
                                                 FOREIGN KEY (character_id) REFERENCES character_schema.character(id),
                                                 FOREIGN KEY (item_id) REFERENCES character_schema.item(id)
);
