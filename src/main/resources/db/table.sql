
create table user_tb(
    users_id int primary KEY auto_increment,
    username VARCHAR(20) NOT NULL UNIQUE,
    password varchar(20) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    profile_img LONGTEXT,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

create table category(
    category_id int primary KEY AUTO_INCREMENT,
    category_title VARCHAR(50) UNIQUE,
    user_id int NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

create table love(
    love_id int primary KEY auto_increment,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
     UNIQUE uk_loves (user_id,post_id)
);


create table post(
    post_id int primary KEY auto_increment,
    post_title varchar(20) NOT null,
    post_content longtext NOT null,
    post_thumnail longtext,
    user_id int NOT NULL,
    category_id INT,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

create table visit(
    visit_id int primary KEY auto_increment,
    user_id int NOT null,
    total_count int NOT null,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE current_timestamp,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);
