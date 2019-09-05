
    create table account (
       id integer not null auto_increment,
        last_daily_cards_refresh_date datetime,
        num_of_common_cards_left integer,
        num_of_daily_cards_left integer,
        num_of_rare_cards_left integer,
        num_of_unique_cards_left integer,
        game_stats_id integer,
        primary key (id)
    );

    create table account_achievement (
       id integer not null auto_increment,
        account_id integer not null,
        achievement_id integer,
        primary key (id)
    );

    create table account_card (
       id integer not null auto_increment,
        account_id integer not null,
        card_count integer not null,
        card_id integer,
        primary key (id)
    );

    create table account_collection (
       id integer not null auto_increment,
        account_id integer not null,
        completed bit,
        deleted bit,
        last_new_card_for_common datetime,
        last_new_card_for_rare datetime,
        last_new_card_for_unique datetime,
        probability_of_rare integer,
        probability_of_unique integer,
        collection_id integer,
        primary key (id)
    );

    create table achievement (
       id integer not null auto_increment,
        cap_image_url varchar(255),
        common_cards_awarded integer,
        criteria varchar(255),
        description varchar(255) not null,
        rare_cards_awarded integer,
        title varchar(255) not null,
        unique_cards_awarded integer,
        primary key (id)
    );

    create table card (
       id integer not null auto_increment,
        card_class varchar(255),
        collection_id integer,
        description longtext,
        image_url varchar(255),
        points integer,
        title varchar(255) not null,
        primary key (id)
    );

    create table collection (
       id integer not null auto_increment,
        cap_image_url varchar(255),
        num_of_cards integer,
        title varchar(255) not null,
        collection_category_id integer,
        primary key (id)
    );

    create table collection_category (
       id integer not null auto_increment,
        title varchar(255) not null,
        primary key (id)
    );

    create table game_stats (
       id integer not null auto_increment,
        total_cards_opened integer not null,
        total_common_cards_opened integer not null,
        total_points integer not null,
        total_rare_cards_opened integer not null,
        total_unique_cards_opened integer not null,
        primary key (id)
    );

    create table user (
       id integer not null auto_increment,
        account_id integer not null,
        email varchar(255) not null,
        fullname varchar(255),
        password varchar(255),
        role varchar(255) not null,
        sign_in_type varchar(255) not null,
        signup bit not null,
        primary key (id)
    );