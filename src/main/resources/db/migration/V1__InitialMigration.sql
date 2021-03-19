CREATE TABLE IF NOT EXISTS "user" (
  id bigserial NOT NULL PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "movie_news" (
  id bigserial PRIMARY KEY,
  title varchar(255) NOT NULL,
  is_visible bit NOT NULL,
  main_image_url varchar(255),
  publication_date timestamp NOT NULL,
  description varchar(255) NOT NULL,
  video_url varchar(255),
  gallery_image_urls varchar(255),
  seo_url varchar(255),
  seo_title varchar(255),
  seo_keywords varchar(255),
  seo_description varchar(255)
);

CREATE TABLE IF NOT EXISTS "movie" (
  id bigserial NOT NULL PRIMARY KEY,
  title varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  main_image_url varchar(255),
  gallery_image_urls varchar(255),
  video_url varchar(255),
  release_date date NOT NULL,
  available_in_two_d boolean NOT NULL,
  available_in_three_d boolean NOT NULL,
  available_in_imax boolean NOT NULL,
  seo_url varchar(255),
  seo_title varchar(255),
  seo_keywords varchar(255),
  seo_description varchar(255)
);
