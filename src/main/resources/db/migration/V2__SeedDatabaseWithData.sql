-- Insert data into users table
INSERT INTO character_schema.users (id, role)
VALUES (1, 'ROLE_GameMaster'),
       (2, 'ROLE_User');
-- Insert data into character_class table
INSERT INTO character_schema.character_class (name, description)
VALUES ('Warrior', 'Strong and brave'),
       ('Mage', 'Wise and powerful'),
       ('Rogue', 'Stealthy and agile'),
       ('Paladin', 'Holy warrior'),
       ('Ranger', 'Master of the wilderness'),
       ('Warlock', 'User of dark magic');
-- Insert data into character table
INSERT INTO character_schema.character (name, health, mana, base_strength, base_agility, base_intelligence, base_faith, character_class_id, created_by)
VALUES ('Aethelred', 100, 50, 10, 5, 5, 5, 1, 1),
       ('Balgar', 80, 70, 5, 5, 10, 5, 2, 2),
       ('Caelum', 90, 60, 5, 10, 5, 5, 3, 3);

-- Insert data into item table
INSERT INTO character_schema.item (name, description, bonus_strength, bonus_agility, bonus_intelligence, bonus_faith)
VALUES ('Sword', 'A sharp blade', 5, 0, 0, 0),
       ('Staff', 'A magical staff', 0, 0, 5, 0),
       ('Dagger', 'A stealthy weapon', 0, 5, 0, 0),
       ('Shield', 'A sturdy shield', 3, 0, 0, 2),
       ('Bow', 'A long-range weapon', 0, 3, 0, 0),
       ('Grimoire', 'A book of dark spells', 0, 0, 3, 0);

-- Insert data into character_item table
INSERT INTO character_schema.character_item (character_id, item_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);