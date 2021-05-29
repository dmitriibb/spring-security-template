create table users (
    id int(11) primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    active boolean not null default true
);

create table user_roles (
    id int(11) primary key auto_increment,
    user_id int(11) not null,
    role varchar(10) not null default 'USER',
    foreign key (user_id) references users(id) on delete cascade
);
