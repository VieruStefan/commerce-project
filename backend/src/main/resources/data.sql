SELECT CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Bucharest';

INSERT INTO "user" (first_name, last_name, email) VALUES ('Stefan', 'Vieru', 'stefanstef62@gmail.com');
INSERT INTO "user" (first_name, last_name, email) VALUES ('Dana', 'Maritca', 'danamaritca@gmail.com');
INSERT INTO "user" (first_name, last_name, email) VALUES ('Bob', 'Bob', 'stefan.vieru@student.tuiasi.ro');

-- Assuming user_id corresponds to the IDs of users inserted above
INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Chitară', 'Chitară in stare foarte bună', 500, 1, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Cameră foto', 'Cameră foto profesională cu accesorii', 800, 2, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Raft pentru carți', 'Raft pentru carți ajustabil', 200, 3, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
