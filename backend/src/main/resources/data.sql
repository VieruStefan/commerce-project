INSERT INTO "user" (first_name, last_name, email) VALUES ('Stefan', 'Vieru', 'stefan@gmail.com');
INSERT INTO "user" (first_name, last_name, email) VALUES ('Dana', 'Maritca', 'dana@gmail.com');
INSERT INTO "user" (first_name, last_name, email) VALUES ('Bob', 'Johnson', 'bob@gmail.com');

-- Assuming user_id corresponds to the IDs of users inserted above
INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Guitar', 'Vintage guitar in excellent condition', 500, 1, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Camera', 'Professional camera with accessories', 800, 2, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO listing (title, description, price, user_id, image_url, pub_date, updated_date)
VALUES ('Bookshelf', 'Solid wood bookshelf with adjustable shelves', 200, 3, 'https://oferte-directe-bucket.s3.amazonaws.com/listing_3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
