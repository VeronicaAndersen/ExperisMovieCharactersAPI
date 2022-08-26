-- Insert data into movie_character
INSERT INTO movie_character (character_name, character_alias, character_gender, character_picture)
    VALUES ('Harry Potter', 'Daniel Jacob Radcliffe', 'male', 'https://i2-prod.birminghammail.co.uk/interactives/article14974605.ece/ALTERNATES/s810/0_Wizarding-World-of-Harry-Potter.jpg');

INSERT INTO movie_character (character_name, character_alias, character_gender, character_picture)
    VALUES ('Hermione Granger', 'Emma Watson', 'female', 'https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Hermione_Granger_poster.jpg/220px-Hermione_Granger_poster.jpg');

INSERT INTO movie_character (character_name, character_alias, character_gender, character_picture)
    VALUES ('Ron Weasley', 'Rupert Grint', 'male', 'https://upload.wikimedia.org/wikipedia/en/thumb/5/5e/Ron_Weasley_poster.jpg/220px-Ron_Weasley_poster.jpg');

INSERT INTO movie_character (character_name, character_alias, character_gender, character_picture)
    VALUES ('Black Widow', 'Natasha Romanova', 'female', 'https://upload.wikimedia.org/wikipedia/en/thumb/f/f6/Scarlett_Johansson_as_Black_Widow.jpg/220px-Scarlett_Johansson_as_Black_Widow.jpg');

INSERT INTO movie_character (character_name, character_alias, character_gender, character_picture)
    VALUES ('Flo', 'Jenifer Lewis', 'female', 'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F6%2F2016%2F06%2Fflo-jenifer-lewis.jpg&w=200&c=sc&poi=face&q=60');


-- Insert data into movie
INSERT INTO movie (movie_title, movie_genre, movie_release_year, movie_director, movie_picture, movie_trailer)
    VALUES ('Harry Potter and the Sorcerer´s Stone', 'Fantasy, Adventure', 2001, 'Chris Columbus', 'https://www.imdb.com/title/tt0241527/mediaviewer/rm3145094400/?ref_=tt_ov_i', 'https://www.imdb.com/video/vi3115057433?playlistId=tt0241527&ref_=tt_ov_vi');

INSERT INTO movie (movie_title, movie_genre, movie_release_year, movie_director, movie_picture, movie_trailer)
    VALUES ('Harry Potter and the Chamber of Secrets', 'Fantasy, Adventure', 2002, 'Chris Columbus', 'https://www.imdb.com/title/tt0295297/mediaviewer/rm3790637825/?ref_=tt_ov_i', 'https://www.imdb.com/video/vi1705771289?playlistId=tt0295297&ref_=tt_pr_ov_vi');

INSERT INTO movie (movie_title, movie_genre, movie_release_year, movie_director, movie_picture, movie_trailer)
    VALUES ('Harry Potter and the Prisoner of Azkaban', 'Fantasy, Adventure', 2004, 'Chris Columbus', 'https://www.imdb.com/title/tt0304141/mediaviewer/rm3241184256/?ref_=tt_ov_i', 'https://www.imdb.com/video/vi2007761177?playlistId=tt0304141&ref_=tt_pr_ov_vi');

INSERT INTO movie (movie_title, movie_genre, movie_release_year, movie_director, movie_picture, movie_trailer)
    VALUES ('Cars 2', 'Animation, Adventure, Comedy', '2011', 'John Lasseter, Bradford Lewis', 'https://www.imdb.com/title/tt1216475/mediaviewer/rm1951513344/?ref_=tt_ov_i', 'https://www.imdb.com/video/vi1822465049?playlistId=tt1216475&ref_=tt_ov_vi');

INSERT INTO movie (movie_title, movie_genre, movie_release_year, movie_director, movie_picture, movie_trailer)
    VALUES ('Black Widow', 'Action, Adventure, Sci-Fi', '2021', 'Cate Shortland', 'https://www.imdb.com/title/tt3480822/mediaviewer/rm676068097/?ref_=tt_ov_i', 'https://www.imdb.com/video/vi645185561?playlistId=tt3480822&ref_=tt_ov_vi');



-- Insert data into franchise
INSERT INTO franchise (franchise_name, franchise_description)
    VALUES ('Warner Bros', 'Warner Bros. Entertainment Inc. is an American entertainment conglomerate headquartered at the Warner Bros. Studios complex in Burbank, California, and a division of Warner Bros. Discovery. Founded in 1923 by four brothers, Harry, Albert, Sam, and Jack Warner, the company established itself as a leader in the American film industry before diversifying into animation, television, and video games and is one of the "Big Five" major American film studios, as well as a member of the Motion Picture Association (MPA).');

INSERT INTO franchise (franchise_name, franchise_description)
    VALUES ('Pixar', 'Pixar Animation Studios, commonly known as just Pixar, is an American computer animation studio known for its critically and commercially successful computer animated feature films. It is based in Emeryville, California, United States, and is a subsidiary of Walt Disney Studios, which is another studio owned by The Walt Disney Company.');

INSERT INTO franchise (franchise_name, franchise_description)
    VALUES ('Marvel Studios', 'Marvel Studios, LLC (originally known as Marvel Films from 1993 to 1996) is an American film and television production company that is a subsidiary of Walt Disney Studios, a division of the Walt Disney Company. Marvel Studios produces the Marvel Cinematic Universe films, based on characters that appear in Marvel Comics publications.');


-- Insert data to manyToMany relation for characters in movies
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (1, 1); -- Harry
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (2, 1);
-- INSERT INTO movie_characters (movie_id, character_id)VALUES (3, 1);
--
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (1, 2); -- Hermoine
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (2, 2);
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (3, 2);
--
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (1, 3); -- Ron
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (2, 3);
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (3, 3);
--
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (4, 5); -- Flo
--
-- INSERT INTO movie_characters (movie_id, character_id) VALUES (5, 4); -- Black Widow


-- Update franchise_id in movie
-- UPDATE movie SET franchise_id = 1 WHERE movie_title LIKE '%Harry Potter%';
-- UPDATE movie SET franchise_id = 2 WHERE movie_id = 4;
-- UPDATE movie SET franchise_id = 3 WHERE movie_id = 5;



-- UPDATE characterMovie SET movieId = 5 WHERE characterId = 1 AND movieId = 1;

-- Get all movies in a franchise
-- SELECT movie.movie_title, franchise.franchise_name FROM movie
--     JOIN franchise ON movie.franchise_id = franchise.franchise_id
--     WHERE franchise.franchise_id = 1;


-- Get all characters in a movie
-- SELECT DISTINCT movie_character.character_name FROM movie_character
--     JOIN movie_characters ON movie_characters.character_id = movie_character.character_id
--     JOIN movie ON movie.movie_id = movie_characters.movie_id
--     WHERE movie.movie_id = 1;


-- Get all characters in a franchise
-- SELECT DISTINCT movie_character.character_name FROM movie_character
--     JOIN movie_characters ON movie_characters.character_id = movie_character.character_id
--     JOIN movie ON movie.movie_id = movie_characters.movie_id
--     JOIN franchise ON franchise.franchise_id = movie.franchise_id
--     WHERE franchise.franchise_id = 1;